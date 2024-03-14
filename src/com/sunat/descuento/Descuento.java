package com.sunat.descuento;

public class Descuento {

    public String codDescuentoGlobal;
    public Double baseDescuentoGlobal;
    public Double tasaDescuentoGlobal;
    public Double mntDescuentoGlobal;
    public Double mntTotalDescuentos;

    

    
    public void setTasaDescuentoGlobal(String tasaDescuentoGlobal) {
        this.tasaDescuentoGlobal = Double.valueOf(Double.parseDouble(tasaDescuentoGlobal)) ;
        //this.tasaDescuentoGlobal = 0.99;     }
    }
    
    public String getCodDescuentoGlobal() {
        return codDescuentoGlobal;
    }
    
    public void setCodDescuentoGlobal(String codDescuentoGlobal) {
        this.codDescuentoGlobal = codDescuentoGlobal;
    }

    public Double getBaseDescuentoGlobal() {
        return baseDescuentoGlobal;
    }

    public void setBaseDescuentoGlobal(String baseDescuentoGlobal) {
        this.baseDescuentoGlobal = Double.parseDouble(baseDescuentoGlobal);
    }

    public Double getMntDescuentoGlobal() {
        return this.mntDescuentoGlobal;
    }

    public void setMntDescuentoGlobal(String mntDescuentoGlobal) {
        this.mntDescuentoGlobal = Double.parseDouble(mntDescuentoGlobal);
    }

    public Double getMntTotalDescuentos() {
        return this.mntTotalDescuentos;
    }

    public void setMntTotalDescuentos(String mntTotalDescuentos) {
        this.mntTotalDescuentos = Double.parseDouble(mntTotalDescuentos);
    }
}
