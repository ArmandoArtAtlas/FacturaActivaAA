package com.atlas.oauth;

public class OAuth2Details {

    private String scope = "";

    private String grantType = "client_credentials";
    private String clientId = "2UFezWRaTjsAtqVGtL48qj";
    private String clientSecret = "YzNkZTdhNjNkNjk1NDRmZjhjYTU3MWRlYWVjNzUwYWQ";
    private static String DataBase = "B1H_ARTATLAS";
    private String accessToken = "";
    private String refreshToken;
    private String username;
    private String password;
    
    
    /*  
    private String authenticationServerUrl = "http://159.203.97.221:3000/oauth2/token";
    private String resourceServerUrlDocuments = "http://159.203.97.221:3000/emission/documents";
    private String resourceServerUrlSummaries = "http://159.203.97.221:3000/emission/summaries";
    */
    
    //Pruebas
   /*
    private String authenticationServerUrl = "http://dev.api.emisores.facturactiva.com/oauth2/token";
    private String resourceServerUrlDocuments = "http://dev.api.emisores.facturactiva.com/emission/documents";
    private String resourceServerUrlSummaries = "http://dev.api.emisores.facturactiva.com/emission/summaries";
    */
   
    //Produccion
   
    private String authenticationServerUrl = "http://api.emisores.facturactiva.com/oauth2/token";
    private String resourceServerUrlDocuments = "http://api.emisores.facturactiva.com/emission/documents";
    private String resourceServerUrlSummaries = "http://api.emisores.facturactiva.com/emission/summaries";
   
   /*  
    public static String getQueryBoletaFactura = "CALL \"B1H_ARTATLAS\".\"FA_Documento\" (%s)";
    public static String getQueryDetalleDocumento_BF = "CALL \"B1H_ARTATLAS\".\"FA_Detalle\" (%s)";
    public static String getQueryIndicadorDocumento_BF = "CALL \"B1H_ARTATLAS\".\"FA_Indicadores\" (%s)";
    public static String getQueryImpuestoDocumento_BF = "CALL \"B1H_ARTATLAS\".\"FA_Impuesto\" (%s)";
    public static String getQueryCuotasDocumento_BF = "CALL \"B1H_ARTATLAS\".\"FA_CuotasPago\" (%s)";
     */
    
    /*Factura y Boleta de Venta*/
    public static String getQueryBoletaFactura = "CALL " + DataBase + ".\"FA_Documento\" (%s)";
    public static String getQueryDetalleDocumento_BF = "CALL " + DataBase + ".\"FA_Detalle\" (%s)";
    public static String getQueryIndicadorDocumento_BF = "CALL " + DataBase + ".\"FA_Indicadores\" (%s)";
    public static String getQueryImpuestoDocumento_BF = "CALL " + DataBase + ".\"FA_Impuesto\" (%s)";
    public static String getQueryCuotasDocumento_BF = "CALL " + DataBase + ".\"FA_CuotasPago\" (%s)";
    public static String getQueryAnticipoDocumento_BF = "CALL " + DataBase + ".\"FA_Anticipo\" (%s)";
    public static String getQueryDescuentoDocumento_BF = "CALL " + DataBase + ".\"FA_Descuento\" (%s)";
    
    /*Factura y Boleta de Venta Anticipos*/
    public static String getQueryBoletaFacturaAnticipo = "CALL " + DataBase + ".\"FA_Documento_Anticipo\" (%s)";
    public static String getQueryDetalleDocumento_Anticipo_BF = "CALL " + DataBase + ".\"FA_Detalle_Anticipo\" (%s)";
    public static String getQueryIndicadorDocumento_Anticipo_BF = "CALL " + DataBase + ".\"FA_Indicadores_Anticipo\" (%s)";
    public static String getQueryImpuestoDocumento_Anticipo_BF = "CALL " + DataBase + ".\"FA_Impuesto_Anticipo\" (%s)";
    public static String getQueryCuotasDocumento_Anticipo_BF = "CALL " + DataBase + ".\"FA_CuotasPago_Anticipo\" (%s)";
    
