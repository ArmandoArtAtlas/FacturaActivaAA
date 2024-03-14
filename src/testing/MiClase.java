package testing;

import java.text.DecimalFormat;

public class MiClase {

    public Integer id;
    public Integer name;
    
    public static void main(String [] arg){
    
    String numero0="10.223456";    
    Double numero1= Double.parseDouble(numero0);
    double numero2;
    
    int nro2=Integer.valueOf(Integer.parseInt(numero0));
    
    numero0=String.format("%.5f",numero1);
    System.out.println(Double.toString(0).format("%.5f",numero1));    
    System.out.println(Math.round(numero1*100.000/100.000));
    DecimalFormat df = new DecimalFormat("###0.00000");
    System.out.println("Rounded Double value (DecimalFormat): "+df.format(numero1));
    System.out.println("Rounded Double value (DecimalFormat): "+nro2);
    

            
    }
    
}
