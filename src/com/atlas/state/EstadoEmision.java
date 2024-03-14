package com.atlas.state;

public class EstadoEmision {

    static String A = "Aceptado";
    static String E = "Enviado a SUNAT";
    static String N = "Envió Erróneo";
    static String O = "Aceptado con observación";
    static String R = "Rechazado";
    static String P = "Pendiente de envió SUNAT (Recibido por Facturactiva)";

    public static String getEstado(String var){
        switch(var){
            case "A": return A;
            case "E": return E;
            case "N": return N;
            case "O": return O;
            case "R": return R;
            case "P": return P;
            default: return "Estado no definido por factura activa";
        }
    }
}
