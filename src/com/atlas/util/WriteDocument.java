package com.atlas.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;




public class WriteDocument
{
  public static String LogName = "ArtAtlasFacturador";
  
  FileWriter fichero = null;
  PrintWriter pw = null;
  String path = null;
  
  public WriteDocument(String path) {
    this.path = path;
    try {
      this.fichero = new FileWriter(path);
    } catch (IOException e) {
      Logger.getLogger(LogName).log(Level.SEVERE, "Error en constructor", e);
      
      e.printStackTrace();
    } 
    this.pw = new PrintWriter(this.fichero);
  }
  
  public boolean write(String var) {
    boolean fl = true;
    try {
      this.pw.println(var);
    }
    catch (Exception e) {
      fl = false;
      Logger.getLogger(LogName).log(Level.SEVERE, "Error en write", e);
      
      e.printStackTrace();
    } 
    return fl;
  }
  
  public boolean writeWith(String bt, String... vars) {
    boolean fl = true;
    try {
      for (int i = 0; i < vars.length - 1; i++) {
        this.pw.print(vars[i] + bt);
      }
      this.pw.println(vars[vars.length - 1]);
    } catch (Exception e) {
      fl = false;
      Logger.getLogger(LogName).log(Level.SEVERE, "Error en write", e);
      
      e.printStackTrace();
    } 
    return fl;
  }
  
  public boolean close() {
    boolean fl = true;
    try {
      if (null != this.fichero) {
        this.fichero.close();
      }
      this.pw.close();
    } catch (Exception e2) {
      fl = false;
      Logger.getLogger(LogName).log(Level.SEVERE, "Error en cerrado de fichero", e2);
      
      e2.printStackTrace();
    } 
    return fl;
  }
}
