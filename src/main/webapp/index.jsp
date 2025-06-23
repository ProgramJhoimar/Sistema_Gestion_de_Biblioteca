<%@page import="java.util.List"%>
<%@page import="Dto.LibroModel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Biblioteca Virtual</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet">

        <style>
            body {
                background: linear-gradient(120deg, #0d6efd, #6f42c1);
                color: #fff;
                min-height: 100vh;
                display: flex;
                flex-direction: column;
            }
            .main-content {
                flex: 1;
                display: flex;
                align-items: center;
                justify-content: center;
                text-align: center;
                padding: 20px;
            }
            .btn-login, .btn-Disponibles {
                margin-top: 20px;
                padding: 10px 30px;
                font-size: 1.1rem;
                border-radius: 30px;
            }
            footer {
                text-align: center;
                padding: 10px 0;
                background-color: rgba(0,0,0,0.2);
                color: #ddd;
                font-size: 0.9rem;
            }
            .card {
                margin-bottom: 20px;
            }
        </style>
    </head>
    <body>

        <nav class="navbar navbar-expand-lg navbar-dark bg-dark shadow-sm">
            <div class="container">
                <a class="navbar-brand" href="#">
                    <i class="fas fa-book-open me-2"></i>Biblioteca Virtual
                </a>
            </div>
        </nav>

        <div class="main-content">
            <div>
                <h1>Bienvenido a la Biblioteca Virtual</h1>
                <p>Sistema de Gestión de Préstamos y Recursos Bibliográficos</p>

<<<<<<< HEAD
<!-- Main Content -->
<div class="main-content">
    <div>
        <div class="library-title">Bienvenido a la Biblioteca Virtual</div>
        <div class="library-subtitle">Sistema de Gestión de Préstamos y Recursos Bibliográficos</div>
        
        <a href="<%= request.getContextPath() %>/login.jsp" class="btn btn-light btn-login">
            <i class="fas fa-sign-in-alt me-2"></i> Iniciar Sesión
        </a>
    </div>
</div>
=======
                <a href="<%= request.getContextPath()%>/login" class="btn btn-light btn-login">
                    <i class="fas fa-sign-in-alt me-2"></i> Iniciar Sesión
                </a>
>>>>>>> main

                <a href="<%= request.getContextPath()%>/LibroServlet" class="btn btn-success btn-Disponibles">
                    <i class="fas fa-book-open me-2"></i> Ver Libros Disponibles
                </a>
            </div>
        </div>

        <div class="container my-5" id="libros">
            <h2 class="text-center mb-4 text-white">Libros Disponibles</h2>

            <div class="row">
                <c:if test="${not empty sessionScope.listaLibrosDisponibles}">
                    <c:forEach var="libro" items="${sessionScope.listaLibrosDisponibles}">
                        <div class="col-md-4">
                            <div class="card h-100">
                                <div class="card-body">
                                    <h5 class="card-title">${libro.titulo}</h5>
                                    <p class="card-text"><strong>Autor:</strong> ${libro.autor}</p>
                                    <c:if test="${not empty libro.visualizacion}">
                                        <img src="${libro.visualizacion}" alt="Portada del libro" class="img-fluid mb-2"/>
                                    </c:if>
                                    <p class="card-text"><strong>Estado:</strong> ${libro.estado}</p>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>

                <c:if test="${empty sessionScope.listaLibrosDisponibles}">
                    <div class="col-12 text-center">
                        <p class="text-white">No hay libros disponibles en este momento.</p>
                    </div>
                </c:if>
            </div>
        </div>

        <footer>
            &copy; 2025 Biblioteca Virtual | Sistema de Gestión
        </footer>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
