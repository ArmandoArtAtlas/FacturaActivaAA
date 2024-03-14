package com.sunat.referencia;

import java.util.ArrayList;


public class Referencia
{
  public String tipoDocumentoRef;
  public String serieRef;
  public Integer correlativoRef;
  public String fechaEmisionRef;
  
  public void setTipoDocumentoRef(String tipoDocumentoRef) { this.tipoDocumentoRef = tipoDocumentoRef; }


  
  public void setSerieRef(String serieRef) { this.serieRef = serieRef; }


  
  public void setCorrelativoRef(String correlativoRef) { this.correlativoRef = Integer.valueOf(Integer.parseInt(correlativoRef)); }


  
  public void setFechaEmisionRef(String fechaEmisionRef) { this.fechaEmisionRef = fechaEmisionRef.substring(0, 10); }




  
  public boolean setReferenciaNotaCredito(ArrayList<String> data) {
    this.tipoDocumentoRef = data.get(0);
    this.serieRef = data.get(1);
    this.correlativoRef = Integer.valueOf(Integer.parseInt(data.get(2)));
    this.fechaEmisionRef = ((String)data.get(3)).substring(0, 10);
    return true;
  }
  
  public boolean setReferenciaNotaDebito(ArrayList<String> data) {
    this.tipoDocumentoRef = data.get(0);
    this.serieRef = data.get(1);
    this.correlativoRef = Integer.valueOf(Integer.parseInt(data.get(2)));
    this.fechaEmisionRef = ((String)data.get(3)).substring(0, 10);
    return true;
  }
}
