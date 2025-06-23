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
        usuario.setEmail(correo);
        usuario.setTelefono(telefono);
        usuario.setPassword(password);

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        boolean exito;

        if (idParam != null && !idParam.trim().isEmpty()) {
            int idUsuario = Integer.parseInt(idParam);
            usuario.setIdUsuario(idUsuario);
            exito = usuarioDAO.UpdateUsuario(usuario);
            request.setAttribute("mensaje", exito ? "Usuario actualizado correctamente" : "Error al actualizar usuario");
        } else {
            exito = usuarioDAO.InsertarUsuario(usuario);
            request.setAttribute("mensaje", exito ? "Usuario creado correctamente" : "Error al crear usuario");
        }

        // Redirigir según el resultado
        if (exito) {
            response.sendRedirect(request.getContextPath() + "/usuarios");
        } else {
            request.setAttribute("usuario", usuario);
            request.getRequestDispatcher("/views/usuario_form.jsp").forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void mostrarFormulario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("usuario", new UsuarioModel());
        request.setAttribute("accion", "nuevo");
        request.getRequestDispatcher("/Views/FormularioRegistro.jsp").forward(request, response);

    }

    private void cargarUsuarioParaEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("idUsuario");
        if (idParam != null && !idParam.trim().isEmpty()) {
            int idUsuario = Integer.parseInt(idParam);

            UsuarioDAO usuarioDAO = new UsuarioDAO();
            UsuarioModel usuario = usuarioDAO.obtenerPorId(idUsuario);

            if (usuario != null) {
                request.setAttribute("usuario", usuario);
                request.setAttribute("accion", "editar");
                request.getRequestDispatcher("/Views/FormularioRegistro.jsp").forward(request, response);
                return;
            }
        }

        // Si no se encontró el usuario o no se proporcionó ID válido
        response.sendRedirect(request.getContextPath() + "/usuarios");
    }

    /**
     * Elimina un usuario
     */
    private void eliminarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("idUsuario");
        if (idParam != null && !idParam.trim().isEmpty()) {
            int idUsuario = Integer.parseInt(idParam);

            UsuarioDAO usuarioDAO = new UsuarioDAO();
            boolean exito = usuarioDAO.DeleteUsuario(idUsuario);

            if (exito) {
                request.setAttribute("mensaje", "Usuario eliminado correctamente");
            } else {
                request.setAttribute("error", "Error al eliminar usuario");
            }
        }

        response.sendRedirect(request.getContextPath() + "/usuarios");
    }

}
