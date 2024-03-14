package com.sunat.documentFacade;

import com.sunat.comunicacionBaja.DetalleBaja;
import com.sunat.resumen.Resumen;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BigDocumentBaja {

    public String tipoResumen;
    public String fechaGeneracion;
    public String idTransaccion;
    public Resumen resumen;
    public ArrayList<DetalleBaja> detalle;

    public String getTipoResumen() {
        return this.tipoResumen;
    }

    public void setTipoResumen(String tipoResumen) {
        this.tipoResumen = tipoResumen;
    }

    public String getFechaGeneracion() {
        return this.fechaGeneracion;
    }

    public void setFechaGeneracion(String fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public String getIdTransaccion() {
        return this.idTransaccion;
    }

    public void setIdTransaccion(String idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public Resumen getResumen() {
        return this.resumen;
    }

    public ArrayList<DetalleBaja> getDetalle() {
        return this.detalle;
    }

    public void setResumen(HashMap<String, String> data) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Resumen resumen = new Resumen();

        String tempId = data.get("id");
        String idTrans = (tempId.length() > 5) ? tempId.substring(1, tempId.length()) : tempId;

        Class<?>[] paramTypes = new Class[]{String.class};
        Class<? extends Resumen> objetoDetalle = (Class) resumen.getClass();
        for (Map.Entry<String, String> entry : data.entrySet()) {
            try {
                Method method = objetoDetalle.getDeclaredMethod("set" + BigDocument.capitalize(entry.getKey()), paramTypes);
                method.invoke(resumen, new Object[]{entry.getValue()});
            } catch (NoSuchMethodException e) {
                System.err.println("No Method");
            }
        }

        this.resumen = resumen;
    }

    public void setDetalle(ArrayList<HashMap<String, String>> dataDetalle) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        this.detalle = new ArrayList<>();
        ArrayList<DetalleBaja> det = this.detalle;
        Class<?>[] paramTypes = new Class[]{String.class};
        for (int i = 0; i < dataDetalle.size(); i++) {
            DetalleBaja dtTemp = new DetalleBaja();
            Class<? extends DetalleBaja> objetoDetalle = (Class) dtTemp.getClass();
            for (Map.Entry<String, String> entry : (Iterable<Map.Entry<String, String>>) ((HashMap) dataDetalle.get(i)).entrySet()) {
                try {
                    Method method = objetoDetalle.getDeclaredMethod("set" + BigDocument.capitalize(entry.getKey()), paramTypes);
                    method.invoke(dtTemp, new Object[]{entry.getValue()});
                } catch (NoSuchMethodException e) {
                    System.err.println("No Method");
                }
            }

            det.add(dtTemp);
        }
    }
}
