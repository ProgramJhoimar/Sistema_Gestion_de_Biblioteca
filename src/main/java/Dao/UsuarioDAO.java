/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Dto.UsuarioModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import Model.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author jhoim
 */
public class UsuarioDAO {

    public Connection connection = null;
    public PreparedStatement stmt = null;
    public String sqlCommand = null;
    public boolean response = false;

    public boolean InsertarUsuario(UsuarioModel usuario) {

        try {
            connection = Conexion.getConnection();
            sqlCommand = "CALL sp_InsertarUsuario(?, ?, ?, ?, ?)";
            stmt = connection.prepareStatement(sqlCommand);
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getDocumento());
            stmt.setString(3, usuario.getCorreo());
            stmt.setString(4, usuario.getTelefono());
            stmt.setString(5, usuario.getPassword());

            int request = stmt.executeUpdate();
            response = request > 0;

        } catch (SQLException e) {
            System.err.print("Error en Usuario Dao al insertar" + e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }

                Conexion.closeConnection(connection);
            } catch (SQLException e) {
                System.err.println("Error con el close de conexion" + e.getMessage());

            }
        }
        return response;
    }

    public boolean UpdateUsuario(UsuarioModel usuario) {

        try {
            connection = Conexion.getConnection();
            sqlCommand = "CALL sp_ActualizarUsuario (?, ?, ?, ?, ?, ?)";
            stmt = connection.prepareStatement(sqlCommand);
            stmt.setString(1, usuario.getNombre());
            stmt.setString(3, usuario.getCorreo());
            stmt.setString(4, usuario.getTelefono());
            stmt.setString(5, usuario.getPassword());
            stmt.setInt(6, usuario.getIdUsuario());
            int request = stmt.executeUpdate();
            response = request > 0;

        } catch (Exception e) {
            System.err.println("Error al actualizar en Dao" + e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                Conexion.closeConnection(connection);
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión: " + ex.getMessage());
            }
        }
        return response;
    }

    public boolean DeleteUsuario(int idUsuario) {
        try {

            connection = Conexion.getConnection();
            sqlCommand = "sp_EliminarUsuario";
            stmt = connection.prepareStatement(sqlCommand);
            stmt.setInt(1, idUsuario);
            int request = stmt.executeUpdate();
            response = request > 0;

        } catch (SQLException ex) {
            System.err.println("Error al eliminar usuario: " + ex.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                Conexion.closeConnection(connection);
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión: " + ex.getMessage());
            }
        }
        return response;
    }

    public UsuarioModel validarLogin(String email, String password) {

        UsuarioModel usuario = null;
        ResultSet rs = null;
        try {
            connection = Conexion.getConnection();
            sqlCommand = "SELECT * FROM usuarios WHERE email=? AND password=?";
            stmt = connection.prepareStatement(sqlCommand);
            stmt.setString(1, email);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

            if (rs.next()) {
                usuario = mapearUsuario(rs);
            }

        } catch (SQLException ex) {
            System.err.println("Error al validar login en Dao: " + ex.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                Conexion.closeConnection(connection);
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión: " + ex.getMessage());
            }
        }

        return usuario;
    }

    public UsuarioModel obtenerPorId(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        UsuarioModel usuario = null;

        try {
            conn = Conexion.getConnection();
            String sql = "SELECT * FROM usuarios WHERE idUsuario=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                usuario = mapearUsuario(rs);
            }

        } catch (SQLException ex) {
            System.err.println("Error al obtener usuario: " + ex.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                Conexion.closeConnection(conn);
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión: " + ex.getMessage());
            }
        }

        return usuario;
    }

    private UsuarioModel mapearUsuario(ResultSet rs) throws SQLException {
        UsuarioModel usuario = new UsuarioModel();
        usuario.setIdUsuario(rs.getInt("idUsuario"));
        usuario.setNombre(rs.getString("nombre"));
        usuario.setCorreo(rs.getString("email"));
        usuario.setTelefono(rs.getString("telefono"));
        usuario.setPassword(rs.getString("password"));
        return usuario;
    }

}
