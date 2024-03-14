package com.atlas.oauth;

import com.atlas.business.DocObject;
import com.atlas.business.Token;

import com.atlas.readerSql.JsonGenerator;
import com.atlas.response.FacturaResponse;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;

public class OAuth2Client {

    public static String LogName = "ArtAtlasFacturador";
    public static boolean flag_factura_boleta = false;
    public static boolean flag_nota_credito = false;
    public static boolean flag_nota_debito = false;
    public static boolean flag_factura_boleta_anticipo = false;
    public static boolean flag_guia_remision = false;
    
    /*
    public static boolean flag_guia_remision_entrega_venta = false;
    public static boolean flag_guia_remision_devolucion_venta = false;
    public static boolean flag_guia_remision_entrada_compra = false;
    public static boolean flag_guia_remision_devolucion_compra = false;
    public static boolean flag_guia_remision_entrada_inventario = false;
    public static boolean flag_guia_remision_salida_inventario = false;
    public static boolean flag_guia_remision_transferencia_inventario = false;
    public static boolean flag_guia_remision_transferencia_odoo = false;
    */
    public static boolean flag_resumen_baja = false;
    public static boolean flag_test = false;
    public static boolean flag_no_option = false;
    public static String idTransaccion;
    
    public static String flagDocTypeDocument;
    public static String flagDocTypeGuia;
    public static String flagDocTypeDetail;
    public static String dataBaseExecute;
    

    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            System.out.print(args[i] + " ");
            
        }
        if ((((args.length == 0) ? 1 : 0) | ((args.length == 1) ? 1 : 0)) != 0) {
            
            return;
        }
        
        switch (args[0]) {
            case "-f":
                flag_factura_boleta = true;
                idTransaccion = args[1];
                break;
            case "-b":
                flag_factura_boleta = true;
                idTransaccion = args[1];
                break;
            case "-nc":
                flag_nota_credito = true;
                idTransaccion = args[1];
                break;
            case "-nd":
                flag_nota_debito = true;
                idTransaccion = args[1];
                break;
            case "-fa":
                flag_factura_boleta_anticipo = true;
                idTransaccion = args[1];
                break;
            case "-rb":
                flag_resumen_baja = true;
                idTransaccion = args[1];
                break;
                
            case "-grve":
                flag_guia_remision = true;
                idTransaccion = args[1];
                flagDocTypeDocument = OAuth2Details.getQueryGuiaDocumentoVentaEntrega;
                flagDocTypeGuia = OAuth2Details.getQueryGuiaVentaEntrega;
                flagDocTypeDetail = OAuth2Details.getQueryGuiaDetalleVentaEntrega;
                break;
            case "-grvd" :
                flag_guia_remision = true;
                idTransaccion = args[1];
                flagDocTypeDocument = OAuth2Details.getQueryGuiaDocumentoVentaDevolucion;
                flagDocTypeGuia = OAuth2Details.getQueryGuiaVentaDevolucion;
                flagDocTypeDetail = OAuth2Details.getQueryGuiaDetalleVentaDevolucion;
                break;    
            case "-grce" :
                flag_guia_remision = true;
                idTransaccion = args[1];
                flagDocTypeDocument = OAuth2Details.getQueryGuiaDocumentoCompraEntrada;
                flagDocTypeGuia = OAuth2Details.getQueryGuiaCompraEntrada;
                flagDocTypeDetail = OAuth2Details.getQueryGuiaDetalleCompraEntrada;
                break;    
            case "-grcd" :
                flag_guia_remision = true;
                idTransaccion = args[1];
                flagDocTypeDocument = OAuth2Details.getQueryGuiaDocumentoCompraDevolucion;
                flagDocTypeGuia = OAuth2Details.getQueryGuiaCompraDevolucion;
                flagDocTypeDetail = OAuth2Details.getQueryGuiaDetalleCompraDevolucion;
                break;    
            case "-grie" :
                flag_guia_remision = true;
                idTransaccion = args[1];
                flagDocTypeDocument = OAuth2Details.getQueryGuiaDocumentoInventarioEntrada;
                flagDocTypeGuia = OAuth2Details.getQueryGuiaInventarioEntrada;
                flagDocTypeDetail = OAuth2Details.getQueryGuiaDetalleInventarioEntrada;
                break;    
            case "-gris" :
                flag_guia_remision = true;
                idTransaccion = args[1];
                flagDocTypeDocument = OAuth2Details.getQueryGuiaDocumentoInventarioSalida;
                flagDocTypeGuia = OAuth2Details.getQueryGuiaInventarioSalida;
                flagDocTypeDetail = OAuth2Details.getQueryGuiaDetalleInventarioSalida;
                break;    
            case "-grto" :
                flag_guia_remision = true;
                idTransaccion = args[1];
                flagDocTypeDocument = OAuth2Details.getQueryGuiaDocumentoAsignacionOdoo;
                flagDocTypeGuia = OAuth2Details.getQueryGuiaAsignacionOdoo;
                flagDocTypeDetail = OAuth2Details.getQueryGuiaDetalleAsignacionOdoo;
                break;    
            case "-groo" :
                flag_guia_remision = true;
                idTransaccion = args[1];
                flagDocTypeDocument = OAuth2Details.getQueryGuiaDocumentoOtrosOdoo;
                flagDocTypeGuia = OAuth2Details.getQueryGuiaOtrosOdoo;
                flagDocTypeDetail = OAuth2Details.getQueryGuiaDetalleOtrosOdoo;
                break;    
            
            default:
                flag_no_option = true;
                break;
        }

        if (flag_no_option) {
            return;
        }
        
        try {
            System.out.println("\nArtAtlasFacturador System Started");

            OAuth2Details oauthDetails = new OAuth2Details();
            Token token = Token.getToken();

            boolean req = token.load();
            
            oauthDetails.setAccessTokenRequest(!req);

            if (!OAuthUtils.isValidInput(oauthDetails)) {
                System.out.println("Ingresar una configuracion valida.");
                System.exit(0);
            }
            
            System.out.println("step 0");
            String accessToken = OAuthUtils.getAccessToken(oauthDetails);
            
            if (OAuthUtils.isValid(accessToken)) {
                System.out.println("Se genero exitosamente el Token de Acceso");
            } else {
                System.out.println("Nose pudo generar el Token de Acceso");
            }

            JsonGenerator jgen = new JsonGenerator();

            if (flag_factura_boleta) {
                System.out.println("Generando Factuta Boleta id:" + idTransaccion);
                
                sendBoletasFacturas(oauthDetails, jgen, idTransaccion);
                
            } else if (flag_factura_boleta_anticipo) {
                System.out.println("Generando Factura de Anticipo id:" + idTransaccion);
                sendBoletasFacturasAnticipo(oauthDetails, jgen, idTransaccion);
            } else if (flag_nota_credito) {
                System.out.println("Generando Nota de credito id:" + idTransaccion);
                sendNotasCredito(oauthDetails, jgen, idTransaccion);
            } else if (flag_guia_remision) {
                System.out.println("Generando Guia de Remision id:" + idTransaccion);
                sendGuiaRemision(oauthDetails, jgen, idTransaccion,flagDocTypeDocument,flagDocTypeGuia,flagDocTypeDetail,args[0]);
            } else if (flag_nota_debito) {
                System.out.println("Generando Nota de Debito id:" + idTransaccion);
                sendNotasDebito(oauthDetails, jgen, idTransaccion);
            } else if (flag_resumen_baja) {
                System.out.println("Generando Resumen Baja id:" + idTransaccion);
                sendBajas(oauthDetails, jgen, idTransaccion);
            }

            System.out.println();
        } catch (Exception e) {
            
            System.out.println("Se Genero un Error - Posibles Razones: \n "
                    + "1. Desconexion con el servicio de Factura Activa. \n "
                    + "2. Error de Conexion con la Base de Datos. \n "
                    + "3. Error de Conexion por tema de red.");
            System.out.println(e);
            
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String errorStackTrace = sw.toString();
            System.out.println("Detalle del error: " + errorStackTrace);
            
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    private static void sendBoletasFacturas(OAuth2Details oauthDetails, JsonGenerator jgen, String idoperacion) throws SQLException, ClientProtocolException, IOException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        System.out.println("step 1");
        ArrayList<DocObject> arrJson = jgen.getJsonMessageBoletaFactura(idoperacion);
        System.out.println("step 2");
        for (int i = 0; i < arrJson.size(); i++) {
            System.out.println("Envio idTransaccion: " + ((DocObject) arrJson.get(i)).getIdTransaccion());
            HttpResponse response = OAuthUtils.getProtectedResource(oauthDetails, ((DocObject) arrJson.get(i)).getJson(), "BF");
            FacturaResponse responseFacturActiva = new FacturaResponse(response, ((DocObject) arrJson.get(i)).getIdTransaccion(), "BF", ((DocObject) arrJson.get(i)).getDocument());
            storeOnFile(((DocObject) arrJson.get(i)).getJson(), responseFacturActiva.transId);
            System.out.println(responseFacturActiva);
            responseFacturActiva.save();
        }
    }

    private static void sendBoletasFacturasAnticipo(OAuth2Details oauthDetails, JsonGenerator jgen, String idoperacion) throws SQLException, ClientProtocolException, IOException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        ArrayList<DocObject> arrJson = jgen.getJsonMessageBoletaFacturaAnticipo(idoperacion);
        for (int i = 0; i < arrJson.size(); i++) {
            System.out.println("Envio idTransaccion: " + ((DocObject) arrJson.get(i)).getIdTransaccion());
            HttpResponse response = OAuthUtils.getProtectedResource(oauthDetails, ((DocObject) arrJson.get(i)).getJson(), "BF");
            FacturaResponse responseFacturActiva = new FacturaResponse(response, ((DocObject) arrJson.get(i)).getIdTransaccion(), "BF", ((DocObject) arrJson.get(i)).getDocument());
            storeOnFile(((DocObject) arrJson.get(i)).getJson(), responseFacturActiva.transId);
            System.out.println(responseFacturActiva);
            responseFacturActiva.save();
        }
    }

    private static void sendNotasCredito(OAuth2Details oauthDetails, JsonGenerator jgen, String idoperacion) throws ClientProtocolException, IOException, SQLException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        ArrayList<DocObject> arrJsonNC = jgen.getJsonMessageNotaCredito(idoperacion);
        for (int i = 0; i < arrJsonNC.size(); i++) {
            System.out.println("Envio idTransaccion: " + ((DocObject) arrJsonNC.get(i)).getIdTransaccion());
            HttpResponse response = OAuthUtils.getProtectedResource(oauthDetails, ((DocObject) arrJsonNC.get(i)).getJson(), "NC");
            FacturaResponse responseFacturActiva = new FacturaResponse(response, ((DocObject) arrJsonNC.get(i)).getIdTransaccion(), "NC", ((DocObject) arrJsonNC.get(i)).getDocument());
            storeOnFile(((DocObject) arrJsonNC.get(i)).getJson(), responseFacturActiva.transId);
            System.out.println(responseFacturActiva);
            responseFacturActiva.save();
        }
    }

    private static void sendNotasDebito(OAuth2Details oauthDetails, JsonGenerator jgen, String idoperacion) throws ClientProtocolException, IOException, SQLException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        ArrayList<DocObject> arrJsonND = jgen.getJsonMessageNotaDebito(idoperacion);
        for (int i = 0; i < arrJsonND.size(); i++) {
            System.out.println("Envio idTransaccion: " + ((DocObject) arrJsonND.get(i)).getIdTransaccion());

            HttpResponse response = OAuthUtils.getProtectedResource(oauthDetails, ((DocObject) arrJsonND.get(i)).getJson(), "ND");
            FacturaResponse responseFacturActiva = new FacturaResponse(response, ((DocObject) arrJsonND.get(i)).getIdTransaccion(), "ND", ((DocObject) arrJsonND.get(i)).getDocument());
            storeOnFile(((DocObject) arrJsonND.get(i)).getJson(), responseFacturActiva.transId);
            System.out.println(responseFacturActiva);
            responseFacturActiva.save();
        }
    }

    private static void sendGuiaRemision(OAuth2Details oauthDetails, JsonGenerator jgen, 
            String idoperacion, String flagDocTypeDocument, String flagDocTypeGuia, 
            String flagDocTypeDetail, String deliveryTypeFlag) throws ClientProtocolException, 
            IOException, SQLException, NoSuchMethodException, IllegalAccessException, 
            IllegalArgumentException, InvocationTargetException {
        
        ArrayList<DocObject> arrJsonGR = jgen.getJsonMessageGuiaRemision(idoperacion, flagDocTypeDocument, flagDocTypeGuia, flagDocTypeDetail, deliveryTypeFlag);
        for (int i = 0; i < arrJsonGR.size(); i++) {
            
            System.out.println("Envio idTransaccion: " + ((DocObject) arrJsonGR.get(i)).getIdTransaccion());
            HttpResponse response = OAuthUtils.getProtectedResource(oauthDetails, ((DocObject) arrJsonGR.get(i)).getJson(), "GR");
            FacturaResponse responseFacturActiva = new FacturaResponse(response, ((DocObject) arrJsonGR.get(i)).getIdTransaccion(), "GR", ((DocObject) arrJsonGR.get(i)).getDocument());
            storeOnFileGuias(((DocObject) arrJsonGR.get(i)).getJson(), responseFacturActiva.transId);
            System.out.println(responseFacturActiva);
            responseFacturActiva.save();
            /*
            if (!responseFacturActiva.transId.equals("")){
            }
            */
            /*
            Result = responseFacturActiva.transId +'|'+
                     responseFacturActiva.tipo +'|'+
                     responseFacturActiva.serie +'|'+
                     responseFacturActiva.correlativo +'|'+
                     responseFacturActiva.estado +'|'+
                     responseFacturActiva.cod_error +'|'+
                     responseFacturActiva.observaciones ;
            
            System.out.println("FE_DOCS_"+Result);
            */
        }
        
    }
    
    
    private static void sendBajas(OAuth2Details oauthDetails, JsonGenerator jgen, String idoperacion) throws ClientProtocolException, IOException, SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        ArrayList<DocObject> arrJsonB = jgen.getJsonMessageBajas(idoperacion);
        for (int i = 0; i < arrJsonB.size(); i++) {
            System.out.println("Envio idTransaccion: " + ((DocObject) arrJsonB.get(i)).getIdTransaccion());
            HttpResponse response = OAuthUtils.getProtectedResource(oauthDetails, ((DocObject) arrJsonB.get(i)).getJson(), "CB");
            FacturaResponse responseFacturActiva = new FacturaResponse(response, ((DocObject) arrJsonB.get(i)).getIdTransaccion(), "RA", ((DocObject) arrJsonB.get(i)).getDocument());
            storeOnFile(((DocObject) arrJsonB.get(i)).getJson(), responseFacturActiva.transId);
            System.out.println(responseFacturActiva);
            responseFacturActiva.save();
        }
    }

    private static void storeOnFile(String data, String transId) {
        try {
            Calendar cal = Calendar.getInstance();
            String DATE_FORMAT_NOW = "yyyy_MM_dd___HH_mm_ss";
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
            String nombre = transId + "_" + sdf.format(cal.getTime());

            FileWriter fichero = new FileWriter("C:\\FacturaElectronica\\" + nombre + ".txt");
            //FileWriter fichero = new FileWriter("C:\\FA_ENVIO\\Facturas\\" + nombre + ".txt");

            fichero.write(data + "\r\n");

            fichero.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
        private static void storeOnFileGuias(String data, String transId) {
        try {
            Calendar cal = Calendar.getInstance();
            String DATE_FORMAT_NOW = "yyyy_MM_dd___HH_mm_ss";
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
            String nombre = transId + "_" + sdf.format(cal.getTime());

            FileWriter fichero = new FileWriter("C:\\GuiasElectronicas\\Json\\" + nombre + ".txt");

            fichero.write(data + "\r\n");

            fichero.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
