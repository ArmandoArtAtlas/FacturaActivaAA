package com.sunat.impuesto;

import java.util.ArrayList;

public class Impuesto {

    public String codImpuesto;
    public Double montoImpuesto;
    public Double tasaImpuesto;

    public void setCodImpuesto(String codImpuesto) {
        this.codImpuesto = codImpuesto;
    }

    public void setMontoImpuesto(String montoImpuesto) {
        this.montoImpuesto = Double.valueOf(Double.parseDouble(montoImpuesto));
    }

    public void setTasaImpuesto(String tasaImpuesto) {
        this.tasaImpuesto = Double.valueOf(Double.parseDouble(tasaImpuesto));
    }

    public boolean setImpuesto(ArrayList<String> data) {
        this.codImpuesto = data.get(8);
        this.montoImpuesto = Double.valueOf(Double.parseDouble(data.get(10)));
        this.tasaImpuesto = Double.valueOf(Double.parseDouble(data.get(9)));
        return true;
    }

    public boolean setImpuestoNotaCredito(ArrayList<String> data) {
        this.codImpuesto = data.get(8);
        this.montoImpuesto = Double.valueOf(Double.parseDouble(data.get(10)));
        this.tasaImpuesto = Double.valueOf(Double.parseDouble(data.get(9)));
        return true;
    }

    public boolean setImpuestoNotaDebito(ArrayList<String> data) {
        this.codImpuesto = data.get(8);
        this.montoImpuesto = Double.valueOf(Double.parseDouble(data.get(10)));
        this.tasaImpuesto = Double.valueOf(Double.parseDouble(data.get(9)));
        return true;
    }
}
