package com.atlas.error;

public class ErrorHandler {
    private static String error_10 = "Error de autenticación (credenciales invalidas o no existentes)";
    private static String error_11 = "Error de autorización (permisos)";
    private static String error_12 = "Cuenta suspendida";
    private static String error_13 = "Uso de servicio bloqueado por abuso";
    private static String error_14 = "Límite de consultas en el periodo de tiempo superadas (Rate Limit)";
    
    private static String error_31 = "Sujeto invalido o no existente";
    private static String error_32 = "Configuración necesaria para completar operación en servicio está incompleta.";
    private static String error_33 = "Credenciales necesarias para completar operación en servicio no existen.";
    private static String error_34 = "Certificados digital necesarios para completar operación en servicio no existen.";
    private static String error_35 = "Certificados digital necesarios para completar operación en servicio han expirado.";
    
    private static String error_49 = "Método/Modalidad no implementada";
    private static String error_50 = "No se encontró ningún recurso con los datos enviados.";
    private static String error_51 = "Validación de datos fallo al tratar de realizar operación en servicio.";
    private static String error_52 = "Validación de datos adicionales (personalizados) fallo al tratar de realizar operación en servicio.";
    
    private static String error_70 = "Error de duplicidad al tratar de procesar esta operación (Operación no única).";
    private static String error_71 = "La operación está en estado pendiente de procesamiento.";
    private static String error_72 = "Error retornado por entidad externa (Ej: Error retornado por SUNAT, etc)";
    
    private static String error_90 = "Error interno desconocido en la suite";
    
  public static String getErrorMessage(String value) {
    switch (value) { case "10":
        return error_10;
      case "11": return error_11;
      case "12": return error_12;
      case "13": return error_13;
      case "14": return error_14;
      case "31": return error_31;
      case "32": return error_32;
      case "33": return error_33;
      case "34": return error_34;
      case "35": return error_35;
      case "49": return error_49;
      case "50": return error_50;
      case "51": return error_51;
      case "52": return error_52;
      case "70": return error_70;
      case "71": return error_71;
      case "72": return error_72;
      case "90": return error_90; }
    
    return "Error no definido por FACTURACTIVA";
  }

  
  public static String getErrorMessage(int value) {
    switch (value) { case 10:
        return error_10;
      case 11: return error_11;
      case 12: return error_12;
      case 13: return error_13;
      case 14: return error_14;
      case 31: return error_31;
      case 32: return error_32;
      case 33: return error_33;
      case 34: return error_34;
      case 35: return error_35;
      case 49: return error_49;
      case 50: return error_50;
      case 51: return error_51;
      case 52: return error_52;
      case 70: return error_70;
      case 71: return error_71;
      case 72: return error_72;
      case 90: return error_90; }
    
    return "Error no definido por FACTURACTIVA";
  }
}
