package testing;

import com.google.gson.Gson;

public class TestMain
{
  public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
    MiClase objetoDeMiClase = new MiClase();
    objetoDeMiClase.id = Integer.valueOf(2);
    
    Class<? extends MiClase> objetoDeClassConInfoDeMiClase = (Class)objetoDeMiClase.getClass();
    
    objetoDeClassConInfoDeMiClase.getField("id").set(objetoDeMiClase, Integer.valueOf(3));
    objetoDeClassConInfoDeMiClase.getField("name").set(objetoDeMiClase, Integer.valueOf(3));
    
    Gson gson = new Gson();
    String json = gson.toJson(objetoDeMiClase);
    System.out.println(json);
  }
}
