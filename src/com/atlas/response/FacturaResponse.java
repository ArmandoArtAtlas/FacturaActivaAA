package com.atlas.response;

import com.atlas.factory.ConectorDB;
import com.atlas.factory.DataBaseConnectionFactory;
import com.atlas.factory.DataBaseType;
import com.atlas.oauth.OAuth2Details;
import com.atlas.oauth.OAuthConstants;
import com.atlas.oauth.OAuthUtils;
import com.atlas.readerSql.ConectorSql;
import com.atlas.state.EstadoEmision;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sunat.comunicacionBaja.DetalleBaja;
import com.sunat.documentFacade.BigDocument;
import com.sunat.documentFacade.BigDocumentBaja;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import org.apache.http.HttpResponse;

public class FacturaResponse {

    public int name = 1;

    public int status;

    public String transId;

    public String tipo;

    public String serie;
    public String correlativo;
    public String estado;
    public String cod_error;
    public String observaciones;

    public FacturaResponse(HttpResponse response, String id, String type, Object document) {
        this.transId = "";
        this.tipo = "";
        this.serie = "";
        this.correlativo = "";
        this.estado = "";
        this.cod_error = "";
        this.observaciones = "";

        try {
            System.out.println(document.toString());
            insert(response, id, type, document);
        } catch (Exception e) {
            System.out.println("Error on Insert ... ");
            e.printStackTrace();
            System.out.println(e);
        }
    }

    public void insert(HttpResponse response, String id, String type, Object document) {
        Map<String, String> map = OAuthUtils.handleResponse(response);

        this.status = response.getStatusLine().getStatusCode();
        Map.Entry<String, String> entry = map.entrySet().iterator().next();
        JsonParser parser = new JsonParser();
        JsonObject o = parser.parse(String.format("{%s:%s}", new Object[]{entry.getKey(), entry.getValue()})).getAsJsonObject();
        System.out.println("aqui tambien me encuentro--------------------------------------------");
        System.out.println(this.status);
        System.out.println(o);
        System.out.println("aqui tambien me encuentro--------------------------------------------");
        if (!type.equals("RA")) {
            BigDocument doc = (BigDocument) document;

            if (this.status == 200) {
                JsonObject temp = o.get("data").getAsJsonObject();

                this.transId = id;
                this.tipo = doc.tipoDocumento;
                this.serie = doc.documento.serie;
                this.correlativo = doc.documento.correlativo + "";
                this.estado = temp.get("estadoEmision").getAsString();
                this.cod_error = "0";
                this.observaciones = EstadoEmision.getEstado(this.estado);
            } else if (this.status == 400 || this.status == 500 || this.status == 401 ) {
                JsonObject temp = o.get("errors").getAsJsonArray().get(0).getAsJsonObject();

                this.transId = id;
                this.tipo = doc.tipoDocumento;
                this.serie = doc.documento.serie;
                this.correlativo = doc.documento.correlativo + "";
                if (temp.get("meta") != null) {
                    this.estado = (temp.get("meta").getAsJsonObject().get("estadoEmision") != null) ? temp.get("meta").getAsJsonObject().get("estadoEmision").getAsString() : "";
                }
                this.cod_error = temp.get("code").getAsString();
                this.observaciones = temp.get("detail").getAsString();
            }
        } else {
            BigDocumentBaja doc = (BigDocumentBaja) document;

            if (this.status == 200) {
                JsonObject temp = o.get("data").getAsJsonObject();

                this.transId = temp.get("idResumen").getAsString();
                this.tipo = ((DetalleBaja) doc.detalle.get(0)).tipoDocumento;
                this.serie = ((DetalleBaja) doc.detalle.get(0)).serie;
                this.correlativo = ((DetalleBaja) doc.detalle.get(0)).correlativo + "";

                this.estado = "RA";
                this.cod_error = "0";
                this.observaciones = EstadoEmision.getEstado(this.estado);
            } else {
                JsonObject temp = o.get("errors").getAsJsonArray().get(0).getAsJsonObject();

                this.transId = id;
                this.tipo = ((DetalleBaja) doc.detalle.get(0)).tipoDocumento;
                this.serie = ((DetalleBaja) doc.detalle.get(0)).serie;
                this.correlativo = ((DetalleBaja) doc.detalle.get(0)).correlativo + "";

                this.estado = "RA";
                this.cod_error = temp.get("code").getAsString();
                this.observaciones = temp.get("detail").getAsString();
            }
        }
    }

    public void save() throws SQLException {
        ConectorDB cn = null;
        cn = DataBaseConnectionFactory.createConnection(DataBaseType.HanaSQL);
        //ConectorSql cn = new ConectorSql();
        cn.getConnection();
        
        String query_co = String.format(OAuth2Details.selectBefore, new Object[0]);
        ResultSet rs = cn.querySQL(query_co);
        ArrayList<ArrayList<String>> docData = cn.getData(rs);
        this.name = (docData.size() > 0) ? (Integer.parseInt(((ArrayList<String>) docData.get(0)).get(0)) + 1) : this.name;

        String query = String.format(OAuth2Details.insertResponse, new Object[]{Integer.valueOf(this.name), Integer.valueOf(this.name), this.transId, this.tipo, this.serie, this.correlativo, this.estado, this.cod_error, this.observaciones});
        cn.queryInsertSQL(query);
        
        
        cn = DataBaseConnectionFactory.createConnection(DataBaseType.MsSQL);
        /*
        Connection conn = cn.getConnection();;
        PreparedStatement pstm = null;
        query = "DELETE FROM TEMP_TABLE_TEST";
        Statement stmt = conn.createStatement();
        stmt.executeQuery(query);
        */
        
        String insertResponse = "DELETE FROM SBO_ANNTARAH_PRODUCCION.dbo.TEMP_TABLE_TEST; INSERT INTO " + OAuthConstants.MSSQL_ANN_DATABASE_NAME + ".DBO.\"TEMP_TABLE_TEST\" (\"Code\",\"U_Transid\", \"U_Tipo\", \"U_Serie\", \"U_Correlativo\", \"U_Estado\", \"U_Cod_Error\", \"U_Observacion\") VALUES ('%s','%s','%s','%s','%s','%s','%s','%s')";
        query = String.format(insertResponse, new Object[]{Integer.valueOf(this.name), this.transId, this.tipo, this.serie, this.correlativo, this.estado, this.cod_error, this.observaciones});
        System.out.println(query);
        cn.queryInsertSQL(query);
               
        // "INSERT INTO " + OAuthConstants.MSSQL_ANN_DATABASE_NAME + ".\"@FE_DOCS\" (\"Code\", \"Name\", \"U_Transid\", \"U_Tipo\", \"U_Serie\", \"U_Correlativo\", \"U_Estado\", \"U_Cod_Error\", \"U_Observacion\") VALUES ('%s','%s','%s','%s','%s','%s','%s','%s','%s')";
        
    }

    public String toString() {
        String fr = "transId: %s, tipo:%s, serie:%s, correlativo:%s, estado:%s, codError:%s, observaciones:%s";
        return String.format(fr, new Object[]{this.transId, this.tipo, this.serie, this.correlativo, this.estado, this.cod_error, this.observaciones});
    }
}
