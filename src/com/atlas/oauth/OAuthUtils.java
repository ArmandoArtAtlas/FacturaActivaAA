package com.atlas.oauth;

import com.atlas.business.Token;
import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;

import org.apache.http.ParseException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.simple.parser.JSONParser;

//import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class OAuthUtils {

    public static int numberOfTries = 10;

    public static OAuth2Details createOAuthDetails(Properties config) {
        OAuth2Details oauthDetails = new OAuth2Details();
        oauthDetails.setAccessToken((String) config
                .get("access_token"));

        oauthDetails.setRefreshToken((String) config
                .get("refresh_token"));

        oauthDetails.setGrantType((String) config
                .get("grant_type"));

        oauthDetails.setClientId((String) config.get("client_id"));

        oauthDetails.setClientSecret((String) config
                .get("client_secret"));

        oauthDetails.setScope((String) config.get("scope"));

        oauthDetails.setAuthenticationServerUrl((String) config
                .get("authentication_server_url"));

        oauthDetails.setUsername((String) config.get("username"));

        oauthDetails.setPassword((String) config.get("password"));

        oauthDetails.setResourceServerUrlDocuments((String) config.get("resource_server_url_documents"));

        oauthDetails.setResourceServerUrlSummaries((String) config.get("resource_server_url_summaries"));

        if (!isValid(oauthDetails.getResourceServerUrlDocuments())) {
            System.out.println("Resource server url is null. Will assume request is for generating Access token");
            oauthDetails.setAccessTokenRequest(true);
        }

        return oauthDetails;
    }

    public static Properties getClientConfigProps(String path) {
        System.out.println("#######################");
        System.out.println(path);
        File miDir = new File(".");
        path = "Oauth2Client.config";
        try {
            System.out.println("Directorio actual: " + miDir.getAbsolutePath());
        } catch (Exception ex) {
            Logger.getLogger(OAuthUtils.class.getName()).log(Level.SEVERE, null, ex);
        }

        InputStream is = OAuthUtils.class.getClassLoader().getResourceAsStream(path);
        Properties config = new Properties();
        try {
            config.load(is);
        } catch (IOException e) {
            System.out.println("Could not load properties from " + path);
            e.printStackTrace();
            return null;
        }
        return config;
    }

    public static HttpResponse getProtectedResource(OAuth2Details oauthDetails, String json, String type) throws ClientProtocolException, IOException {
        String resourceURL = oauthDetails.getResourceServerUrlDocuments();

        if (type.equals("CB")) {
            resourceURL = oauthDetails.getResourceServerUrlSummaries();
        }

        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost post = new HttpPost(resourceURL);
        Token token = Token.getToken();

        StringEntity postingString = new StringEntity(json, StandardCharsets.UTF_8);
        post.setEntity((HttpEntity) postingString);
        post.setHeader("Content-type", "application/json");
        post.setHeader("User-Agent", "Client Application");
        post.setHeader("Authorization", "Bearer " + token.getTokenString());
        HttpResponse response = httpClient.execute((HttpUriRequest) post);

        int code = response.getStatusLine().getStatusCode();
        System.out.println(response.getStatusLine());
        System.out.println(code);
        if (code == 401 || code == 403) {

            //System.out.println("Number of tries "+numberOfTries);
            numberOfTries--;

            if (numberOfTries == 0) {
                //System.out.println(response.getStatusLine());
                return null;
            }
            getProtectedResource(oauthDetails, json, type);
        }
        return response;
    }

    public static String getToken(OAuth2Details oauthDetails) throws ClientProtocolException, IOException {
        String postUrl = oauthDetails.getAuthenticationServerUrl();
        Gson gson = new Gson();
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost post = new HttpPost(postUrl);
        BodyAccessToken body = new BodyAccessToken();

        body.grant_type = "client_credentials";
        StringEntity postingString = new StringEntity(gson.toJson(body));
        post.setEntity((HttpEntity) postingString);
        post.setHeader("Content-type", "application/json");
        post.setHeader("User-Agent", "Client Application");
        post.setHeader("Authorization", encodeCredentials(oauthDetails.getClientId(), oauthDetails.getClientSecret()));
        HttpResponse response = httpClient.execute((HttpUriRequest) post);

        Map<String, String> map = handleResponse(response);
        Token token = Token.getToken();
        String accessToken = map.get("access_token");

        token.setToken(accessToken);

        for (Map.Entry<String, String> entry : map.entrySet()) {
            token.setVars(entry.getKey(), String.valueOf(entry.getValue()));
        }

        token.save();

        return accessToken;
    }

    public static String getAccessToken(OAuth2Details oauthDetails) {
        String token = "";
        try {
            token = getToken(oauthDetails);
        } catch (ClientProtocolException e2) {
            e2.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return token;
    }

    public static Map<String, String> handleResponse(HttpResponse response) {
        
        
        String contentType = "application/json";
        if (response.getEntity().getContentType() != null) {
            contentType = response.getEntity().getContentType().getValue();
        }
        
        if (contentType.contains("application/json")) {
            return handleJsonResponse(response);
        }
        if (contentType.contains("application/x-www-form-urlencoded")) {
            return handleURLEncodedResponse(response);
        }
        if (contentType.contains("application/xml")) {
            return handleXMLResponse(response);
        }

        throw new RuntimeException("Cannot handle " + contentType + " content type. Supported content types include JSON, XML and URLEncoded");
        
    }

    public static Map<String, String> handleJsonResponse(HttpResponse response) {
        Map<String, String> oauthLoginResponse = null;

        try {
            oauthLoginResponse = (Map<String, String>) (new JSONParser()).parse(EntityUtils.toString(response.getEntity()));
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException();
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
            throw new RuntimeException();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        } catch (RuntimeException e) {
            System.out.println("Could not parse JSON response");
            throw e;
        }

        return oauthLoginResponse;
    }

    public static Map<String, String> handleURLEncodedResponse(HttpResponse response) {
        Map<String, Charset> map = Charset.availableCharsets();
        Map<String, String> oauthResponse = new HashMap<>();
        Set<Map.Entry<String, Charset>> set = map.entrySet();
        Charset charset = null;
        HttpEntity entity = response.getEntity();

        System.out.println();
        System.out.println("********** Response Received **********");

        for (Map.Entry<String, Charset> entry : set) {
            System.out.println(String.format("  %s = %s", new Object[]{entry.getKey(), entry
                .getValue()}));
            if (((String) entry.getKey()).equalsIgnoreCase("UTF-8")) {
                charset = entry.getValue();
            }
        }

        try {
            List<NameValuePair> list = URLEncodedUtils.parse(
                    EntityUtils.toString(entity), Charset.forName("UTF-8"));
            for (NameValuePair pair : list) {
                System.out.println(String.format("  %s = %s", new Object[]{pair.getName(), pair
                    .getValue()}));
                oauthResponse.put(pair.getName(), pair.getValue());
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not parse URLEncoded Response");
        }

        return oauthResponse;
    }

    public static Map<String, String> handleXMLResponse(HttpResponse response) {
        Map<String, String> oauthResponse = new HashMap<>();

        try {
            String xmlString = EntityUtils.toString(response.getEntity());

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = factory.newDocumentBuilder();
            InputSource inStream = new InputSource();
            inStream.setCharacterStream(new StringReader(xmlString));
            Document doc = db.parse(inStream);

            System.out.println("********** Response Receieved **********");
            parseXMLDoc(null, doc, oauthResponse);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Exception occurred while parsing XML response");
        }

        return oauthResponse;
    }

    public static void parseXMLDoc(Element element, Document doc, Map<String, String> oauthResponse) {
        NodeList child = null;
        if (element == null) {
            child = doc.getChildNodes();
        } else {

            child = element.getChildNodes();
        }
        for (int j = 0; j < child.getLength(); j++) {
            if (child.item(j).getNodeType() == 1) {

                Element childElement = (Element) child.item(j);
                if (childElement.hasChildNodes()) {
                    System.out.println(childElement.getTagName() + " : " + childElement
                            .getTextContent());
                    oauthResponse.put(childElement.getTagName(), childElement
                            .getTextContent());
                    parseXMLDoc(childElement, null, oauthResponse);
                }
            }
        }
    }

    public static String getAuthorizationHeaderForAccessToken(String accessToken) {
        return "Bearer " + accessToken;
    }

    public static String getBasicAuthorizationHeader(String username, String password) {
        return "Basic "
                + encodeCredentials(username, password);
    }

    public static String encodeCredentials(String username, String password) {
        String cred = username + ":" + password;
        String encodedValue = null;
        byte[] encodedBytes = Base64.encodeBase64(cred.getBytes());
        encodedValue = new String(encodedBytes);

        return "Basic " + encodedValue;
    }

    public static boolean isValidInput(OAuth2Details input) {
        if (input == null) {
            return false;
        }

        String grantType = input.getGrantType();

        if (!isValid(grantType)) {
            System.out.println("Please provide valid value for grant_type");
            return false;
        }

        if (!isValid(input.getAuthenticationServerUrl())) {
            System.out.println("Please provide valid value for authentication server url");
            return false;
        }

        if (grantType.equals("password") && (!isValid(input.getUsername()) || !isValid(input.getPassword()))) {
            System.out.println("Please provide valid username and password for password grant_type");
            return false;
        }

        if (grantType.equals("client_credentials") && (!isValid(input.getClientId()) || !isValid(input.getClientSecret()))) {
            System.out.println("Please provide valid client_id and client_secret for client_credentials grant_type");
            return false;
        }

        return true;
    }

    public static boolean isValid(String str) {
        return (str != null && str.trim().length() > 0);
    }
}
