package com.sunat.indicadores;

public class Indicadores {

    Boolean indExportacion;
    Boolean indNoDomiciliados;
    Boolean indAnticipo;
    Boolean indDeduccionAnticipos;
    Boolean indServiciosHospedaje;
    Boolean indVentaItinerante;
    Boolean indTrasladoBienesConRepresentacionImpresa;
    Boolean indVentaArrozPilado;
    Boolean indComprobantePercepcion;
    Boolean indBienesTransferidosAmazonia;
    Boolean indServiciosPrestadosAmazonia;
    Boolean indContratosConstruccionEjecutadosAmazonia;
    Boolean indComprobanteContingencia;

    public void setIndExportacion(String indExportacion) {
        this.indExportacion = Boolean.valueOf(Boolean.parseBoolean(indExportacion));
    }

    public void setIndComprobanteContingencia(String indComprobanteContingencia) {
        this.indComprobanteContingencia = Boolean.valueOf(Boolean.parseBoolean(indComprobanteContingencia));
    }

    public void setIndNoDomiciliados(String indNoDomiciliados) {
        this.indNoDomiciliados = Boolean.valueOf(Boolean.parseBoolean(indNoDomiciliados));
    }

    public void setIndAnticipo(String indAnticipo) {
        this.indAnticipo = Boolean.valueOf(Boolean.parseBoolean(indAnticipo));
    }

    public void setIndDeduccionAnticipos(String indDeduccionAnticipos) {
        this.indDeduccionAnticipos = Boolean.valueOf(Boolean.parseBoolean(indDeduccionAnticipos));
    }

    public void setIndServiciosHospedaje(String indServiciosHospedaje) {
        this.indServiciosHospedaje = Boolean.valueOf(Boolean.parseBoolean(indServiciosHospedaje));
    }

    public void setIndVentaItinerante(String indVentaItinerante) {
        this.indVentaItinerante = Boolean.valueOf(Boolean.parseBoolean(indVentaItinerante));
    }

    public void setIndTrasladoBienesConRepresentacionImpresa(String indTrasladoBienesConRepresentacionImpresa) {
        this.indTrasladoBienesConRepresentacionImpresa = Boolean.valueOf(Boolean.parseBoolean(indTrasladoBienesConRepresentacionImpresa));
    }

    public void setIndVentaArrozPilado(String indVentaArrozPilado) {
        this.indVentaArrozPilado = Boolean.valueOf(Boolean.parseBoolean(indVentaArrozPilado));
    }

    public void setIndComprobantePercepcion(String indComprobantePercepcion) {
        this.indComprobantePercepcion = Boolean.valueOf(Boolean.parseBoolean(indComprobantePercepcion));
    }

    public void setIndBienesTransferidosAmazonia(String indBienesTransferidosAmazonia) {
        this.indBienesTransferidosAmazonia = Boolean.valueOf(Boolean.parseBoolean(indBienesTransferidosAmazonia));
    }

    public void setIndServiciosPrestadosAmazonia(String indServiciosPrestadosAmazonia) {
        this.indServiciosPrestadosAmazonia = Boolean.valueOf(Boolean.parseBoolean(indServiciosPrestadosAmazonia));
    }

    public void setIndContratosConstruccionEjecutadosAmazonia(String indContratosConstruccionEjecutadosAmazonia) {
        this.indContratosConstruccionEjecutadosAmazonia = Boolean.valueOf(Boolean.parseBoolean(indContratosConstruccionEjecutadosAmazonia));
    }

}
