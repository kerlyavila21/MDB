package com.example.kerly.myapplicationmdb.models;

import com.google.firebase.database.IgnoreExtraProperties;

import com.google.firebase.database.Exclude;


public class Maquillaje  {


    private String Color;
    private String Datos_Adicional_Producto;
    private String Descripcion;
    private String Peso_Neto;
    private String Precio;
    private String Producto_Nombre;

    public Maquillaje() {
    }

    public Maquillaje(String color, String datos_Adicional_Producto, String descripcion, String peso_Neto, String precio, String producto_Nombre) {
        Color = color;
        Datos_Adicional_Producto = datos_Adicional_Producto;
        Descripcion = descripcion;
        Peso_Neto = peso_Neto;
        Precio = precio;
        Producto_Nombre = producto_Nombre;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public String getDatos_Adicional_Producto() {
        return Datos_Adicional_Producto;
    }

    public void setDatos_Adicional_Producto(String datos_Adicional_Producto) {
        Datos_Adicional_Producto = datos_Adicional_Producto;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getPeso_Neto() {
        return Peso_Neto;
    }

    public void setPeso_Neto(String peso_Neto) {
        Peso_Neto = peso_Neto;
    }

    public String getPrecio() {
        return Precio;
    }

    public void setPrecio(String precio) {
        Precio = precio;
    }

    public String getCodigo() {
        String codigo = Producto_Nombre.toLowerCase();
        return codigo;
    }

    public String getProducto_Nombre() {
        return Producto_Nombre;
    }


    public void setProducto_Nombre(String producto_Nombre) {
        Producto_Nombre = producto_Nombre;
    }
}