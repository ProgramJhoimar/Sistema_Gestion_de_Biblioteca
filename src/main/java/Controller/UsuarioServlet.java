package Controller;

import Dao.UsuarioDAO;
import Dto.UsuarioModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "UsuarioServlet", urlPatterns = {"/usuario"})
public class UsuarioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        if (accion == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        HttpSession session = request.getSession();
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        switch (accion) {
            case "registrar":

                String nombre = request.getParameter("nombre");
                String documento = request.getParameter("documento");
                String correo = request.getParameter("correo");
                String telefono = request.getParameter("telefono");
                String password = request.getParameter("password");

                if (nombre.isEmpty() || documento.isEmpty() || correo.isEmpty() || telefono.isEmpty() || password.isEmpty()) {
                    request.setAttribute("error", "Todos los campos deben estar completos.");
                    request.getRequestDispatcher("/Views/FormularioRegistro.jsp").forward(request, response);
                    return;
                }
                UsuarioModel nuevoUsuario = new UsuarioModel();
                nuevoUsuario.setNombre(nombre);
                nuevoUsuario.setDocumento(documento);
                nuevoUsuario.setCorreo(correo);
                nuevoUsuario.setTelefono(telefono);
                nuevoUsuario.setPassword(password);

                boolean registrado = usuarioDAO.InsertarUsuario(nuevoUsuario);

                if (registrado) {
                    request.setAttribute("mensaje", "Usuario registrado correctamente.");
                    request.getRequestDispatcher("/Views/login.asp").forward(request, response);
                } else {
                    request.setAttribute("error", "Error al registrar el usuario.");
                    request.getRequestDispatcher("/Views/Formulario.jsp").forward(request, response);
                }
                break;
            case "editar":
                UsuarioModel usuarioActual = (UsuarioModel) session.getAttribute("usuario");

                if (usuarioActual == null) {
                    response.sendRedirect(request.getContextPath() + "/login.jsp");
                    return;
                }

                String nombreEditar = request.getParameter("nombre");
                String documentoEditar = request.getParameter("documento");
                String correoEditar = request.getParameter("correo");
                String telefonoEditar = request.getParameter("telefono");
                String passwordEditar = request.getParameter("password");

                usuarioActual.setNombre(nombreEditar);
                usuarioActual.setDocumento(documentoEditar);
                usuarioActual.setCorreo(correoEditar);
                usuarioActual.setTelefono(telefonoEditar);
                usuarioActual.setPassword(passwordEditar);

                boolean actualizado = usuarioDAO.UpdateUsuario(usuarioActual);

                if (actualizado) {
                    session.setAttribute("usuario", usuarioActual);
                    request.setAttribute("mensaje", "Perfil actualizado correctamente.");
                } else {
                    request.setAttribute("error", "No se pudo actualizar el perfil.");
                }

                request.setAttribute("usuario", usuarioActual);
                request.getRequestDispatcher("/Views/PerfilUsuario.jsp").forward(request, response);
                break;

            case "eliminar":
                UsuarioModel usuarioEliminar = (UsuarioModel) session.getAttribute("usuario");
                if (usuarioEliminar != null) {
                    usuarioDAO.DeleteUsuario(usuarioEliminar.getIdUsuario());
                    session.invalidate();
                    response.sendRedirect(request.getContextPath() + "/login.jsp?mensaje=Cuenta eliminada correctamente.");
                } else {
                    response.sendRedirect(request.getContextPath() + "/login.jsp");
                }
                break;

            default:
                response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
    }
}
