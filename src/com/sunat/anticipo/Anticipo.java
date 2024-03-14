package com.sunat.anticipo;

public class Anticipo {

    public String tipoDocumentoAnticipo;    //opcional //no habilitado en notas
    public String serieAnticipo;            //opcional //no habilitado en notas
    public Integer correlativoAnticipo;     //opcional //no habilitado en notas
    public Double mntAnticipo;              //opcional //no habilitado en notas
    public String nombreAnticipo;           //opcional //no habilitado en notas

    public void setTipoDocumentoAnticipo(String tipoDocumentoAnticipo) {
        this.tipoDocumentoAnticipo = tipoDocumentoAnticipo;
    }

    public void setSerieAnticipo(String serieAnticipo) {
        this.serieAnticipo = serieAnticipo;
    }

    public void setCorrelativoAnticipo(String correlativoAnticipo) {
        this.correlativoAnticipo = Integer.valueOf(Integer.parseInt(correlativoAnticipo));
    }

    public void setMntAnticipo(String mntAnticipo) {
        this.mntAnticipo = Double.valueOf(Double.parseDouble(mntAnticipo));
    }

    public void setNombreAnticipo(String nombreAnticipo) {
        this.nombreAnticipo = nombreAnticipo;
    }

}
