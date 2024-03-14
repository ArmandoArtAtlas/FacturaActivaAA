package com.sunat.comunicacionBaja;

import java.util.ArrayList;

public class DetalleBaja {

    public String serie;
    public Integer correlativo;
    public String tipoDocumento;
    public String motivo;

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public void setCorrelativo(String correlativo) {
        this.correlativo = Integer.valueOf(Integer.parseInt(correlativo));
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public void setDetalle(ArrayList<String> data) {
        this.serie = data.get(0);
        this.correlativo = Integer.valueOf(Integer.parseInt(data.get(1)));
        this.tipoDocumento = data.get(2);
        this.motivo = data.get(3);
    }
}
