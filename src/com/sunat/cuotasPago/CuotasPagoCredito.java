package com.sunat.cuotasPago;

public class CuotasPagoCredito {

    public  Integer nroCuota;
    public Double mntTotalCuota;
    public String fechaPagoCuota;
    public String tipoMonedaCuota;
  
    public void setNroCuota(String nroCuota) {
        this.nroCuota = Integer.valueOf(Integer.parseInt(nroCuota));
    }

    public void setMntTotalCuota(String mntTotalCuota) {
        this.mntTotalCuota = Double.valueOf(Double.parseDouble(mntTotalCuota));
    }

    public void setFechaPagoCuota(String fechaPagoCuota) {
        this.fechaPagoCuota = fechaPagoCuota.substring(0, 10);
    }

    
    public void setTipoMonedaCuota(String tipoMonedaCuota) {
        this.tipoMonedaCuota = tipoMonedaCuota;
        //this.tipoModenaCuota = tipoModenaCuota.equals("PEN") ? "PEN" : (tipoModenaCuota.equals("USD") ? "USD" : "");
    }

}