    /*Nota de Credito */
    public static String getQueryNotaCredito = "CALL " + DataBase + ".\"FA_Documento_nc\" (%s)";
    public static String getQueryDetalleNotaCredito = "CALL " + DataBase + ".\"FA_Detalle_nc\" (%s)";
    public static String getQueryImpuestoNotaCredito = "CALL " + DataBase + ".\"FA_Impuesto_nc\" (%s)";
    public static String getQueryIndicadorNotaCredito = "CALL " + DataBase + ".\"FA_Indicadores_NC\" (%s)";
    public static String getQueryReferenciaNotaCredito = "CALL " + DataBase + ".\"FA_Referencia\" (%s)";
    public static String getQueryCuotasNotaCredito = "CALL " + DataBase + ".\"FA_CuotasPago_NC\" (%s)";

    /*Nota de Debito*/
    public static String getQueryNotaDebito = "CALL " + DataBase + ".\"FA_Documento_ND\"(%s)";
    public static String getQueryDetalleNotaDebito = "CALL " + DataBase + ".\"FA_Detalle_ND\"(%s)";
    public static String getQueryImpuestoNotaDebito = "CALL " + DataBase + ".\"FA_Impuesto_ND\"(%s)";
    public static String getQueryIndicadorNotaDebito = "CALL " + DataBase + ".\"FA_Indicadores_ND\"(%s)";
    public static String getQueryReferenciaNotaDebito = "CALL " + DataBase + ".\"FA_Referencia_ND\"(%s)";

    /*Guia de Remision*/
    public static String getQueryGuia = "CALL " + DataBase + ".\"GR_Guia\"(%s)";
    public static String getQueryGuiaRemision = "CALL " + DataBase + ".\"GR_Documento\"(%s)";
    public static String getQueryGuiaDetalle = "CALL " + DataBase + ".\"GR_Detalle\"(%s)";
    
    public static String getQueryGuiaDocumentoVentaEntrega = "CALL " + DataBase + ".\"GR_Documento_Venta_Entrega\"(%s)";
    public static String getQueryGuiaDocumentoVentaDevolucion = "CALL " + DataBase + ".\"GR_Documento_Venta_Devolucion\"(%s)";
    public static String getQueryGuiaDocumentoCompraEntrada = "CALL " + DataBase + ".\"GR_Documento_Compra_Entrada\"(%s)";
    public static String getQueryGuiaDocumentoCompraDevolucion = "CALL " + DataBase + ".\"GR_Documento_Compra_Devolucion\"(%s)";
    public static String getQueryGuiaDocumentoInventarioEntrada = "CALL " + DataBase + ".\"GR_Documento_Inventario_Entrada\"(%s)";
    public static String getQueryGuiaDocumentoInventarioSalida = "CALL " + DataBase + ".\"GR_Documento_Inventario_Salida\"(%s)";
   
    public static String getQueryGuiaVentaEntrega = "CALL " + DataBase + ".\"GR_Guia_Venta_Entrega\"(%s)";
    public static String getQueryGuiaVentaDevolucion = "CALL " + DataBase + ".\"GR_Guia_Venta_Devolucion\"(%s)";
    public static String getQueryGuiaCompraEntrada = "CALL " + DataBase + ".\"GR_Guia_Compra_Entrada\"(%s)";
    public static String getQueryGuiaCompraDevolucion = "CALL " + DataBase + ".\"GR_Guia_Compra_Devolucion\"(%s)";
    public static String getQueryGuiaInventarioEntrada = "CALL " + DataBase + ".\"GR_Guia_Inventario_Entrada\"(%s)";
    public static String getQueryGuiaInventarioSalida = "CALL " + DataBase + ".\"GR_Guia_Inventario_Salida\"(%s)";
    
