/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dto;

/**
 *
 * @author jhoim
 */
public class UsuarioModel {

    private int idUsuario;
<<<<<<< HEAD
    private String nombre;
    private String documento;
=======
    private String documento;
    public String nombre;
>>>>>>> main
    private String telefono;
    private String correo;
    private String password;

    public UsuarioModel() {
    }

<<<<<<< HEAD

    public UsuarioModel(String password, String correo, String telefono, String documento, String nombre, int idUsuario) {
        this.password = password;
        this.correo = correo;
        this.telefono = telefono;
        this.documento = documento;
        this.nombre = nombre;
=======
    public UsuarioModel(String password, String correo, String telefono,String nombre, int idUsuario, String documento) {
        this.password = password;
        this.correo = correo;
        this.telefono = telefono;
        this.nombre = nombre;
        this.documento = documento;
  
>>>>>>> main
        this.idUsuario = idUsuario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

<<<<<<< HEAD
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

=======
    public void setPassword(String password) {
        this.password = password;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
>>>>>>> main
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
<<<<<<< HEAD

=======
>>>>>>> main
    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }
<<<<<<< HEAD

    public String getTelefono() {
        return telefono;
=======
    public int getIdUsuario() {
        return idUsuario;
>>>>>>> main
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
