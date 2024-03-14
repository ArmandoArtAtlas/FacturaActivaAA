/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sunat.guia;

/**
 *
 * @author oscarapaza
 */
public class Guia {

    private String horaEmision;
    private String codigoMotivoTraslado;
    private String motivoTraslado;
    private String fechaInicioTraslado;
    private String direccionOrigen;
    private String direccionOrigenUbigeo;
    private String direccionOrigenDistrito;
    private String direccionOrigenProvincia;
    private String direccionOrigenDepartamento;
    private String direccionDestino;
    private String direccionDestinoUbigeo;
    private String direccionDestinoDistrito;
    private String direccionDestinoProvincia;
    private String direccionDestinoDepartamento;
    private String codigoModalidadTransporte;
    private Double totalPesoBrutoTransportado;
    private String unidadMedidaPesoTransportado;
    private Boolean esTransbordoProgramado;
    private String tipoDocumentoChofer;
    private String numeroDocumentoChofer;
    private String nombreChofer;
    private String placaTransporte;
    private String nombreTransportista;
    private String numeroDocumentoTransportista;
    private String tipoDocumentoTransportista;

    private String tipoDocumentoProveedor;
    private String numeroDocumentoProveedor;
    private String nombreProveedor;

    //private String cantidadUnitariaTransportado;
    //private String mtc; No Habilitado
    public String getHoraEmision() {
        return horaEmision;
    }

    public void setHoraEmision(String horaEmision) {
        this.horaEmision = horaEmision;
    }

    public String getCodigoMotivoTraslado() {
        return codigoMotivoTraslado;
    }

    public void setCodigoMotivoTraslado(String codigoMotivoTraslado) {
        this.codigoMotivoTraslado = codigoMotivoTraslado;
    }

    public String getMotivoTraslado() {
        return motivoTraslado;
    }

    public void setMotivoTraslado(String motivoTraslado) {
        this.motivoTraslado = motivoTraslado;
    }

    public String getFechaInicioTraslado() {
        return fechaInicioTraslado;
    }

    public void setFechaInicioTraslado(String fechaInicioTraslado) {
        this.fechaInicioTraslado = fechaInicioTraslado;
    }

    public String getDireccionOrigen() {
        return direccionOrigen;
    }

    public void setDireccionOrigen(String direccionOrigen) {
        this.direccionOrigen = direccionOrigen;
    }

    public String getDireccionOrigenUbigeo() {
        return direccionOrigenUbigeo;
    }

    public void setDireccionOrigenUbigeo(String direccionOrigenUbigeo) {
        this.direccionOrigenUbigeo = direccionOrigenUbigeo;
    }

    public String getDireccionOrigenDistrito() {
        return direccionOrigenDistrito;
    }

    public void setDireccionOrigenDistrito(String direccionOrigenDistrito) {
        this.direccionOrigenDistrito = direccionOrigenDistrito;
    }

    public String getDireccionOrigenProvincia() {
        return direccionOrigenProvincia;
    }

    public void setDireccionOrigenProvincia(String direccionOrigenProvincia) {
        this.direccionOrigenProvincia = direccionOrigenProvincia;
    }

    public String getDireccionOrigenDepartamento() {
        return direccionOrigenDepartamento;
    }

    public void setDireccionOrigenDepartamento(String direccionOrigenDepartamento) {
        this.direccionOrigenDepartamento = direccionOrigenDepartamento;
    }

    public String getDireccionDestino() {
        return direccionDestino;
    }

    public void setDireccionDestino(String direccionDestino) {
        this.direccionDestino = direccionDestino;
    }

    public String getDireccionDestinoUbigeo() {
        return direccionDestinoUbigeo;
    }

    public void setDireccionDestinoUbigeo(String direccionDestinoUbigeo) {
        this.direccionDestinoUbigeo = direccionDestinoUbigeo;
    }

    public String getDireccionDestinoDistrito() {
        return direccionDestinoDistrito;
    }

    public void setDireccionDestinoDistrito(String direccionDestinoDistrito) {
        this.direccionDestinoDistrito = direccionDestinoDistrito;
    }

    public String getDireccionDestinoProvincia() {
        return direccionDestinoProvincia;
    }

    public void setDireccionDestinoProvincia(String direccionDestinoProvincia) {
        this.direccionDestinoProvincia = direccionDestinoProvincia;
    }

    public String getDireccionDestinoDepartamento() {
        return direccionDestinoDepartamento;
    }

    public void setDireccionDestinoDepartamento(String direccionDestinoDepartamento) {
        this.direccionDestinoDepartamento = direccionDestinoDepartamento;
    }

    public String getCodigoModalidadTransporte() {
        return codigoModalidadTransporte;
    }

    public void setCodigoModalidadTransporte(String codigoModalidadTransporte) {
        this.codigoModalidadTransporte = codigoModalidadTransporte;
    }

    public Double getTotalPesoBrutoTransportado() {
        return totalPesoBrutoTransportado;
    }

    public void setTotalPesoBrutoTransportado(String totalPesoBrutoTransportado) {
        this.totalPesoBrutoTransportado = Double.parseDouble(totalPesoBrutoTransportado);
    }

    public String getUnidadMedidaPesoTransportado() {
        return unidadMedidaPesoTransportado;
    }

    public void setUnidadMedidaPesoTransportado(String unidadMedidaPesoTransportado) {
        this.unidadMedidaPesoTransportado = unidadMedidaPesoTransportado;
    }

    public Boolean getEsTransbordoProgramado() {
        return esTransbordoProgramado;
    }