    public static String getQueryGuiaDetalleVentaEntrega = "CALL " + DataBase + ".\"GR_Detalle_Venta_Entrega\"(%s)";
    public static String getQueryGuiaDetalleVentaDevolucion = "CALL " + DataBase + ".\"GR_Detalle_Venta_Devolucion\"(%s)";
    public static String getQueryGuiaDetalleCompraEntrada = "CALL " + DataBase + ".\"GR_Detalle_Compra_Entrada\"(%s)";
    public static String getQueryGuiaDetalleCompraDevolucion = "CALL " + DataBase + ".\"GR_Detalle_Compra_Devolucion\"(%s)";
    public static String getQueryGuiaDetalleInventarioEntrada = "CALL " + DataBase + ".\"GR_Detalle_Inventario_Entrada\"(%s)";
    public static String getQueryGuiaDetalleInventarioSalida = "CALL " + DataBase + ".\"GR_Detalle_Inventario_Salida\"(%s)";
    
    public static String getQueryGuiaDocumentoAsignacionOdoo = "SELECT * FROM \"gr_odoo_cabecera\" (%s)";
    public static String getQueryGuiaAsignacionOdoo = "SELECT * FROM \"gr_odoo_guia_inventario_salida\" (%s)";
    public static String getQueryGuiaDetalleAsignacionOdoo = "SELECT * FROM \"gr_odoo_detalle\" (%s)";
    
    public static String getQueryGuiaDocumentoOtrosOdoo = "SELECT * FROM \"gr_odoo_cabecera_traslado_materiales\" (%s)";
    public static String getQueryGuiaOtrosOdoo = "SELECT * FROM \"gr_odoo_guia_no_inventariable_salida_traslado_maquinarias\" (%s)";
    public static String getQueryGuiaDetalleOtrosOdoo = "SELECT * FROM \"gr_odoo_detalle_traslado_materiales\" (%s)";
    
    
    /*Comunicado de Baja*/
    public static String getQueryResumenBaja = "CALL " + DataBase + ".\"FA_Resumen\" (%s)";
    public static String getQueryDocumentoBaja = "CALL " + DataBase + ".\"FA_Baja\" (%s)";

    /*Respuesta del Servicio de Facturacion*/
    public static String insertResponse = "INSERT INTO " + DataBase + ".\"@FE_DOCS\" (\"Code\", \"Name\", \"U_Transid\", \"U_Tipo\", \"U_Serie\", \"U_Correlativo\", \"U_Estado\", \"U_Cod_Error\", \"U_Observacion\") VALUES ('%s','%s','%s','%s','%s','%s','%s','%s','%s')";
    public static String selectBefore = "SELECT CAST(\"Name\" AS INT) AS \"ind\" FROM " + DataBase + ".\"@FE_DOCS\" ORDER BY \"ind\" DESC ";

    private boolean isAccessTokenRequest;

    public String getScope() {
        return this.scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getGrantType() {
        return this.grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public String getClientId() {
        return this.clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return this.clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return this.refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getAuthenticationServerUrl() {
        return this.authenticationServerUrl;
    }

    public void setAuthenticationServerUrl(String authenticationServerUrl) {
        this.authenticationServerUrl = authenticationServerUrl;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAccessTokenRequest() {
        return this.isAccessTokenRequest;
    }

    public void setAccessTokenRequest(boolean isAccessTokenRequest) {
        this.isAccessTokenRequest = isAccessTokenRequest;
    }

    public String getResourceServerUrlDocuments() {
        return this.resourceServerUrlDocuments;
    }

    public void setResourceServerUrlDocuments(String resourceServerUrl) {
        System.out.println("setteando Doc " + resourceServerUrl);
        this.resourceServerUrlDocuments = resourceServerUrl;
    }

    public String getResourceServerUrlSummaries() {
        return this.resourceServerUrlSummaries;
    }

    public void setResourceServerUrlSummaries(String resourceServerUrl) {
        System.out.println("setteando Sum " + resourceServerUrl);
        this.resourceServerUrlSummaries = resourceServerUrl;
    }
}
