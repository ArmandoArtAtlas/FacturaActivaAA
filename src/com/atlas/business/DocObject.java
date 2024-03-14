package com.atlas.business;

public class DocObject {

    String idTransaccion;
    String json;
    String date;
    Object document;

    public DocObject(String id, String date, Object document, String json) {
        this.idTransaccion = id;
        this.json = json;
        this.date = date;
        this.document = document;
    }

    public String getIdTransaccion() {
        return this.idTransaccion;
    }

    public void setIdTransaccion(String idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public String getJson() {
        return this.json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String toString() {
        return this.idTransaccion + " : " + this.json;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Object getDocument() {
        return this.document;
    }

    public void setDocument(Object document) {
        this.document = document;
    }
}
