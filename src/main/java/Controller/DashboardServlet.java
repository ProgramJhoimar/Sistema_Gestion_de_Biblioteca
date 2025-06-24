package Controller;

import Dto.UsuarioModel;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet para el panel de control
 */
@WebServlet(name = "DashboardServlet", urlPatterns = {"/dashboard"})
public class DashboardServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     * Muestra el panel de control
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        UsuarioModel usuario = (UsuarioModel) session.getAttribute("usuario");
        
        // Verificar si el usuario está autenticado
        if (usuario == null) {
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }
        // Redirigir al panel de control
        request.getRequestDispatcher("/views/dashboard.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Por ahora, el dashboard no procesa peticiones POST
        response.sendRedirect(request.getContextPath() + "/dashboard");
    }

    /**
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servlet para el panel de control";
    }
}
