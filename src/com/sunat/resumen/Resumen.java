package com.sunat.resumen;

public class Resumen {

    public Integer id;
    public String tipoDocEmisor;
    public String numDocEmisor;
    public String nombreEmisor;
    public String fechaReferente;
    public String tipoFormatoRepresentacionImpresa = "GENERAL";

    public Integer getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = Integer.valueOf(Integer.parseInt(id));
    }

    public String getTipoDocEmisor() {
        return this.tipoDocEmisor;
    }

    public void setTipoDocEmisor(String tipoDocEmisor) {
        this.tipoDocEmisor = tipoDocEmisor;
    }

    public String getNumDocEmisor() {
        return this.numDocEmisor;
    }

    public void setNumDocEmisor(String numDocEmisor) {
        this.numDocEmisor = numDocEmisor;
    }

    public String getNombreEmisor() {
        return this.nombreEmisor;
    }

    public void setNombreEmisor(String nombreEmisor) {
        this.nombreEmisor = nombreEmisor;
    }

    public String getFechaReferente() {
        return this.fechaReferente;
    }

    public void setFechaReferente(String fechaReferente) {
        this.fechaReferente = fechaReferente.substring(0, 10);
    }

    public String getTipoFormatoRepresentacionImpresa() {
        return this.tipoFormatoRepresentacionImpresa;
    }

    public void setTipoFormatoRepresentacionImpresa(String tipoFormatoRepresentacionImpresa) {
        this.tipoFormatoRepresentacionImpresa = tipoFormatoRepresentacionImpresa;
    }
}
