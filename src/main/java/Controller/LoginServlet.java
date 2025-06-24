package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Dao.UsuarioLoginDAO;
import Dto.UsuarioModel;
import Util.CaptchaGenerator;

/**
 * Servlet para gestionar el inicio de sesión
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     * Muestra la página de login con CAPTCHA
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Generar un nuevo CAPTCHA para el formulario de login
        String captchaText = CaptchaGenerator.generarTextoCaptcha();
        String captchaImage = CaptchaGenerator.generarImagenCaptcha(captchaText);

        // Guardar el texto del CAPTCHA en la sesión para validarlo después
        HttpSession session = request.getSession();
        session.setAttribute("captchaText", captchaText);

        // Enviar la imagen del CAPTCHA a la página JSP
        request.setAttribute("captchaImage", captchaImage);

        // Redirigir a la página de login
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * Procesa el inicio de sesión
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        String correo = request.getParameter("correo");
        String password = request.getParameter("password");
        String captchaIngresado = request.getParameter("captcha");

        HttpSession session = request.getSession();
        String captchaReal = (String) session.getAttribute("captchaText");

        // Validar CAPTCHA
        if (!CaptchaGenerator.validarCaptcha(captchaIngresado, captchaReal)) {
            request.setAttribute("error", "El código CAPTCHA ingresado es incorrecto");

            // Generar un nuevo CAPTCHA
            String nuevoCaptchaText = CaptchaGenerator.generarTextoCaptcha();
            String nuevoCaptchaImage = CaptchaGenerator.generarImagenCaptcha(nuevoCaptchaText);
            session.setAttribute("captchaText", nuevoCaptchaText);
            request.setAttribute("captchaImage", nuevoCaptchaImage);

            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        // Validar credenciales
        UsuarioLoginDAO usuarioDAO = new UsuarioLoginDAO();
        UsuarioModel usuario = usuarioDAO.validarLogin(correo, password);
        System.out.println("Usuario ingresado: " + correo);
        System.out.println("Contraseña ingresada: " + password);


        if (usuario != null) {
            // Login exitoso
            session.setAttribute("usuario", usuario);
            session.setAttribute("usuarioId", usuario.getIdUsuario());
            session.setAttribute("usuarioNombre", usuario.getNombre());

            // Redirigir al dashboard
            response.sendRedirect(request.getContextPath() + "/dashboard");
        } else {
            // Login fallido
            request.setAttribute("error", "Credenciales incorrectas");

            // Generar un nuevo CAPTCHA
            String nuevoCaptchaText = CaptchaGenerator.generarTextoCaptcha();
            String nuevoCaptchaImage = CaptchaGenerator.generarImagenCaptcha(nuevoCaptchaText);
            session.setAttribute("captchaText", nuevoCaptchaText);
            request.setAttribute("captchaImage", nuevoCaptchaImage);

            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servlet para gestionar el inicio de sesión";
    }
}
