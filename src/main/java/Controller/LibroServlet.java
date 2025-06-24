/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Dao.LibroDAO;
import Dto.LibroModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
@WebServlet(name = "LibroServlet", urlPatterns = {"/LibroServlet"})
public class LibroServlet extends HttpServlet {

    public LibroDAO libroDao = new LibroDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("iniciar".equals(action)) {
            List<LibroModel> librosDisponibles = libroDao.listarDisponibles();
            request.getSession().setAttribute("listaLibrosDisponibles", librosDisponibles);
            response.sendRedirect("index.jsp?loaded=true");
        } else if ("pendientes".equals(action)) {
            List<LibroModel> librosPrestados = libroDao.listaPrestados();
            request.getSession().setAttribute("librosPrestados", librosPrestados);
            response.sendRedirect("/Views/dashboard.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
