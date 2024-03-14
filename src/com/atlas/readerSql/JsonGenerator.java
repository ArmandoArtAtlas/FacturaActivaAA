package com.atlas.readerSql;

import com.atlas.business.DocObject;
import com.atlas.factory.ConectorDB;
import com.atlas.factory.DataBaseConnectionFactory;
import com.atlas.factory.DataBaseType;
import com.atlas.oauth.OAuth2Details;
import com.google.gson.Gson;
import com.sunat.documentFacade.BigDocument;
import com.sunat.documentFacade.BigDocumentBaja;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class JsonGenerator {

    public ArrayList<DocObject> getJsonMessageBoletaFactura(String idoperacion) throws SQLException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        ConectorSql cn = new ConectorSql();
        cn.getConnection();
        String query = String.format(OAuth2Details.getQueryBoletaFactura, new Object[]{idoperacion});
        System.out.println(query);
        ResultSet rs = cn.querySQL(query);
        Class<?>[] paramTypes = new Class[]{String.class};

        ArrayList<HashMap<String, String>> docData = cn.getDataDict(rs);

        ArrayList<DocObject> jsonMess = new ArrayList<>();
        for (int i = 0; i < docData.size(); i++) {
            BigDocument document = new BigDocument();

            Class<? extends BigDocument> objetoDeClassDocument = (Class) document.getClass();

            Method setTipoDocumento = objetoDeClassDocument.getDeclaredMethod("setTipoDocumento", paramTypes);
            Method setFechaEmision = objetoDeClassDocument.getDeclaredMethod("setFechaEmision", paramTypes);
            Method setIdTransaccion = objetoDeClassDocument.getDeclaredMethod("setIdTransaccion", paramTypes);
            Method setCorreoReceptor = objetoDeClassDocument.getDeclaredMethod("setCorreoReceptor", paramTypes);

            setTipoDocumento.invoke(document, new Object[]{((HashMap) docData.get(i)).get("tipoDocumento")});
            setFechaEmision.invoke(document, new Object[]{((HashMap) docData.get(i)).get("fechaEmision")});
            setIdTransaccion.invoke(document, new Object[]{((HashMap) docData.get(i)).get("idTransaccion")});
            setCorreoReceptor.invoke(document, new Object[]{((HashMap) docData.get(i)).get("correoReceptor")});

            document.setDocumento(docData.get(i));

            ResultSet rsCu = getCuotaBoletaFactura((String) ((HashMap) docData.get(i)).get("idTransaccion"));
            ArrayList<HashMap<String, String>> dataCuota = cn.getDataDict(rsCu);

            if (!dataCuota.isEmpty()) {
                document.setCuota(dataCuota);
            }

            ResultSet rsAn = getAnticipoBoletaFacturaAnticipo((String) ((HashMap) docData.get(i)).get("idTransaccion"));
            ArrayList<HashMap<String, String>> dataAnticipo = cn.getDataDict(rsAn);

            if (!dataAnticipo.isEmpty()) {
                document.setAnticipo(dataAnticipo);
            }

            ResultSet rsD = getDetalleBoletaFactura((String) ((HashMap) docData.get(i)).get("idTransaccion"));
            ArrayList<HashMap<String, String>> dataDetalle = cn.getDataDict(rsD);

            document.setDetalle(dataDetalle);

            ResultSet rsIn = getIndicadoresBoletaFactura((String) ((HashMap) docData.get(i)).get("idTransaccion"));
            ArrayList<HashMap<String, String>> dataIndicadores = cn.getDataDict(rsIn);

            document.setIndicadores(dataIndicadores.get(0));

            ResultSet rsDs = getDescuentoBoletaFactura((String) ((HashMap) docData.get(i)).get("idTransaccion"));
            ArrayList<HashMap<String, String>> dataDescuento = cn.getDataDict(rsDs);

            if (!dataDescuento.isEmpty()) {
                document.setDescuento(dataDescuento.get(0));
            }

            ResultSet rsI = getImpuestoBoletaFactura((String) ((HashMap) docData.get(i)).get("idTransaccion"));
            ArrayList<HashMap<String, String>> dataImpuesto = cn.getDataDict(rsI);

            document.setImpuesto(dataImpuesto);

            document.setDatosAdicionales(docData.get(i));

            Gson gson = new Gson();
            jsonMess.add(new DocObject((String) ((HashMap) docData.get(i)).get("idTransaccion"), (String) ((HashMap) docData.get(i)).get("fechaEmision"), document, gson.toJson(document)));
            System.out.println(jsonMess.get(i));
        }

        return jsonMess;
    }

    private ResultSet getDetalleBoletaFactura(String id) {
        ConectorSql cn = new ConectorSql();
        cn.getConnection();
        String query = String.format(OAuth2Details.getQueryDetalleDocumento_BF, new Object[]{id});
        ResultSet rs = cn.querySQL(query);
        return rs;
    }

    private ResultSet getCuotaBoletaFactura(String id) {
        ConectorSql cn = new ConectorSql();
        cn.getConnection();
        String query = String.format(OAuth2Details.getQueryCuotasDocumento_BF, new Object[]{id});
        ResultSet rs = cn.querySQL(query);
        return rs;
    }

    private ResultSet getIndicadoresBoletaFactura(String id) {
        ConectorSql cn = new ConectorSql();
        cn.getConnection();
        String query = String.format(OAuth2Details.getQueryIndicadorDocumento_BF, new Object[]{id});
        ResultSet rs = cn.querySQL(query);
        return rs;
    }

    private ResultSet getImpuestoBoletaFactura(String id) {
        ConectorSql cn = new ConectorSql();
        cn.getConnection();
        String query = String.format(OAuth2Details.getQueryImpuestoDocumento_BF, new Object[]{id});
        ResultSet rs = cn.querySQL(query);
        return rs;
    }

    private ResultSet getAnticipoBoletaFacturaAnticipo(String id) {
        ConectorSql cn = new ConectorSql();
        cn.getConnection();
        String query = String.format(OAuth2Details.getQueryAnticipoDocumento_BF, new Object[]{id});
        ResultSet rs = cn.querySQL(query);
        return rs;
    }

    private ResultSet getDescuentoBoletaFactura(String id) {
        ConectorSql cn = new ConectorSql();
        cn.getConnection();
        String query = String.format(OAuth2Details.getQueryDescuentoDocumento_BF, new Object[]{id});
        System.out.println(query);
        ResultSet rs = cn.querySQL(query);
        return rs;
    }

    /*
    private ResultSet getDescuentoBoletaFactura(String id) {
        ConectorSql cn = new ConectorSql();
        cn.getConnection();
        ResultSet rs = cn.querySQL("SELECT * FROM SBO_ATLAS_PRODUCCION.dbo.mitb_descuento WHERE idoperacion=" + id);
        return rs;
    }
     */
    private ResultSet getDatosAdicionalesBoletaFactura(String id) {
        ConectorSql cn = new ConectorSql();
        cn.getConnection();
        ResultSet rs = cn.querySQL("SELECT * FROM SBO_ATLAS_PRODUCCION.dbo.mitb_datosAdicionales WHERE idoperacion=" + id);
        System.out.println(rs);
        return rs;
    }

    public ArrayList<DocObject> getJsonMessageBoletaFacturaAnticipo(String idoperacion) throws SQLException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        ConectorSql cn = new ConectorSql();
        cn.getConnection();
        String query = String.format(OAuth2Details.getQueryBoletaFacturaAnticipo, new Object[]{idoperacion});
        System.out.println(query);
        ResultSet rs = cn.querySQL(query);
        Class<?>[] paramTypes = new Class[]{String.class};

        ArrayList<HashMap<String, String>> docData = cn.getDataDict(rs);

        ArrayList<DocObject> jsonMess = new ArrayList<>();
        for (int i = 0; i < docData.size(); i++) {
            BigDocument document = new BigDocument();

            Class<? extends BigDocument> objetoDeClassDocument = (Class) document.getClass();

            Method setTipoDocumento = objetoDeClassDocument.getDeclaredMethod("setTipoDocumento", paramTypes);
            Method setFechaEmision = objetoDeClassDocument.getDeclaredMethod("setFechaEmision", paramTypes);
            Method setIdTransaccion = objetoDeClassDocument.getDeclaredMethod("setIdTransaccion", paramTypes);
            Method setCorreoReceptor = objetoDeClassDocument.getDeclaredMethod("setCorreoReceptor", paramTypes);
            setTipoDocumento.invoke(document, new Object[]{((HashMap) docData.get(i)).get("tipoDocumento")});
            setFechaEmision.invoke(document, new Object[]{((HashMap) docData.get(i)).get("fechaEmision")});
            setIdTransaccion.invoke(document, new Object[]{((HashMap) docData.get(i)).get("idTransaccion")});
            setCorreoReceptor.invoke(document, new Object[]{((HashMap) docData.get(i)).get("correoReceptor")});

            document.setDocumento(docData.get(i));

            ResultSet rsCu = getCuotaBoletaFacturaAnticipo((String) ((HashMap) docData.get(i)).get("idTransaccion"));
            ArrayList<HashMap<String, String>> dataCuota = cn.getDataDict(rsCu);

            if (!dataCuota.isEmpty()) {
                document.setCuota(dataCuota);
            }

            /*
            ResultSet rsAn = getAnticipoBoletaFacturaAnticipo((String) ((HashMap) docData.get(i)).get("idTransaccion"));
            ArrayList<HashMap<String, String>> dataAnticipo = cn.getDataDict(rsAn);

            if(!dataAnticipo.isEmpty()){
            document.setAnticipo(dataAnticipo);
            }
             */
            ResultSet rsD = getDetalleBoletaFacturaAnticipo((String) ((HashMap) docData.get(i)).get("idTransaccion"));
            ArrayList<HashMap<String, String>> dataDetalle = cn.getDataDict(rsD);

            document.setDetalle(dataDetalle);

            ResultSet rsIn = getIndicadoresBoletaFacturaAnticipo((String) ((HashMap) docData.get(i)).get("idTransaccion"));
            ArrayList<HashMap<String, String>> dataIndicadores = cn.getDataDict(rsIn);

            document.setIndicadores(dataIndicadores.get(0));

            ResultSet rsI = getImpuestoBoletaFacturaAnticipo((String) ((HashMap) docData.get(i)).get("idTransaccion"));
            ArrayList<HashMap<String, String>> dataImpuesto = cn.getDataDict(rsI);

            document.setImpuesto(dataImpuesto);

            document.setDatosAdicionales(docData.get(i));

            Gson gson = new Gson();
            jsonMess.add(new DocObject((String) ((HashMap) docData.get(i)).get("idTransaccion"), (String) ((HashMap) docData.get(i)).get("fechaEmision"), document, gson.toJson(document)));
            System.out.println(jsonMess.get(i));
        }

        return jsonMess;
    }

    private ResultSet getDetalleBoletaFacturaAnticipo(String id) {
        ConectorSql cn = new ConectorSql();
        cn.getConnection();
        String query = String.format(OAuth2Details.getQueryDetalleDocumento_Anticipo_BF, new Object[]{id});
        ResultSet rs = cn.querySQL(query);
        return rs;
    }

    private ResultSet getCuotaBoletaFacturaAnticipo(String id) {
        ConectorSql cn = new ConectorSql();
        cn.getConnection();
        String query = String.format(OAuth2Details.getQueryCuotasDocumento_Anticipo_BF, new Object[]{id});
        ResultSet rs = cn.querySQL(query);
        return rs;
    }

    private ResultSet getIndicadoresBoletaFacturaAnticipo(String id) {
        ConectorSql cn = new ConectorSql();
        cn.getConnection();
        String query = String.format(OAuth2Details.getQueryIndicadorDocumento_Anticipo_BF, new Object[]{id});
        ResultSet rs = cn.querySQL(query);
        return rs;
    }

    private ResultSet getImpuestoBoletaFacturaAnticipo(String id) {
        ConectorSql cn = new ConectorSql();
        cn.getConnection();
        String query = String.format(OAuth2Details.getQueryImpuestoDocumento_Anticipo_BF, new Object[]{id});
        ResultSet rs = cn.querySQL(query);
        return rs;
    }

    public ArrayList<DocObject> getJsonMessageNotaCredito(String idoperacion) throws SQLException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        ConectorSql cn = new ConectorSql();
        cn.getConnection();
        String query = String.format(OAuth2Details.getQueryNotaCredito, new Object[]{idoperacion});
        System.out.println(query);
        ResultSet rs = cn.querySQL(query);
        Class<?>[] paramTypes = new Class[]{String.class};

        ArrayList<HashMap<String, String>> docData = cn.getDataDict(rs);

        ArrayList<DocObject> jsonMess = new ArrayList<>();
        for (int i = 0; i < docData.size(); i++) {
            BigDocument document = new BigDocument();

            Class<? extends BigDocument> objetoDeClassDocument = (Class) document.getClass();

            Method setTipoDocumento = objetoDeClassDocument.getDeclaredMethod("setTipoDocumento", paramTypes);
            Method setFechaEmision = objetoDeClassDocument.getDeclaredMethod("setFechaEmision", paramTypes);
            Method setIdTransaccion = objetoDeClassDocument.getDeclaredMethod("setIdTransaccion", paramTypes);
            Method setCorreoReceptor = objetoDeClassDocument.getDeclaredMethod("setCorreoReceptor", paramTypes);

            setTipoDocumento.invoke(document, new Object[]{"07"});
            setFechaEmision.invoke(document, new Object[]{((HashMap) docData.get(i)).get("fechaEmision")});

            setIdTransaccion.invoke(document, new Object[]{((HashMap) docData.get(i)).get("idTransaccion")});
            setCorreoReceptor.invoke(document, new Object[]{((HashMap) docData.get(i)).get("correoReceptor")});

            document.setDocumentoNotaCredito(docData.get(i));

            ResultSet rsCu = getCuotaNotaCredito((String) ((HashMap) docData.get(i)).get("idTransaccion"));
            ArrayList<HashMap<String, String>> dataCuota = cn.getDataDict(rsCu);

            if (!dataCuota.isEmpty()) {
                document.setCuotaNotaCredito(dataCuota);
            }

            ResultSet rsD = getDetalleNotaCredito((String) ((HashMap) docData.get(i)).get("idTransaccion"));
            ArrayList<HashMap<String, String>> dataDetalle = cn.getDataDict(rsD);
            document.setDetalleNotaCredito(dataDetalle);

            ResultSet rsI = getImpuestoNotaCredito((String) ((HashMap) docData.get(i)).get("idTransaccion"));
            ArrayList<HashMap<String, String>> dataImpuesto = cn.getDataDict(rsI);
            document.setImpuestoNotaCredito(dataImpuesto);

            ResultSet rsR = getReferenciaNotaCredito((String) ((HashMap) docData.get(i)).get("idTransaccion"));
            ArrayList<HashMap<String, String>> dataReferencia = cn.getDataDict(rsR);

            document.setReferenciaNotaCredito(dataReferencia);

            ResultSet rsIn = getIndicadorNotaCredito((String) ((HashMap) docData.get(i)).get("idTransaccion"));
            ArrayList<HashMap<String, String>> dataIndicador = cn.getDataDict(rsIn);

            document.setIndicadorNotaCredito(dataIndicador.get(0));

            Gson gson = new Gson();
            jsonMess.add(new DocObject((String) ((HashMap) docData.get(i)).get("idTransaccion"), (String) ((HashMap) docData.get(i)).get("fechaEmision"), document, gson.toJson(document)));
            System.out.println(jsonMess.get(i));
        }

        return jsonMess;
    }

    private ResultSet getDetalleNotaCredito(String id) {
        ConectorSql cn = new ConectorSql();
        cn.getConnection();
        String query = String.format(OAuth2Details.getQueryDetalleNotaCredito, new Object[]{id});
        System.out.println(query);
        ResultSet rs = cn.querySQL(query);
        return rs;
    }

    private ResultSet getCuotaNotaCredito(String id) {
        ConectorSql cn = new ConectorSql();
        cn.getConnection();
        String query = String.format(OAuth2Details.getQueryCuotasNotaCredito, new Object[]{id});
        System.out.println(query);
        ResultSet rs = cn.querySQL(query);
        return rs;
    }

    private ResultSet getImpuestoNotaCredito(String id) {
        ConectorSql cn = new ConectorSql();
        cn.getConnection();
        String query = String.format(OAuth2Details.getQueryImpuestoNotaCredito, new Object[]{id});
        System.out.println(query);
        ResultSet rs = cn.querySQL(query);
        return rs;
    }

    private ResultSet getReferenciaNotaCredito(String id) {
        ConectorSql cn = new ConectorSql();
        cn.getConnection();
        String query = String.format(OAuth2Details.getQueryReferenciaNotaCredito, new Object[]{id});
        System.out.println(query);
        ResultSet rs = cn.querySQL(query);
        return rs;
    }

    private ResultSet getIndicadorNotaCredito(String id) {
        ConectorSql cn = new ConectorSql();
        cn.getConnection();
        String query = String.format(OAuth2Details.getQueryIndicadorNotaCredito, new Object[]{id});
        System.out.println(query);
        ResultSet rs = cn.querySQL(query);
        return rs;
    }

    private ResultSet getDescuentoNotaCredito(String id) {
        ConectorSql cn = new ConectorSql();
        cn.getConnection();
        ResultSet rs = cn.querySQL("SELECT * FROM SBO_ATLAS_PRODUCCION.dbo.mitb_descuentocredito WHERE idoperacion=" + id);
        return rs;
    }

    private ResultSet getDatosAdicionalesNotaCredito(String id) {
        ConectorSql cn = new ConectorSql();
        cn.getConnection();
        ResultSet rs = cn.querySQL("SELECT * FROM SBO_ATLAS_PRODUCCION.dbo.mitb_datosAdicionalesNcredito WHERE idoperacion=" + id);
        System.out.println(rs);
        return rs;
    }

    public ArrayList<DocObject> getJsonMessageNotaDebito(String idoperacion) throws SQLException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        ConectorSql cn = new ConectorSql();
        cn.getConnection();
        String query = String.format(OAuth2Details.getQueryNotaDebito, new Object[]{idoperacion});

        System.out.println(query);
        ResultSet rs = cn.querySQL(query);

        Class<?>[] paramTypes = new Class[]{String.class};

        ArrayList<HashMap<String, String>> docData = cn.getDataDict(rs);

        ArrayList<DocObject> jsonMess = new ArrayList<>();
        for (int i = 0; i < docData.size(); i++) {
            BigDocument document = new BigDocument();

            Class<? extends BigDocument> objetoDeClassDocument = (Class) document.getClass();

            Method setTipoDocumento = objetoDeClassDocument.getDeclaredMethod("setTipoDocumento", paramTypes);
            Method setFechaEmision = objetoDeClassDocument.getDeclaredMethod("setFechaEmision", paramTypes);
            Method setIdTransaccion = objetoDeClassDocument.getDeclaredMethod("setIdTransaccion", paramTypes);
            Method setCorreoReceptor = objetoDeClassDocument.getDeclaredMethod("setCorreoReceptor", paramTypes);

            setTipoDocumento.invoke(document, new Object[]{"08"});
            setFechaEmision.invoke(document, new Object[]{((HashMap) docData.get(i)).get("fechaEmision")});
            setIdTransaccion.invoke(document, new Object[]{((HashMap) docData.get(i)).get("idTransaccion")});
            setCorreoReceptor.invoke(document, new Object[]{((HashMap) docData.get(i)).get("correoReceptor")});

            document.setDocumentoNotaDebito(docData.get(i));

            ResultSet rsD = getDetalleNotaDebito((String) ((HashMap) docData.get(i)).get("idTransaccion"));

            ArrayList<HashMap<String, String>> dataDetalle = cn.getDataDict(rsD);

            document.setDetalleNotaDebito(dataDetalle);

            ResultSet rsI = getImpuestoNotaDebito((String) ((HashMap) docData.get(i)).get("idTransaccion"));
            ArrayList<HashMap<String, String>> dataImpuesto = cn.getDataDict(rsI);

            document.setImpuestoNotaDebito(dataImpuesto);

            ResultSet rsR = getReferenciaNotaDebito((String) ((HashMap) docData.get(i)).get("idTransaccion"));
            ArrayList<HashMap<String, String>> dataReferencia = cn.getDataDict(rsR);

            document.setReferenciaNotaDebito(dataReferencia);

            ResultSet rsIn = getIndicadorNotaDebito((String) ((HashMap) docData.get(i)).get("idTransaccion"));
            ArrayList<HashMap<String, String>> dataIndicador = cn.getDataDict(rsIn);

            document.setIndicadorNotaDebito(dataIndicador.get(0));

            Gson gson = new Gson();
            jsonMess.add(new DocObject((String) ((HashMap) docData.get(i)).get("idTransaccion"), (String) ((HashMap) docData.get(i)).get("fechaEmision"), document, gson.toJson(document)));
            System.out.println(jsonMess.get(i));
        }

        return jsonMess;
    }

    private ResultSet getDetalleNotaDebito(String id) {
        ConectorSql cn = new ConectorSql();
        cn.getConnection();

        String query = String.format(OAuth2Details.getQueryDetalleNotaDebito, new Object[]{id});
        System.out.println(query);
        ResultSet rs = cn.querySQL(query);
        return rs;
    }

    private ResultSet getImpuestoNotaDebito(String id) {
        ConectorSql cn = new ConectorSql();
        cn.getConnection();
        String query = String.format(OAuth2Details.getQueryImpuestoNotaDebito, new Object[]{id});
        System.out.println(query);
        ResultSet rs = cn.querySQL(query);
        return rs;
    }

    private ResultSet getReferenciaNotaDebito(String id) {
        ConectorSql cn = new ConectorSql();
        cn.getConnection();
        String query = String.format(OAuth2Details.getQueryReferenciaNotaDebito, new Object[]{id});
        System.out.println(query);
        ResultSet rs = cn.querySQL(query);
        return rs;
    }

    private ResultSet getIndicadorNotaDebito(String id) {
        ConectorSql cn = new ConectorSql();
        cn.getConnection();
        String query = String.format(OAuth2Details.getQueryIndicadorNotaDebito, new Object[]{id});
        System.out.println(query);
        ResultSet rs = cn.querySQL(query);
        return rs;
    }

    /*Guia de Remision */
    public ArrayList<DocObject> getJsonMessageGuiaRemision(String idoperacion,
            String flagDocTypeDocument, String flagDocTypeGuia, String flagDocTypeDetail, String deliveryTypeFlag)
            throws SQLException, NoSuchMethodException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {

        ConectorDB cn = null;

        if (deliveryTypeFlag.equals("-grto") || deliveryTypeFlag.equals("-groo") ) {
            cn = DataBaseConnectionFactory.createConnection(DataBaseType.PostgreSQL);
            System.out.println("Se Esta conectando a PostgresSql");
        } else {
            cn = DataBaseConnectionFactory.createConnection(DataBaseType.HanaSQL);
            System.out.println("Se Esta conectando a HanaSql");

        }
        cn.getConnection();
        /*
        *Este Fragmento de Codigo se usaba antes
        ConectorSql cn = new ConectorSql();
        cn.getConnection();
         */
        String query = String.format(flagDocTypeDocument, new Object[]{idoperacion});

        System.out.println(query);
        ResultSet rs = cn.querySQL(query);

        Class<?>[] paramTypes = new Class[]{String.class};

        ArrayList<HashMap<String, String>> docData = cn.getDataDict(rs);

        ArrayList<DocObject> jsonMess = new ArrayList<>();

        for (int i = 0; i < docData.size(); i++) {
            BigDocument document = new BigDocument();

            Class<? extends BigDocument> objetoDeClassDocument = (Class) document.getClass();

            Method setTipoDocumento = objetoDeClassDocument.getDeclaredMethod("setTipoDocumento", paramTypes);
            Method setFechaEmision = objetoDeClassDocument.getDeclaredMethod("setFechaEmision", paramTypes);
            Method setIdTransaccion = objetoDeClassDocument.getDeclaredMethod("setIdTransaccion", paramTypes);
            Method setCorreoReceptor = objetoDeClassDocument.getDeclaredMethod("setCorreoReceptor", paramTypes);

            setTipoDocumento.invoke(document, new Object[]{((HashMap) docData.get(i)).get("tipoDocumento")});
            setFechaEmision.invoke(document, new Object[]{((HashMap) docData.get(i)).get("fechaEmision")});
            setIdTransaccion.invoke(document, new Object[]{((HashMap) docData.get(i)).get("idTransaccion")});
            setCorreoReceptor.invoke(document, new Object[]{((HashMap) docData.get(i)).get("correoReceptor")});

            //System.out.println("aqui el tipo de idTransaccion "+docData.get(i).get("idTransaccion"));
            ResultSet rsGr = getGuia((String) ((HashMap) docData.get(i)).get("idTransaccion"), flagDocTypeGuia, cn);
            ArrayList<HashMap<String, String>> dataGuia = cn.getDataDict(rsGr);
            filtrarDataGuia(dataGuia);
            document.setGuia(dataGuia.get(0));

            document.setDocumentoGuia(docData.get(i));

            if ((docData.get(i).get("observacion") != null) || (docData.get(i).get("cantidadTotal") != null) || (docData.get(i).get("cantidadCajas") != null)) {
                document.setDatosAdicionales(docData.get(i));
            }

            //System.out.println("observacion "+ docData.get(i).get("observacion") == null ? '' : '' );
            ResultSet rsD = getDetalleGuia((String) ((HashMap) docData.get(i)).get("idTransaccion"), flagDocTypeDetail, cn);
            ArrayList<HashMap<String, String>> dataDetalle = cn.getDataDict(rsD);
            document.setDetalleGuia(dataDetalle);

            Gson gson = new Gson();
            jsonMess.add(new DocObject((String) ((HashMap) docData.get(i)).get("idTransaccion"), (String) ((HashMap) docData.get(i)).get("fechaEmision"), document, gson.toJson(document).toString()));
            System.out.println(jsonMess.get(i));

            /*
            System.out.println("Encoding Utf8");

            DocObject docObject = jsonMess.get(i);
            byte[] utf8Bytes = gson.toJson(docObject).getBytes(StandardCharsets.UTF_8);
            String utf8EncodedJson = new String(utf8Bytes, StandardCharsets.UTF_8);
            System.out.println(utf8EncodedJson);
             */
        }

        return jsonMess;
    }

    private ResultSet getGuia(String id, String flagDocType, ConectorDB cn) throws SQLException {
        /*
        ConectorSql cn = new ConectorSql();
        cn.getConnection();
         */
        String query = String.format(flagDocType, new Object[]{id});
        System.out.println(query);
        ResultSet rs = cn.querySQL(query);
        return rs;
    }

    private ResultSet getDetalleGuia(String id, String flagDocType, ConectorDB cn) {
        /*
        ConectorSql cn = new ConectorSql();
        cn.getConnection();
         */
        String query = String.format(flagDocType, new Object[]{id});
        System.out.println(query);
        ResultSet rs = cn.querySQL(query);
        return rs;
    }

    //private ArrayList<HashMap<String, String>> filtrarDataGuia(ArrayList<HashMap<String, String>> dataGuia) {
    private void filtrarDataGuia(ArrayList<HashMap<String, String>> dataGuia) {
        ArrayList<HashMap<String, String>> resultadoFiltrado = new ArrayList<>();

        for (HashMap<String, String> fila : dataGuia) {
            // Verifica el valor del atributo "data"
            String valorData = fila.get("codigoModalidadTransporte");
            
            // Si el valor del atributo "data" es igual a 1, no considera ciertos campos
            if (valorData.equals("01")) {
                // Aquí puedes eliminar los campos que no deseas mantener en la fila filtrada
                fila.remove("tipoDocumentoChofer");
                fila.remove("numeroDocumentoChofer");
                fila.remove("nombreChofer");
                fila.remove("placaTransporte");
                
            } else if (valorData.equals("02")){
                
                // Aquí puedes eliminar los campos que no deseas mantener en la fila filtrada
                fila.remove("nombreTransportista");
                fila.remove("numeroDocumentoTransportista");
                fila.remove("tipoDocumentoTransportista");
                
            }
        }
        //return resultadoFiltrado;
    }

    public ArrayList<DocObject> getJsonMessageBajas(String idoperacion) throws SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        ConectorSql cn = new ConectorSql();
        cn.getConnection();
        String query = String.format(OAuth2Details.getQueryResumenBaja, new Object[]{idoperacion});
        System.out.println(query);

        ResultSet rs = cn.querySQL(query);

        ArrayList<HashMap<String, String>> docData = cn.getDataDict(rs);

        ArrayList<DocObject> jsonMess = new ArrayList<>();
        for (int i = 0; i < docData.size(); i++) {
            BigDocumentBaja document = new BigDocumentBaja();

            document.setTipoResumen("RA");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaActual = new Date();
            String fechaConFormato = sdf.format(fechaActual);

            document.setFechaGeneracion(fechaConFormato);

            document.setIdTransaccion((String) ((HashMap) docData.get(i)).get("idTransaccion"));
            //document.setIdTransaccion("2");

            document.setResumen(docData.get(i));

            ResultSet rsB = getDocumentoBaja((String) ((HashMap) docData.get(i)).get("idTransaccion"));

            ArrayList<HashMap<String, String>> dataDetalle = cn.getDataDict(rsB);

            document.setDetalle(dataDetalle);

            Gson gson = new Gson();
            jsonMess.add(new DocObject((String) ((HashMap) docData.get(i)).get("idTransaccion"), fechaConFormato, document, gson.toJson(document)));
            System.out.println(jsonMess.get(i));
        }

        return jsonMess;
    }

    private ResultSet getDocumentoBaja(String id) {
        ConectorSql cn = new ConectorSql();
        cn.getConnection();
        String query = String.format(OAuth2Details.getQueryDocumentoBaja, new Object[]{id});
        System.out.println(query);

        ResultSet rs = cn.querySQL(query);
        return rs;
    }
}
