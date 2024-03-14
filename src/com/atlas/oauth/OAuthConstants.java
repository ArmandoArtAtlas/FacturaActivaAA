package com.atlas.oauth;

public class OAuthConstants {

    public static final String ACCESS_TOKEN = "access_token";
    public static final String CLIENT_ID = "client_id";
    public static final String CLIENT_SECRET = "client_secret";
    public static final String REFRESH_TOKEN = "refresh_token";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String CODE = "code";
    public static final String CALLER = "caller";
    public static final String AUTHENTICATION_SERVER_URL = "authentication_server_url";
    public static final String RESOURCE_SERVER_URL_DOCUMENTS = "resource_server_url_documents";
    public static final String RESOURCE_SERVER_URL_SUMMARIES = "resource_server_url_summaries";
    public static final String GRANT_TYPE = "grant_type";
    public static final String GRANT_TYPE_PASSWORD = "password";
    public static final String GRANT_TYPE_AUTHORIZATION_CODE = "authorization_code";
    public static final String GRANT_TYPE_CLIENT_CREDENTIALS = "client_credentials";
    public static final String SCOPE = "scope";
    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer";
    public static final String BASIC = "Basic";
    public static final String JSON_CONTENT = "application/json";
    public static final String XML_CONTENT = "application/xml";
    public static final String URL_ENCODED_CONTENT = "application/x-www-form-urlencoded";
    public static final int HTTP_OK = 200;
    public static final int HTTP_FORBIDDEN = 403;
    public static final int HTTP_UNAUTHORIZED = 401;
    public static final String TOKEN_LOCATION = "/information/token/token.txt";
    public static final String LOGNAME = "ArtAtlasFacturador";
    
    public static final String DATABASE_NAME = "B1H_ARTATLAS";
    public static final String DATABASE_USER = "SYSTEM";
    public static final String DATABASE_PASS = "Passw0rd";
    public static final String DATABASE_URL = "jdbc:sap://192.168.0.104:30015/autocomit=false";
    
    public static final String POSTGRES_DATABASE_NAME = "Produccion";
    public static final String POSTGRES_DATABASE_USER = "sistemas";
    public static final String POSTGRES_DATABASE_PASS = "sis7emas";
    public static final String POSTGRES_DATABASE_URL = "jdbc:postgresql://192.168.0.15:5432/"+POSTGRES_DATABASE_NAME;
    //public static final String POSTGRES_DATABASE_URL = "jdbc:postgresql://192.168.0.15:5432/AAPrueba04?ssl=false";
    
    //MsSql Sap Anntarah
    public static final String MSSQL_ANN_DATABASE_NAME = "SBO_ANNTARAH_PRODUCCION";
    public static final String MSSQL_ANN_DATABASE_USER = "sa";
    public static final String MSSQL_ANN_DATABASE_PASS = "4T3NU$2015";
    public static final String MSSQL_ANN_DATABASE_URL = "jdbc:sqlserver://192.168.0.212:1433;databaseName="+MSSQL_ANN_DATABASE_NAME;
	
    
}
