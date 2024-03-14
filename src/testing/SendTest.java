package testing;

import com.atlas.business.Token;
import com.atlas.oauth.OAuth2Details;
import com.atlas.oauth.OAuthUtils;
import java.io.IOException;
import org.apache.http.HttpResponse;


public class SendTest
{
  public static void main(String[] args) throws IOException {
    System.out.println("Init 1");
    OAuth2Details oauthDetails = new OAuth2Details();
    
    String json = "{\"tipoDocumento\":\"09\",\"fechaEmision\":\"2018-09-24\",\"idTransaccion\":\"156409\",\"correoReceptor\":\"null\",\"documento\":{\"serie\":\"B001\",\"correlativo\":2,\"nombreEmisor\":\"Art Atlas S.R.L.\",\"tipoDocEmisor\":\"6\",\"numDocEmisor\":\"20413770204\",\"direccionOrigen\":\"Av test\",\"direccionUbigeo\":\"040123\",\"nombreComercialEmisor\":\"Art Atlas S.A.\",\"tipoDocReceptor\":\"1\",\"numDocReceptor\":\"29718610\",\"nombreReceptor\":\"Persona x\",\"direccionDestino\":\"URB. BANCARIOS C-18, JOSE LUIS BUSTAMANTE Y RIVERO, AREQUIPA\\rAREQUIPA\",\"tipoMoneda\":\"PEN\",\"mntNeto\":250.73,\"mntTotalIgv\":45.13,\"mntTotal\":295.86},\"impuesto\":[{\"codImpuesto\":\"1000\",\"montoImpuesto\":21.06,\"tasaImpuesto\":0.18},{\"codImpuesto\":\"1000\",\"montoImpuesto\":5.08,\"tasaImpuesto\":0.18},{\"codImpuesto\":\"1000\",\"montoImpuesto\":18.99,\"tasaImpuesto\":0.18}],\"detalle\":[{\"cantidadItem\":1.0,\"unidadMedidaItem\":\"NIU\",\"codItem\":\"ESRMCEST00059\",\"nombreItem\":\"Chompa.\",\"precioItem\":138.04,\"precioItemSinIgv\":116.98,\"montoItem\":116.98,\"codAfectacionIgv\":\"10\",\"tasaIgv\":0.18,\"montoIgv\":21.06,\"idOperacion\":\"156407\"},{\"cantidadItem\":1.0,\"unidadMedidaItem\":\"NIU\",\"codItem\":\"ESRMXSCXX0001\",\"nombreItem\":\"Chompa lana\",\"precioItem\":33.32,\"precioItemSinIgv\":28.24,\"montoItem\":28.24,\"codAfectacionIgv\":\"10\",\"tasaIgv\":0.18,\"montoIgv\":5.08,\"idOperacion\":\"156407\"},{\"cantidadItem\":1.0,\"unidadMedidaItem\":\"NIU\",\"codItem\":\"ESRMCEST60001\",\"nombreItem\":\"Hilado\",\"precioItem\":124.5,\"precioItemSinIgv\":105.51,\"montoItem\":105.51,\"codAfectacionIgv\":\"10\",\"tasaIgv\":0.18,\"montoIgv\":18.99,\"idOperacion\":\"156407\"}],\"descuento\":{\"mntDescuentoGlobal\":59.172,\"mntTotalDescuentos\":59.172}}";
    
    System.out.println("Init 2");
    Token token = Token.getToken();
    token.setToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJGYWN0dXJhY3RpdmEiLCJpYXQiOjE1NDAzNDA0ODEsImV4cCI6MTU0MDQyNjg4MSwianRpIjoiZDMzZmJhY2EzY2Q4NGExMjhlMGU4Nzc2MjkwOTA0OGMiLCJjb25zdW1lcktleSI6IjJCU25BSm1qVVVmTVdMdnFaUW1TcnIifQ.UKbj2e2gFhyai0xlg1LcC9jWzBzJRGvBYVvy-xp1jOw");
    HttpResponse response = OAuthUtils.getProtectedResource(oauthDetails, json, "BF");



    
    System.out.println("Fin ");
  }
}
