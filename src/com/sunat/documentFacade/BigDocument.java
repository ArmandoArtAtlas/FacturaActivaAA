package com.sunat.documentFacade;

import com.sunat.anticipo.Anticipo;
import com.sunat.datosAdicionales.DatosAdicionales;
import com.sunat.descuento.Descuento;
import com.sunat.detalle.DetalleDocumento;
import com.sunat.cuotasPago.CuotasPagoCredito;
import com.sunat.documento.Documento;
import com.sunat.guia.Guia;
import com.sunat.impuesto.Impuesto;
import com.sunat.indicadores.Indicador;
import com.sunat.indicadores.Indicadores;
import com.sunat.referencia.Referencia;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BigDocument {

    public String tipoDocumento;
    public String fechaEmision;
    public String idTransaccion;
    public String correoReceptor;
    public Documento documento;
    public Guia guia;
    public ArrayList<CuotasPagoCredito> cuotasPagoCredito;
    public ArrayList<Impuesto> impuesto;
    public ArrayList<DetalleDocumento> detalle;
    public Descuento descuento;
    public ArrayList<Referencia> referencia;
    public Indicadores indicadores;
    public Indicador indicador;
    public ArrayList<Anticipo> anticipo;
    public DatosAdicionales datosAdicionales;

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public void setFechaEmision(String fechaEmision) {
        fechaEmision = fechaEmision.substring(0, 10);
        this.fechaEmision = fechaEmision;
    }

    public void setIdTransaccion(String idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public void setCorreoReceptor(String correoReceptor) {
        this.correoReceptor = correoReceptor;
    }

    public boolean setDocumento(HashMap<String, String> data) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        this.documento = new Documento();

        Class<?>[] paramTypes = new Class[]{String.class};

        Class<? extends Documento> objetoDocumento = (Class) this.documento.getClass();

        for (Map.Entry<String, String> entry : data.entrySet()) {

            try {
                Method method = objetoDocumento.getDeclaredMethod("set" + capitalize(entry.getKey()), paramTypes);
                method.invoke(this.documento, new Object[]{entry.getValue()});
            } catch (NoSuchMethodException e) {
                System.err.println("No Method");
            }
        }

        return true;
    }

    public void setImpuesto(ArrayList<HashMap<String, String>> data) {
        if (data.size() == 0) {
            return;
        }
        this.impuesto = new ArrayList<>();

        Impuesto imp = new Impuesto();

        imp.setCodImpuesto((String) ((HashMap) data.get(0)).get("codImpuesto"));
        imp.setMontoImpuesto((String) ((HashMap) data.get(0)).get("montoImpuesto"));
        imp.setMontoImpuesto((String) ((HashMap) data.get(0)).get("baseImpuesto"));
        imp.setTasaImpuesto((String) ((HashMap) data.get(0)).get("tasaImpuesto"));
        this.impuesto.add(imp);

        if (data.size() > 1) {
            Impuesto imp2 = new Impuesto();

            imp2.setCodImpuesto((String) ((HashMap) data.get(1)).get("codImpuesto"));
            imp2.setMontoImpuesto((String) ((HashMap) data.get(1)).get("montoImpuesto"));
            imp2.setMontoImpuesto((String) ((HashMap) data.get(1)).get("baseImpuesto"));
            imp2.setTasaImpuesto((String) ((HashMap) data.get(1)).get("tasaImpuesto"));
            this.impuesto.add(imp2);
        }
    }

    public void setDetalle(ArrayList<HashMap<String, String>> detalle) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        this.detalle = new ArrayList<>();
        ArrayList<DetalleDocumento> det = this.detalle;
        Class<?>[] paramTypes = new Class[]{String.class};
        
        for (int i = 0; i < detalle.size(); i++) {
            DetalleDocumento dtTemp = new DetalleDocumento();

            Class<? extends DetalleDocumento> objetoDetalle = (Class) dtTemp.getClass();
            for (Map.Entry<String, String> entry : (Iterable<Map.Entry<String, String>>) ((HashMap) detalle.get(i)).entrySet()) {
                try {
                    Method method = objetoDetalle.getDeclaredMethod("set" + capitalize(entry.getKey()), paramTypes);

                    method.invoke(dtTemp, new Object[]{entry.getValue()});
                } catch (NoSuchMethodException e) {
                    System.err.println("No Method");
                }
            }

            det.add(dtTemp);
        }
        
       //System.out.println(this.detalle.get(0).nombreItem);
    }

    public void setCuota(ArrayList<HashMap<String, String>> cuotasPagoCredito) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        this.cuotasPagoCredito = new ArrayList<>();
        ArrayList<CuotasPagoCredito> cuo = this.cuotasPagoCredito;
        Class<?>[] paramTypes = new Class[]{String.class};
        for (int i = 0; i < cuotasPagoCredito.size(); i++) {
            CuotasPagoCredito cuoTemp = new CuotasPagoCredito();

            Class<? extends CuotasPagoCredito> objetoCuota = (Class) cuoTemp.getClass();
            for (Map.Entry<String, String> entry : (Iterable<Map.Entry<String, String>>) ((HashMap) cuotasPagoCredito.get(i)).entrySet()) {
                try {
                    Method method = objetoCuota.getDeclaredMethod("set" + capitalize(entry.getKey()), paramTypes);

                    method.invoke(cuoTemp, new Object[]{entry.getValue()});
                } catch (NoSuchMethodException e) {
                    System.err.println("No Method");
                }
            }

            cuo.add(cuoTemp);
        }
    }

    public void setIndicadores(HashMap<String, String> data) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (data.isEmpty()) {
            return;
        }
        this.indicadores = new Indicadores();

        Class<?>[] paramTypes = new Class[]{String.class};

        Class<? extends Indicadores> objetoIndicadores = (Class) this.indicadores.getClass();

        for (Map.Entry<String, String> entry : data.entrySet()) {

            try {
                Method method = objetoIndicadores.getDeclaredMethod("set" + capitalize(entry.getKey()), paramTypes);
                method.invoke(this.indicadores, new Object[]{entry.getValue()});
            } catch (NoSuchMethodException noSuchMethodException) {
            }
        }
    }

    /*
    public void setDescuento(ArrayList<ArrayList<String>> descuento) {
        if (descuento.size() == 0) {
            return;
        }
        this.descuento = new Descuento();
        System.out.println(descuento.size());
        System.out.println(descuento);
        this.descuento.setMntDescuentoGlobal(Double.valueOf(Double.parseDouble(((ArrayList<String>) descuento.get(0)).get(3))));
        this.descuento.setMntTotalDescuentos(Double.valueOf(Double.parseDouble(((ArrayList<String>) descuento.get(0)).get(3))));
    }
     */
    public void setDescuento(HashMap<String, String> data) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (data.isEmpty()) {
            return;
        }
        this.descuento = new Descuento();

        Class<?>[] paramTypes = new Class[]{String.class};

        Class<? extends Descuento> objetoDescuento = (Class) this.descuento.getClass();

        for (Map.Entry<String, String> entry : data.entrySet()) {
            try {
                Method method = objetoDescuento.getDeclaredMethod("set" + capitalize(entry.getKey()), paramTypes);
                method.invoke(this.descuento, new Object[]{entry.getValue()});
            } catch (NoSuchMethodException noSuchMethodException) {
            }
        }
    }

    public void setReferencia(Referencia referencia) {
    }

    /*
    public void setAnticipo(Anticipo anticipo) {
        
    }
     */
    public void setAnticipo(ArrayList<HashMap<String, String>> anticipo) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        this.anticipo = new ArrayList<>();
        ArrayList<Anticipo> ant = this.anticipo;
        Class<?>[] paramTypes = new Class[]{String.class};
        for (int i = 0; i < anticipo.size(); i++) {
            Anticipo antTemp = new Anticipo();
            Class<? extends Anticipo> objetoAnticipo = (Class) antTemp.getClass();
            for (Map.Entry<String, String> entry : (Iterable<Map.Entry<String, String>>) ((HashMap) anticipo.get(i)).entrySet()) {
                try {
                    Method method = objetoAnticipo.getDeclaredMethod("set" + capitalize(entry.getKey()), paramTypes);

                    method.invoke(antTemp, new Object[]{entry.getValue()});
                } catch (NoSuchMethodException e) {
                    System.err.println("No Method");
                }
            }
            ant.add(antTemp);
        }
    }

    public void setDatosAdicionales(HashMap<String, String> data) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (data.isEmpty()) {
            return;
        }
        this.datosAdicionales = new DatosAdicionales();

        Class<?>[] paramTypes = new Class[]{String.class};

        Class<? extends DatosAdicionales> objetoDocumento = (Class) this.datosAdicionales.getClass();

        for (Map.Entry<String, String> entry : data.entrySet()) {

            try {
                Method method = objetoDocumento.getDeclaredMethod("set" + capitalize(entry.getKey()), paramTypes);
                method.invoke(this.datosAdicionales, new Object[]{entry.getValue()});
            } catch (NoSuchMethodException noSuchMethodException) {
            }
        }
    }

    public boolean setDocumentoNotaCredito(HashMap<String, String> data) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        this.documento = new Documento();
        Class<?>[] paramTypes = new Class[]{String.class};

        Class<? extends Documento> objetoDocumento = (Class) this.documento.getClass();

        for (Map.Entry<String, String> entry : data.entrySet()) {

            try {
                Method method = objetoDocumento.getDeclaredMethod("set" + capitalize(entry.getKey()), paramTypes);
                method.invoke(this.documento, new Object[]{entry.getValue()});
            } catch (NoSuchMethodException e) {
                System.err.println("No Method");
            }
        }

        return true;
    }

    public void setDetalleNotaCredito(ArrayList<HashMap<String, String>> detalle) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        this.detalle = new ArrayList<>();
        ArrayList<DetalleDocumento> det = this.detalle;
        Class<?>[] paramTypes = new Class[]{String.class};
        for (int i = 0; i < detalle.size(); i++) {
            DetalleDocumento dtTemp = new DetalleDocumento();

            Class<? extends DetalleDocumento> objetoDetalle = (Class) dtTemp.getClass();
            for (Map.Entry<String, String> entry : (Iterable<Map.Entry<String, String>>) ((HashMap) detalle.get(i)).entrySet()) {
                try {
                    Method method = objetoDetalle.getDeclaredMethod("set" + capitalize(entry.getKey()), paramTypes);
                    method.invoke(dtTemp, new Object[]{entry.getValue()});
                } catch (NoSuchMethodException e) {
                    System.err.println("No Method");
                }
            }

            det.add(dtTemp);
        }
    }

    public void setCuotaNotaCredito(ArrayList<HashMap<String, String>> cuotasPagoCredito) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        this.cuotasPagoCredito = new ArrayList<>();
        ArrayList<CuotasPagoCredito> cuo = this.cuotasPagoCredito;
        Class<?>[] paramTypes = new Class[]{String.class};
        for (int i = 0; i < cuotasPagoCredito.size(); i++) {
            CuotasPagoCredito cuoTemp = new CuotasPagoCredito();

            Class<? extends CuotasPagoCredito> objetoCuota = (Class) cuoTemp.getClass();
            for (Map.Entry<String, String> entry : (Iterable<Map.Entry<String, String>>) ((HashMap) cuotasPagoCredito.get(i)).entrySet()) {
                try {
                    Method method = objetoCuota.getDeclaredMethod("set" + capitalize(entry.getKey()), paramTypes);
                    method.invoke(cuoTemp, new Object[]{entry.getValue()});
                } catch (NoSuchMethodException e) {
                    System.err.println("No Method");
                }
            }

            cuo.add(cuoTemp);
        }
    }

    public void setImpuestoNotaCredito(ArrayList<HashMap<String, String>> data) {
        if (data.size() == 0) {
            return;
        }
        this.impuesto = new ArrayList<>();
        System.out.println(data);
        Impuesto imp = new Impuesto();
        imp.setCodImpuesto((String) ((HashMap) data.get(0)).get("codImpuesto"));
        imp.setMontoImpuesto((String) ((HashMap) data.get(0)).get("montoImpuesto"));
        imp.setMontoImpuesto((String) ((HashMap) data.get(0)).get("baseImpuesto"));
        imp.setTasaImpuesto((String) ((HashMap) data.get(0)).get("tasaImpuesto"));
        this.impuesto.add(imp);

        if (data.size() > 1) {
            Impuesto imp2 = new Impuesto();

            imp2.setCodImpuesto((String) ((HashMap) data.get(1)).get("codImpuesto"));
            imp2.setMontoImpuesto((String) ((HashMap) data.get(1)).get("montoImpuesto"));
            imp2.setMontoImpuesto((String) ((HashMap) data.get(1)).get("baseImpuesto"));
            imp2.setTasaImpuesto((String) ((HashMap) data.get(1)).get("tasaImpuesto"));
            this.impuesto.add(imp2);
        }
    }

    public void setReferenciaNotaCredito(ArrayList<HashMap<String, String>> data) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        this.referencia = new ArrayList<>();
        Class<?>[] paramTypes = new Class[]{String.class};
        for (int i = 0; i < data.size(); i++) {
            Referencia ref = new Referencia();
            Class<? extends Referencia> objetoReferencia = (Class) ref.getClass();
            for (Map.Entry<String, String> entry : (Iterable<Map.Entry<String, String>>) ((HashMap) data.get(i)).entrySet()) {
                try {
                    System.out.println("set" + capitalize(entry.getKey()));
                    Method method = objetoReferencia.getDeclaredMethod("set" + capitalize(entry.getKey()), paramTypes);
                    method.invoke(ref, new Object[]{entry.getValue()});
                } catch (NoSuchMethodException e) {
                    System.err.println("No Method");
                }
            }

            this.referencia.add(ref);
        }
    }

    public void setIndicadorNotaCredito(HashMap<String, String> data) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (data.isEmpty()) {
            return;
        }
        this.indicador = new Indicador();

        Class<?>[] paramTypes = new Class[]{String.class};

        Class<? extends Indicador> objetoIndicadores = (Class) this.indicador.getClass();

        for (Map.Entry<String, String> entry : data.entrySet()) {

            try {
                Method method = objetoIndicadores.getDeclaredMethod("set" + capitalize(entry.getKey()), paramTypes);
                method.invoke(this.indicador, new Object[]{entry.getValue()});
            } catch (NoSuchMethodException noSuchMethodException) {
            }
        }
    }

    public void setDescuentoNotaCredito(ArrayList<ArrayList<String>> descuento) {
        if (descuento.size() == 0) {
            return;
        }
        this.descuento = new Descuento();
        System.out.println(descuento.size());
        System.out.println(descuento);
        //Estuvo el Codigo asi hasta el 09/08/2022 , lo cambie para efecto s de usar los descuentos globales
        //this.descuento.setMntTotalDescuentos(Double.valueOf(Double.parseDouble(((ArrayList<String>) descuento.get(0)).get(3))));
        this.descuento.setMntTotalDescuentos(((ArrayList<String>) descuento.get(0)).get(3));
    }

    public void setDatosAdicionalesNotaCredito(ArrayList<ArrayList<String>> datosAd) {
        if (datosAd.size() == 0) {
            return;
        }
    }

    public boolean setDocumentoNotaDebito(HashMap<String, String> data) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        this.documento = new Documento();

        Class<?>[] paramTypes = new Class[]{String.class};

        Class<? extends Documento> objetoDocumento = (Class) this.documento.getClass();

        for (Map.Entry<String, String> entry : data.entrySet()) {
            try {
                Method method = objetoDocumento.getDeclaredMethod("set" + capitalize(entry.getKey()), paramTypes);
                method.invoke(this.documento, new Object[]{entry.getValue()});
            } catch (NoSuchMethodException e) {
                System.err.println("No Method");
            }
        }

        return true;
    }

    public void setDetalleNotaDebito(ArrayList<HashMap<String, String>> detalle) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        this.detalle = new ArrayList<>();
        ArrayList<DetalleDocumento> det = this.detalle;
        Class<?>[] paramTypes = new Class[]{String.class};
        for (int i = 0; i < detalle.size(); i++) {
            DetalleDocumento dtTemp = new DetalleDocumento();

            Class<? extends DetalleDocumento> objetoDetalle = (Class) dtTemp.getClass();
            for (Map.Entry<String, String> entry : (Iterable<Map.Entry<String, String>>) ((HashMap) detalle.get(i)).entrySet()) {
                try {
                    Method method = objetoDetalle.getDeclaredMethod("set" + capitalize(entry.getKey()), paramTypes);
                    method.invoke(dtTemp, new Object[]{entry.getValue()});
                } catch (NoSuchMethodException e) {
                    System.err.println("No Method");
                }
            }

            det.add(dtTemp);
        }
    }

    public void setImpuestoNotaDebito(ArrayList<HashMap<String, String>> data) {
        if (data.size() == 0) {
            return;
        }
        this.impuesto = new ArrayList<>();
        System.out.println(data);
        Impuesto imp = new Impuesto();
        imp.setCodImpuesto((String) ((HashMap) data.get(0)).get("codImpuesto"));
        imp.setMontoImpuesto((String) ((HashMap) data.get(0)).get("montoImpuesto"));
        imp.setMontoImpuesto((String) ((HashMap) data.get(0)).get("baseImpuesto"));
        imp.setTasaImpuesto((String) ((HashMap) data.get(0)).get("tasaImpuesto"));
        this.impuesto.add(imp);

        if (data.size() > 1) {
            Impuesto imp2 = new Impuesto();

            imp2.setCodImpuesto((String) ((HashMap) data.get(1)).get("codImpuesto"));
            imp2.setMontoImpuesto((String) ((HashMap) data.get(1)).get("montoImpuesto"));
            imp2.setMontoImpuesto((String) ((HashMap) data.get(1)).get("baseImpuesto"));
            imp2.setTasaImpuesto((String) ((HashMap) data.get(1)).get("tasaImpuesto"));
            this.impuesto.add(imp2);
        }
    }

    public void setReferenciaNotaDebito(ArrayList<HashMap<String, String>> data) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        this.referencia = new ArrayList<>();
        Class<?>[] paramTypes = new Class[]{String.class};
        for (int i = 0; i < data.size(); i++) {
            Referencia ref = new Referencia();
            Class<? extends Referencia> objetoReferencia = (Class) ref.getClass();
            for (Map.Entry<String, String> entry : (Iterable<Map.Entry<String, String>>) ((HashMap) data.get(i)).entrySet()) {
                try {
                    System.out.println("set" + capitalize(entry.getKey()));
                    Method method = objetoReferencia.getDeclaredMethod("set" + capitalize(entry.getKey()), paramTypes);
                    method.invoke(ref, new Object[]{entry.getValue()});
                } catch (NoSuchMethodException e) {
                    System.err.println("No Method");
                }
            }

            this.referencia.add(ref);
        }
    }

    public void setIndicadorNotaDebito(HashMap<String, String> data) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (data.isEmpty()) {
            return;
        }
        this.indicador = new Indicador();

        Class<?>[] paramTypes = new Class[]{String.class};

        Class<? extends Indicador> objetoIndicadores = (Class) this.indicador.getClass();

        for (Map.Entry<String, String> entry : data.entrySet()) {

            try {
                Method method = objetoIndicadores.getDeclaredMethod("set" + capitalize(entry.getKey()), paramTypes);
                method.invoke(this.indicador, new Object[]{entry.getValue()});
            } catch (NoSuchMethodException noSuchMethodException) {
            }
        }
    }

       
    public boolean setGuia(HashMap<String, String> data) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        
        this.guia = new Guia();
        
        Class<?>[] paramTypes = new Class[]{String.class};

        Class<? extends Guia> objetoDocumento = (Class) this.guia.getClass();

        for (Map.Entry<String, String> entry : data.entrySet()) {
            try {
                Method method = objetoDocumento.getDeclaredMethod("set" + capitalize(entry.getKey()), paramTypes);
                method.invoke(this.guia, new Object[]{entry.getValue()});
            } catch (NoSuchMethodException e) {
                System.err.println("No Method");
            }
        }

        return true;
    }

    
    public boolean setDocumentoGuia(HashMap<String, String> data) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        
        
        this.documento = new Documento();

        Class<?>[] paramTypes = new Class[]{String.class};

        Class<? extends Documento> objetoDocumento = (Class) this.documento.getClass();

        for (Map.Entry<String, String> entry : data.entrySet()) {
            try {
                Method method = objetoDocumento.getDeclaredMethod("set" + capitalize(entry.getKey()), paramTypes);
                method.invoke(this.documento, new Object[]{entry.getValue()});
            } catch (NoSuchMethodException e) {
                System.err.println("No Method");
            }
        }

        return true;
    }

    public void setDetalleGuia(ArrayList<HashMap<String, String>> detalle) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        this.detalle = new ArrayList<>();
        ArrayList<DetalleDocumento> det = this.detalle;
        Class<?>[] paramTypes = new Class[]{String.class};
        for (int i = 0; i < detalle.size(); i++) {
            DetalleDocumento dtTemp = new DetalleDocumento();

            Class<? extends DetalleDocumento> objetoDetalle = (Class) dtTemp.getClass();
            for (Map.Entry<String, String> entry : (Iterable<Map.Entry<String, String>>) ((HashMap) detalle.get(i)).entrySet()) {
                try {
                    Method method = objetoDetalle.getDeclaredMethod("set" + capitalize(entry.getKey()), paramTypes);
                    method.invoke(dtTemp, new Object[]{entry.getValue()});
                } catch (NoSuchMethodException e) {
                    System.err.println("No Method");
                }
            }

            det.add(dtTemp);
        }
    }


    
    
    public static String capitalize(String word) {
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }
}
