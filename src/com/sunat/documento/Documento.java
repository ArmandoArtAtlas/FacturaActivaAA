package com.sunat.documento;

import com.sunat.guia.Guia;
import java.util.ArrayList;

public class Documento {

    public String serie;
    public Integer correlativo;
    public String nombreEmisor;
    public String tipoDocEmisor;
    public String numDocEmisor;
    public String direccionCodigo;
    public String direccionOrigen;
    public String direccionUbigeo;
    public String nombreComercialEmisor;
    public String tipoDocReceptor;
    public String numDocReceptor;
    public String nombreReceptor;
    public String nombreComercialReceptor;
    public String tipoDocReceptorAsociado;
    public String numDocReceptorAsociado;
    public String nombreReceptorAsociado;
    public String direccionDestino;
    public String tipoMoneda;
    public String sustento;
    public String tipoMotivoNotaModificatoria;
    public Double mntNeto;
    public Double mntExe;
    public Double mntExo;
    public Double mntExportacion;
    public Double mntTotalIgv;
    public Double mntTotal;
    public Double mntTotalGrat;
    public String fechaVencimiento;
    public String glosaDocumento;
    public String codContrato;
    public String codCentroCosto;
    public Double tipoCambioDestino;
    public Double mntTotalIsc;
    public Double mntTotalOtros;
    public Double mntTotalOtrosCargos;
    public Double mntTotalAnticipos;
    public String tipoFormatoRepresentacionImpresa;
    public Double mntTotalImpuestos;
    public Double mntNetoCreditoPendientePago;
    public String tipoMonedaCreditoPendientePago;
    public Double mntValorVenta;
    public Double mntPrecioVenta;
    public Guia GuiaRemision;
    
    public void setSerie(String serie) {
        this.serie = serie;
    }

    public void setCorrelativo(String correlativo) {
        this.correlativo =Integer.valueOf(Integer.parseInt(correlativo));
    }

    public void setNombreEmisor(String nombreEmisor) {
        this.nombreEmisor = nombreEmisor;
    }

    public void setTipoDocEmisor(String tipoDocEmisor) {
        this.tipoDocEmisor = tipoDocEmisor;
    }

    public void setNumDocEmisor(String numDocEmisor) {
        this.numDocEmisor = numDocEmisor;
    }
    public void setDireccionCodigo(String direccionCodigo) {
        this.direccionCodigo = direccionCodigo;
    }
    public void setDireccionOrigen(String direccionOrigen) {
        this.direccionOrigen = direccionOrigen;
    }

    public void setDireccionUbigeo(String direccionUbigeo) {
        this.direccionUbigeo = direccionUbigeo;
    }

    public void setNombreComercialEmisor(String nombreComercialEmisor) {
        this.nombreComercialEmisor = nombreComercialEmisor;
    }

    public void setTipoDocReceptor(String tipoDocReceptor) {
        this.tipoDocReceptor = tipoDocReceptor;
    }

    public void setNumDocReceptor(String numDocReceptor) {
        this.numDocReceptor = numDocReceptor;
    }

    public void setNombreReceptor(String nombreReceptor) {
        this.nombreReceptor = nombreReceptor;
    }

    public void setNombreComercialReceptor(String nombreComercialReceptor) {
        this.nombreComercialReceptor = nombreComercialReceptor;
    }

    public void setTipoDocReceptorAsociado(String tipoDocReceptorAsociado) {
        this.tipoDocReceptorAsociado = tipoDocReceptorAsociado;
    }

    public void setNumDocReceptorAsociado(String numDocReceptorAsociado) {
        this.numDocReceptorAsociado = numDocReceptorAsociado;
    }

    public void setNombreReceptorAsociado(String nombreReceptorAsociado) {
        this.nombreReceptorAsociado = nombreReceptorAsociado;
    }

    public void setDireccionDestino(String direccionDestino) {
        this.direccionDestino = direccionDestino;
    }

