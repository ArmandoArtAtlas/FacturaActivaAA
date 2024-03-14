package com.atlas.business;

import java.util.Calendar;





public class Token
{
  private String token;
  private long time;
  private String token_type;
  private static Token Ctoken;
  
  public static Token getToken() {
    if (Ctoken == null) {
      Ctoken = new Token();
    }
    return Ctoken;
  }

  
  public boolean isStillValid() { return false; }


  
  public String getTokenString() { return this.token; }


  
  public void setToken(String token) {
    this.token = token;
    Ctoken.token = token;
  }

  
  public long getTime() { return this.time; }


  
  public void setTime(long time) { this.time = time; }


  
  public String getToken_type() { return this.token_type; }


  
  public void setToken_type(String token_type) { this.token_type = token_type; }

  
  public void setVars(String a, String b) {
    switch (a) {
      case "access_token":
        this.token = b;
        break;
      case "expires_at":
        this.time = Long.parseLong(b);
        break;
      case "token_type":
        this.token_type = b;
        break;
    } 
  }

  
  public boolean load() { return true; }



  
  public void save() {
    Calendar c = Calendar.getInstance();
    String dia = Integer.toString(c.get(5));
    String mes = Integer.toString(c.get(2) + 1);
    String annio = Integer.toString(c.get(1));
    String dat = dia + "/" + mes + "/" + annio;
  }





  
  public String toString() { return this.token + "\n" + this.time + "\n" + this.token_type + "\n"; }
}
