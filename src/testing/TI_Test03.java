/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing;

import com.atlas.factory.ConectorDB;
import com.atlas.factory.DataBaseConnectionFactory;
import com.atlas.factory.DataBaseType;
import com.atlas.oauth.OAuth2Details;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author armandovaler
 */
public class TI_Test03 {

    public static void main(String arg[]) {

        ConectorDB cn = null;
        try {
            cn = DataBaseConnectionFactory.createConnection(DataBaseType.MsSQL);
            Connection conn = cn.getConnection();
            ResultSet rs = null;

            if (cn != null) {
                try {
                    System.out.println("Conexion Exitosa");

                    CallableStatement callableStatement = conn.prepareCall("{call [TI_SENDING_ELECTRONIC_RECEIPTS] ('-gris','207507')}");
                    callableStatement.execute();
                    rs = callableStatement.getResultSet();

                    if (rs == null) {
                        System.out.println("the resulset was null");

                        callableStatement = conn.prepareCall("{call ti_test}");
                        callableStatement.execute();
                        rs = callableStatement.getResultSet();
                        
                        while (rs.next()) {
                            System.out.println("print \"U_Transid\": "+rs.getString("U_Transid"));
                            System.out.println("print \"U_Tipo\": "+rs.getString("U_Tipo"));
                            System.out.println("print \"U_Serie\": "+rs.getString("U_Serie"));
                            System.out.println("print \"U_Correlativo\": "+rs.getString("U_Correlativo"));
                            System.out.println("print \"U_Estado\": "+rs.getString("U_Estado"));
                            System.out.println("print \"U_Cod_Error\": "+rs.getString("U_Cod_Error"));
                            System.out.println("print \"U_Observacion\": "+rs.getString("U_Observacion"));
                            
                        }
                        return;
                    }

                    while (rs.next()) {
                        System.out.println("print " + rs.getString(1));
                    }
                } catch (Exception e) {
                    e.printStackTrace(); // Manejar la excepción adecuadamente
                } finally {
                    if (rs != null) {
                        rs.close(); // Cerrar el ResultSet
                    }
                    if (conn != null) {
                        conn.close(); // Cerrar la conexión a la base de datos
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Manejar la excepción adecuadamente
        }
    }
}