    public void setTipoMoneda(String tipoMoneda) {
        this.tipoMoneda = tipoMoneda.equals("S/") ? "PEN" : (tipoMoneda.equals("USD") ? "USD" : "");
    }

    public void setSustento(String sustento) {
        this.sustento = sustento;
    }

    public void setTipoMotivoNotaModificatoria(String tipoMotivoNotaModificatoria) {
        this.tipoMotivoNotaModificatoria = tipoMotivoNotaModificatoria;
    }

    public void setMntNeto(String mntNeto) {
        this.mntNeto = Double.valueOf(Double.parseDouble(mntNeto));
    }

    public void setMntExe(String mntExe) {
        this.mntExe = Double.valueOf(Double.parseDouble(mntExe));
    }

    public void setMntExo(String mntExo) {
        this.mntExo = Double.valueOf(Double.parseDouble(mntExo));
    }

    public void setMntExportacion(String mntExportacion) {
        this.mntExportacion = Double.valueOf(Double.parseDouble(mntExportacion));
    }

    public void setMntTotalIgv(String mntTotalIgv) {
        this.mntTotalIgv = Double.valueOf(Double.parseDouble(mntTotalIgv));
    }

    public void setMntTotal(String mntTotal) {
        this.mntTotal = Double.valueOf(Double.parseDouble(mntTotal));
    }

    public void setMntTotalGrat(String mntTotalGrat) {
        this.mntTotalGrat = Double.valueOf(Double.parseDouble(mntTotalGrat));
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento.substring(0, 10);
    }

    public void setGlosaDocumento(String glosaDocumento) {
        this.glosaDocumento = glosaDocumento;
    }

    public void setCodContrato(String codContrato) {
        this.codContrato = codContrato;
    }

    public void setCodCentroCosto(String codCentroCosto) {
        this.codCentroCosto = codCentroCosto;
    }

    public void setTipoCambioDestino(String tipoCambioDestino) {
        this.tipoCambioDestino = Double.valueOf(Double.parseDouble(tipoCambioDestino));
    }

    public void setMntTotalIsc(String mntTotalIsc) {
        this.mntTotalIsc = Double.valueOf(Double.parseDouble(mntTotalIsc));
    }

    public void setMntTotalOtros(String mntTotalOtros) {
        this.mntTotalOtros = Double.valueOf(Double.parseDouble(mntTotalOtros));
    }

    public void setMntTotalOtrosCargos(String mntTotalOtrosCargos) {
        this.mntTotalOtrosCargos = Double.valueOf(Double.parseDouble(mntTotalOtrosCargos));
    }

    public void setMntTotalAnticipos(String mntTotalAnticipos) {
        this.mntTotalAnticipos = Double.valueOf(Double.parseDouble(mntTotalAnticipos));
    }

    public void setTipoFormatoRepresentacionImpresa(String tipoFormatoRepresentacionImpresa) {
        this.tipoFormatoRepresentacionImpresa = tipoFormatoRepresentacionImpresa;
    }

    public void setMntTotalImpuestos(String mntTotalImpuestos) {
        this.mntTotalImpuestos =Double.valueOf(Double.parseDouble(mntTotalImpuestos));
    }
    public void setMntNetoCreditoPendientePago(String mntNetoCreditoPendientePago) {
	if (!mntNetoCreditoPendientePago.isEmpty()){
        this.mntNetoCreditoPendientePago = Double.valueOf(Double.parseDouble(mntNetoCreditoPendientePago));
        } 
   }

    public void setTipoMonedaCreditoPendientePago(String tipoMonedaCreditoPendientePago) {
	if (!tipoMonedaCreditoPendientePago.isEmpty()){
        this.tipoMonedaCreditoPendientePago = tipoMonedaCreditoPendientePago;
        }
    }

    public void setMntValorVenta (String mntValorVenta){
    
        this.mntValorVenta=Double.parseDouble(mntValorVenta);
    }
    