    public void setEsTransbordoProgramado(String esTransbordoProgramado) {
        this.esTransbordoProgramado = Boolean.parseBoolean(esTransbordoProgramado);
    }

    public String getTipoDocumentoChofer() {
        return tipoDocumentoChofer;
    }

    public void setTipoDocumentoChofer(String tipoDocumentoChofer) {
        this.tipoDocumentoChofer = tipoDocumentoChofer;
    }

    public String getNumeroDocumentoChofer() {
        return numeroDocumentoChofer;
    }

    public void setNumeroDocumentoChofer(String numeroDocumentoChofer) {
        this.numeroDocumentoChofer = numeroDocumentoChofer;
    }

    public String getNombreChofer() {
        return nombreChofer;
    }

    public void setNombreChofer(String nombreChofer) {
        this.nombreChofer = nombreChofer;
    }

    public String getPlacaTransporte() {
        return placaTransporte;
    }

    public void setPlacaTransporte(String placaTransporte) {
        this.placaTransporte = placaTransporte;
    }
    
    public String getNombreTransportista() {
        return nombreTransportista;
    }
    
    
    public void setNombreTransportista(String nombreTransportista) {
        this.nombreTransportista = nombreTransportista;
    }
    
    public String getNumeroDocumentoTransportista() {
        return numeroDocumentoTransportista;
    }
    
    
    public void setNumeroDocumentoTransportista(String numeroDocumentoTransportista) {
        this.numeroDocumentoTransportista = numeroDocumentoTransportista;
    }
    
    public String getTipoDocumentoTransportista() {
        return tipoDocumentoTransportista;
    }
    
    
    public void setTipoDocumentoTransportista(String tipoDocumentoTransportista) {
        this.tipoDocumentoTransportista = tipoDocumentoTransportista;
    }
    
    /*
    public String getCantidadUnitariaTransportado() {
        return cantidadUnitariaTransportado;
    }
     */
 /*
    public void setCantidadUnitariaTransportado(String cantidadUnitariaTransportado) {
        this.cantidadUnitariaTransportado = cantidadUnitariaTransportado ;
    }
     */
    public void setTipoDocumentoProveedor(String tipoDocumentoProveedor) {
        //if (this.codigoMotivoTraslado == "02") {
            this.tipoDocumentoProveedor = tipoDocumentoProveedor;
        //}

    }

    public void setNumeroDocumentoProveedor(String numeroDocumentoProveedor) {
        //if (this.codigoMotivoTraslado == "02") {
            this.numeroDocumentoProveedor = numeroDocumentoProveedor;
        //}

    }

    public void setNombreProveedor(String nombreProveedor) {
        //if (this.codigoMotivoTraslado == "02") {
            this.nombreProveedor = nombreProveedor;
        //}

    }

    public String getTipoDocumentoProveedor() {

        //if (this.codigoMotivoTraslado == "02") {
            return tipoDocumentoProveedor;
            //this.nombreProveedor = nombreProveedor;
        //}else return null;
        
    }

    public String getNumeroDocumentoProveedor() {
        //if (this.codigoMotivoTraslado == "02") {
            return numeroDocumentoProveedor;
            //this.nombreProveedor = nombreProveedor;
        //}else return null;
        
    }

    public String getNombreProveedor() {
        //if (this.codigoMotivoTraslado == "02") {
            return nombreProveedor;
            //this.nombreProveedor = nombreProveedor;
        //}else return null;
        
    }
    
    
    

    @Override
    public String toString() {
        return "Guia{" + "horaEmision=" + horaEmision + ", "
                + "codigoMotivoTraslado=" + codigoMotivoTraslado
                + ", motivoTraslado=" + motivoTraslado
                + ", fechaInicioTraslado=" + fechaInicioTraslado
                + ", direccionOrigen=" + direccionOrigen
                + ", direccionOrigenUbigeo=" + direccionOrigenUbigeo 
                + ", direccionOrigenDistrito=" + direccionOrigenDistrito 
                + ", direccionOrigenProvincia=" + direccionOrigenProvincia 
                + ", direccionOrigenDepartamento=" + direccionOrigenDepartamento
                + ", direccionDestino=" + direccionDestino 
                + ", direccionDestinoUbigeo=" + direccionDestinoUbigeo
                + ", direccionDestinoDistrito=" + direccionDestinoDistrito 
                + ", direccionDestinoProvincia=" + direccionDestinoProvincia 
                + ", direccionDestinoDepartamento=" + direccionDestinoDepartamento 
                + ", codigoModalidadTransporte=" + codigoModalidadTransporte 
                + ", totalPesoBrutoTransportado=" + totalPesoBrutoTransportado 
                + ", unidadMedidaPesoTransportado=" + unidadMedidaPesoTransportado 
                + ", esTransbordoProgramado=" + esTransbordoProgramado 
                + ", tipoDocumentoChofer=" + tipoDocumentoChofer 
                + ", numeroDocumentoChofer=" + numeroDocumentoChofer 
                + ", nombreChofer=" + nombreChofer 
                + ", placaTransporte=" + placaTransporte 
                + ", nombreTransportista=" + nombreTransportista 
                + ", numeroDocumentoTransportista=" + numeroDocumentoTransportista 
                + ", tipoDocumentoTransportista=" + tipoDocumentoTransportista 
                + ", tipoDocumentoProveedor=" + tipoDocumentoProveedor 
                + ", numeroDocumentoProveedor=" + numeroDocumentoProveedor 
                + ", nombreProveedor=" + nombreProveedor 
                + '}';
    }

}
