package com.atlas.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class ReadDocument
{
  FileReader fr;
  BufferedReader br;
  String file;
  
  public ReadDocument(String file) { this.file = file; }


  
  public String[] readWith(String by) {
    String[] temp = null;
    try {
      this.fr = new FileReader(this.file);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } 
    
    this.br = new BufferedReader(this.fr); try {
      String cadena;
      if ((cadena = this.br.readLine()) != null) {
        temp = cadena.split("=");
      }
    } catch (IOException e) {
      e.printStackTrace();
    } 
    return temp;
  }

  
  public void read() {}

  
  public void close() {
    try {
      this.br.close();
      this.fr.close();
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
}