    public void setMntPrecioVenta (String mntPrecioVenta){
         
        this.mntPrecioVenta=Double.parseDouble(mntPrecioVenta);
    }

    public void setGuiaRemision(Guia GuiaRemision) {
        this.GuiaRemision = GuiaRemision;
    }
    

    public boolean setData(ArrayList<String> data) {
        try {
            this.serie = data.get(0);
            this.correlativo = Integer.valueOf(Integer.parseInt(data.get(1)));
            this.nombreEmisor = data.get(2);
            this.tipoDocEmisor = data.get(3);
            this.numDocEmisor = data.get(4);
            this.direccionOrigen = data.get(5);
            this.direccionUbigeo = data.get(6);
            this.nombreComercialEmisor = data.get(7);
            this.tipoDocReceptor = data.get(8);
            this.numDocReceptor = data.get(9);
            this.nombreReceptor = data.get(10);
            this.tipoMoneda = ((String) data.get(11)).equals("S/") ? "PEN" : (((String) data.get(11)).equals("USD") ? "USD" : "");
            this.mntNeto = Double.valueOf(Double.parseDouble(data.get(12)));
            this.mntTotalIgv = Double.valueOf(Double.parseDouble(data.get(13)));
            this.mntTotal = Double.valueOf(Double.parseDouble(data.get(14)));
            this.direccionDestino = data.get(20);
            this.mntExo = Double.valueOf(Double.parseDouble(data.get(21)));
            this.tipoCambioDestino = Double.valueOf(Double.parseDouble(data.get(22)));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean setDataNotaCredito(ArrayList<String> data) {
        try {
            this.serie = data.get(0);
            this.correlativo = Integer.valueOf(Integer.parseInt(data.get(1)));
            this.nombreEmisor = data.get(2);
            this.tipoDocEmisor = data.get(3);
            this.numDocEmisor = data.get(4);
            this.direccionOrigen = data.get(5);
            this.direccionUbigeo = data.get(6);
            this.nombreComercialEmisor = data.get(7);
            this.tipoDocReceptor = data.get(8);
            this.numDocReceptor = data.get(9);
            this.nombreReceptor = data.get(10);
            this.tipoMoneda = ((String) data.get(11)).equals("S/") ? "PEN" : (((String) data.get(11)).equals("USD") ? "USD" : "");
            this.sustento = data.get(17);
            this.tipoMotivoNotaModificatoria = data.get(18);
            this.mntNeto = Double.valueOf(Double.parseDouble(data.get(12)));
            this.mntTotalIgv = Double.valueOf(Double.parseDouble(data.get(13)));
            this.mntTotal = Double.valueOf(Double.parseDouble(data.get(14)));
            this.direccionDestino = data.get(19);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean setDataNotaDebito(ArrayList<String> data) {
        try {
            this.serie = data.get(0);
            this.correlativo = Integer.valueOf(Integer.parseInt(data.get(1)));
            this.nombreEmisor = data.get(2);
            this.tipoDocEmisor = data.get(3);
            this.numDocEmisor = data.get(4);
            this.direccionOrigen = data.get(5);
            this.direccionUbigeo = data.get(6);
            this.nombreComercialEmisor = data.get(7);
            this.tipoDocReceptor = data.get(8);
            this.numDocReceptor = data.get(9);
            this.nombreReceptor = data.get(10);
            this.tipoMoneda = ((String) data.get(11)).equals("S/") ? "PEN" : "";
            this.sustento = data.get(17);
            this.tipoMotivoNotaModificatoria = data.get(18);
            this.mntNeto = Double.valueOf(Double.parseDouble(data.get(12)));
            this.mntTotalIgv = Double.valueOf(Double.parseDouble(data.get(13)));
            this.mntTotal = Double.valueOf(Double.parseDouble(data.get(14)));
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
