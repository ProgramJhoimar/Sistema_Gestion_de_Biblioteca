/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Dto.LibroModel;
import Model.Conexion;
import java.io.Console;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * f
 *
 * @author jhoim
 */
public class LibroDAO {

    public Connection connection = null;
    public PreparedStatement stmt = null;
    public String sqlCommand = null;
    public boolean response = false;
    public ResultSet rs;

    public List<LibroModel> listarDisponibles() {

        List<LibroModel> librosDisponibles = new ArrayList<>();
        try {
            connection = Conexion.getConnection();
            stmt = connection.prepareStatement("SELECT * FROM libros WHERE libros.estado = 'Disponible'");
            rs = stmt.executeQuery();

            while (rs.next()) {
                LibroModel libro = mapeoLibro(rs);
                librosDisponibles.add(libro);
            }

        } catch (SQLException ex) {
            System.err.println("Error al obtener la lista en Modelo: " + ex.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                Conexion.closeConnection(connection);
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión de Modelo: " + ex.getMessage());
            }
        }
        System.out.println("lista" + librosDisponibles);
        return librosDisponibles;
    }

    public List<LibroModel> listaPrestados() {
        List<LibroModel> lsP = new ArrayList<>();

        try {
            connection = Conexion.getConnection();
            stmt = connection.prepareStatement("SELECT * FROM libros WHERE libros.estado = 'Prestado'");
            rs = stmt.executeQuery();

            while (rs.next()) {
                LibroModel lb = mapeoLibro(rs);
                lsP.add(lb);
            }
        } catch (Exception e) {
            System.err.println("Error al obtener la lista en Modelo: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                Conexion.closeConnection(connection);
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión de Modelo: " + ex.getMessage());
            }
        }
        return lsP;
    }
    
    

    private LibroModel mapeoLibro(ResultSet rs) throws SQLException {
        LibroModel libro = new LibroModel();
        libro.setIdLibro(rs.getInt("idLibro"));
        libro.setTitulo(rs.getString("titulo"));
        libro.setAutor(rs.getString("autor"));
        libro.setEditorial(rs.getString("editorial"));
        libro.setAnio_publicacion(rs.getString("anio_publicacion"));
        libro.setIdCategoria(rs.getInt("idCategoria"));
        libro.setCantidad(rs.getInt("cantidad"));
        libro.setVisualizacion(rs.getString("visualizacion"));
        libro.setEstado(rs.getString("estado"));
        return libro;
    }

}
