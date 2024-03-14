/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing;

import com.atlas.factory.ConectorDB;
import com.atlas.factory.DataBaseConnectionFactory;
import com.atlas.factory.DataBaseType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author armandovaler
 */
public class TI_Test04 {

    public static void main(String arg[]) {

        ConectorDB cn = null;
        cn = DataBaseConnectionFactory.createConnection(DataBaseType.PostgreSQL);
        
        Connection conn = cn.getConnection();;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        if (cn != null) {
            try {

                System.out.println("Conexion Existosa");
                String query = "select cc.nro_contrato  from comercial_contrato cc";
                System.out.println(query);
               
                pstm = conn.prepareCall(query);
                rs = pstm.executeQuery();
                
            while (rs.next()) {
                System.out.println("print \"Contrato\": "+rs.getString("nro_contrato"));
                /*
                System.out.println("print \"U_Tipo\": "+rs.getString("U_Tipo"));
                System.out.println("print \"U_Serie\": "+rs.getString("U_Serie"));
                System.out.println("print \"U_Correlativo\": "+rs.getString("U_Correlativo"));
                System.out.println("print \"U_Estado\": "+rs.getString("U_Estado"));
                System.out.println("print \"U_Cod_Error\": "+rs.getString("U_Cod_Error"));
                System.out.println("print \"U_Observacion\": "+rs.getString("U_Cod_Error"));
                */
                
            }
            cn.close();
            
            /*
                ResultSet rs = cn.querySQL(query);

                while (rs.next()) {
                    System.out.println("data1  " + rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
                }
            */
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
