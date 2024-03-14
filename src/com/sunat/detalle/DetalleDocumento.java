package com.sunat.detalle;

import java.util.ArrayList;

public class DetalleDocumento {

    public Double cantidadItem;
    public String unidadMedidaItem;
    public String codItem;
    public String nombreItem;
    public Double precioItem;
    public Double precioItemSinIgv;
    public Double montoItem;
    public Double descuentoMonto;
    public String codAfectacionIgv;
    public Double tasaIgv;
    public Double montoIgv;
    public String codSistemaCalculoIsc;
    public Double montoIsc;
    public Double tasaIsc;
    public Double precioItemReferencia;
    public String idOperacion;
    public Double cantidadIcbper;
    public Integer codIcbper;
    public Double tasaIcbper;
    public Double montoIcbper;
    public Double montoImpuestos;
    public String hts;
    public String partidaArancelaria;

    public void setCantidadItem(String cantidadItem) {
        this.cantidadItem = Double.valueOf(Double.parseDouble(cantidadItem));
    }

    public void setUnidadMedidaItem(String unidadMedidaItem) {
        this.unidadMedidaItem = unidadMedidaItem;
    }

    public void setCodItem(String codItem) {
        this.codItem = codItem;
    }

    public void setNombreItem(String nombreItem) {
        this.nombreItem = nombreItem;

    }

    public void setPrecioItem(String precioItem) {
        this.precioItem = Double.valueOf(Double.parseDouble(precioItem));
    }

    public void setPrecioItemSinIgv(String precioItemSinIgv) {
        this.precioItemSinIgv = Double.valueOf(Double.parseDouble(precioItemSinIgv));
    }

    public void setMontoItem(String montoItem) {
        this.montoItem = Double.valueOf(Double.parseDouble(montoItem));
    }

    public void setDescuentoMonto(String descuentoMonto) {
        this.descuentoMonto = Double.valueOf(Double.parseDouble(descuentoMonto));
    }

    public void setCodAfectacionIgv(String codAfectacionIgv) {
        this.codAfectacionIgv = codAfectacionIgv;
    }

    public void setTasaIgv(String tasaIgv) {
        this.tasaIgv = Double.valueOf(Double.parseDouble(tasaIgv));
    }

    public void setMontoIgv(String montoIgv) {
        this.montoIgv = Double.valueOf(Double.parseDouble(montoIgv));
    }

    public void setCodSistemaCalculoIsc(String codSistemaCalculoIsc) {
        this.codSistemaCalculoIsc = codSistemaCalculoIsc;
    }

    public void setMontoIsc(String montoIsc) {
        this.montoIsc = Double.valueOf(Double.parseDouble(montoIsc));
    }

    public void setTasaIsc(String tasaIsc) {
        this.tasaIsc = Double.valueOf(Double.parseDouble(tasaIsc));
    }

    public void setPrecioItemReferencia(String precioItemReferencia) {
        this.precioItemReferencia = Double.valueOf(Double.parseDouble(precioItemReferencia));
    }

    public void setIdOperacion(String idOperacion) {
        this.idOperacion = idOperacion;
    }

    public void setCantidadIcbper(String cantidadIcbper) {
        this.cantidadIcbper = Double.valueOf(Double.parseDouble(cantidadIcbper));
    }

    public void setCodIcbper(String codIcbper) {
        this.codIcbper = Integer.valueOf(Integer.parseInt(codIcbper));
    }

    public void setTasaIcbper(String tasaIcbper) {
        this.tasaIcbper = Double.valueOf(Double.parseDouble(tasaIcbper));
    }

    public void setMontoIcbper(String montoIcbper) {
        this.montoIcbper = Double.valueOf(Double.parseDouble(montoIcbper));
    }

    public void setMontoImpuestos(String montoImpuestos) {
        this.montoImpuestos = Double.valueOf(Double.parseDouble(montoImpuestos));
    }

    public void setHts(String hts) {
        this.hts = hts;
        if (this.nombreItem != null && this.partidaArancelaria != null) {
            this.nombreItem += "|" + this.partidaArancelaria + "|" + hts;
            this.partidaArancelaria = null;
            this.hts = null;
        }
    }

    public void setPartidaArancelaria(String arancel) {
        this.partidaArancelaria = arancel;
        if (this.nombreItem != null && this.hts != null) {
            this.nombreItem += "|" + arancel + "|" + this.hts;
            this.hts = null;
            this.partidaArancelaria = null;
        }
    }

    public boolean setDetalle(ArrayList<String> data) {
        this.cantidadItem = Double.valueOf(Double.parseDouble(data.get(0)));
        this.unidadMedidaItem = ((String) data.get(1)).equals("07") ? "NIU" : "ZZ";
        this.codItem = data.get(2);
        this.nombreItem = data.get(3);
        this.precioItem = Double.valueOf(Double.parseDouble(data.get(4)));
        this.precioItemSinIgv = Double.valueOf(Double.parseDouble(data.get(5)));
        this.montoItem = Double.valueOf(Double.parseDouble(data.get(6)));
        this.codAfectacionIgv = data.get(7);

        this.tasaIgv = Double.valueOf(Double.parseDouble(data.get(9)));
        this.montoIgv = Double.valueOf(Double.parseDouble(data.get(10)));
        this.idOperacion = data.get(11);
        return true;
    }

    public boolean setDetalleNotaCredito(ArrayList<String> data) {
        this.cantidadItem = Double.valueOf(Double.parseDouble(data.get(0)));
        this.unidadMedidaItem = ((String) data.get(1)).equals("07") ? "NIU" : "ZZ";
        this.codItem = data.get(2);
        this.nombreItem = data.get(3);
        this.precioItem = Double.valueOf(Double.parseDouble(data.get(4)));
        this.precioItemSinIgv = Double.valueOf(Double.parseDouble(data.get(5)));
        this.montoItem = Double.valueOf(Double.parseDouble(data.get(6)));
        this.codAfectacionIgv = data.get(7);

        this.tasaIgv = Double.valueOf(Double.parseDouble(data.get(9)));
        this.montoIgv = Double.valueOf(Double.parseDouble(data.get(10)));
        this.idOperacion = data.get(11);
        return true;
    }

    public boolean setDetalleNotaDebito(ArrayList<String> data) {
        this.cantidadItem = Double.valueOf(Double.parseDouble(data.get(0)));
        this.unidadMedidaItem = ((String) data.get(1)).equals("07") ? "NIU" : "ZZ";
        this.codItem = data.get(2);
        this.nombreItem = data.get(3);
        this.precioItem = Double.valueOf(Double.parseDouble(data.get(4)));
        this.precioItemSinIgv = Double.valueOf(Double.parseDouble(data.get(5)));
        this.montoItem = Double.valueOf(Double.parseDouble(data.get(6)));
        this.codAfectacionIgv = data.get(7);

        this.tasaIgv = Double.valueOf(Double.parseDouble(data.get(9)));
        this.montoIgv = Double.valueOf(Double.parseDouble(data.get(10)));
        this.idOperacion = data.get(11);
        return true;
    }
}
