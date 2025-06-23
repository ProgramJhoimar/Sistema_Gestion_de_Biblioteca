package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Dto.UsuarioModel;
import Model.Conexion;

public class UsuarioLoginDAO {

    public UsuarioModel validarLogin(String username, String password) {
        UsuarioModel usuario = null;

        String sql = "SELECT * FROM usuarios WHERE (email = ? OR nombre = ?) AND password = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username); // Puede ser email
            ps.setString(2, username); // o puede ser nombre
            ps.setString(3, password); // Verifica que sea igual al de la BD

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                usuario = new UsuarioModel();
                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setTelefono(rs.getString("telefono"));
                usuario.setEmail(rs.getString("email"));
                usuario.setPassword(rs.getString("password"));
                usuario.setPerfil(rs.getString("perfil")); // si tu modelo lo tiene
            } else {
                System.out.println("⚠ No se encontró ningún usuario con esas credenciales.");
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al validar login: " + e.getMessage());
        }

        return usuario;
    }
}
