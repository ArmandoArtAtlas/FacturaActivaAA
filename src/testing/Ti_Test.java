/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing;

import com.atlas.oauth.OAuth2Details;
import com.atlas.readerSql.ConectorSql;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author admoscarapaza
 */
public class Ti_Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String  idoperacion;
        idoperacion="54808";
        PreparedStatement ps=null;
        ConectorSql cn = new ConectorSql();
        cn.getConnection();
        if (cn!=null)
        {
            try {
                
             System.out.println("Conexion Existosa");
             String query = String.format(OAuth2Details.getQueryImpuestoDocumento_BF, new Object[]{idoperacion});
             System.out.println(query);
             
             ResultSet rs = cn.querySQL(query);
          
             while (rs.next()){
             System.out.println("data1  "+rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3));
             }
             
             
                
            } catch (Exception e) {
            }
             
             
        }  
       
        
        
     
       
        
        // TODO code application logic here
    }
    
}
