/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dto;

/**
 *
 * @author jhoim
 */
public class LibroModel {

    private int idLibro;
    private String titulo;
    private String autor;
    private String editorial;
    private String anio_publicacion;
    private int idCategoria;
    private int cantidad;
    private String visualizacion;
    private String estado;

    public LibroModel() {
    }

    public LibroModel(int idLibro, String titulo, String autor, String editorial, String anio_publicacion, int idCategoria, int cantidad, String visualizacion, String estado) {
        this.idLibro = idLibro;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.anio_publicacion = anio_publicacion;
        this.idCategoria = idCategoria;
        this.cantidad = cantidad;
        this.visualizacion = visualizacion;
        this.estado = estado;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getAnio_publicacion() {
        return anio_publicacion;
    }

    public void setAnio_publicacion(String anio_publicacion) {
        this.anio_publicacion = anio_publicacion;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getVisualizacion() {
        return visualizacion;
    }

    public void setVisualizacion(String visualizacion) {
        this.visualizacion = visualizacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    

    
}
