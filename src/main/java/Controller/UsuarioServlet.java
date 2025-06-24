/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Dao.UsuarioDAO;
import Dto.UsuarioModel;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jhoim
 */
@WebServlet(name = "UsuarioServlet", urlPatterns = {"/UsuarioServlet"})
public class UsuarioServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UsuarioServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UsuarioServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        UsuarioModel usuarioActual = (UsuarioModel) session.getAttribute("usuario");

        // Verificar si el usuario está autenticado
        if (usuarioActual == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Obtener parámetros y determinar acción
        String accion = request.getParameter("accion");
        if (accion == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Accion no especificada");
            return;
        }

        switch (accion) {
            case "nuevo":
                mostrarFormulario(request, response);
                break;
            case "editar":
                cargarUsuarioParaEditar(request, response);
                break;
            case "eliminar":
                eliminarUsuario(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Accion no válida");
                break;
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        UsuarioModel usuarioActual = (UsuarioModel) session.getAttribute("usuario");

        // Verificar si el usuario está autenticado
        if (usuarioActual == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Obtener parámetros del formulario
        String idParam = request.getParameter("idUsuario");
        String nombre = request.getParameter("nombre");
        String documento = request.getParameter("documento");
        String correo = request.getParameter("correo");
        String telefono = request.getParameter("telefono");
        String password = request.getParameter("password");

        // Validación básica
        if (nombre == null || nombre.trim().isEmpty()
                || documento == null || documento.trim().isEmpty()
                || correo == null || correo.trim().isEmpty()
                || telefono == null || telefono.trim().isEmpty()
                || password == null || password.trim().isEmpty()) {

            request.setAttribute("error", "Todos los campos deben ser completados.");
            request.getRequestDispatcher("/views/FormularioRegistro.jsp").forward(request, response);
            return;
        }

        // Crear objeto Usuario
        UsuarioModel usuario = new UsuarioModel();
        usuario.setNombre(nombre);
        usuario.setCorreo(correo);
        usuario.setTelefono(telefono);
        usuario.setPassword(password);

=======
>>>>>>> main
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
