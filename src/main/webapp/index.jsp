<%-- 
    Document   : index
    Created on : 19/06/2025, 11:05:10 a. m.
    Author     : jhoim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sistema de Biblioteca</title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Font Awesome -->
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
        .library-title {
            font-size: 3rem;
            font-weight: bold;
        }
        .library-subtitle {
            font-size: 1.2rem;
            margin-top: 10px;
            color: #ddd;
        }
        .btn-login {
            margin-top: 30px;
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
    </style>
</head>
<body>

<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark shadow-sm">
    <div class="container">
        <a class="navbar-brand" href="#">
            <i class="fas fa-book-open me-2"></i>Biblioteca Virtual
        </a>
    </div>
</nav>

<!-- Main Content -->
<div class="main-content">
    <div>
        <div class="library-title">Bienvenido a la Biblioteca Virtual</div>
        <div class="library-subtitle">Sistema de Gestión de Préstamos y Recursos Bibliográficos</div>
        
        <a href="<%= request.getContextPath() %>/login" class="btn btn-light btn-login">
            <i class="fas fa-sign-in-alt me-2"></i> Iniciar Sesión
        </a>
    </div>
</div>

<!-- Footer -->
<footer>
    &copy; 2025 Biblioteca Virtual | Sistema de Gestión
</footer>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
