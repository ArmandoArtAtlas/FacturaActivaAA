/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing;

/**
 *
 * @author armandovaler
 */
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

public class TI_Test01 {
    public static void main(String[] args) {
        try {
            // Crear un objeto Java
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("name", "John Doe");
            jsonObject.addProperty("age", 30);
            jsonObject.addProperty("city", "MAZDA 3(14-16)(D) (GP1729=PN25005) / D17|CERAMIC GG|1.694|6.776");
            
            // Convertir el objeto JSON a una cadena
            String json0 = jsonObject.toString();
            
            System.out.println(json0);
            
            System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
            // Convertir el objeto a JSON
            Gson gson = new Gson();
            String json = gson.toJson(jsonObject);
            
            System.out.println(json);
            
            // Codificar el JSON en UTF-8
            byte[] utf8Bytes = json.getBytes(StandardCharsets.UTF_8);
            String utf8EncodedJson = new String(utf8Bytes, StandardCharsets.UTF_8);
            
            System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
            // Imprimir el JSON codificado en UTF-8
            System.out.println(utf8EncodedJson);
            
            
            String encodedString = "MAZDA 3(14-16)(D) (GP1729=PN25005) / D17|CERAMIC GG|1.694|6.776";
            
            // Decodificar la secuencia de escape utilizando org.json
            String decodedString = JSONObject.valueToString(encodedString);
            System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
            System.out.println(decodedString);
        } catch (JSONException ex) {
            Logger.getLogger(TI_Test01.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
