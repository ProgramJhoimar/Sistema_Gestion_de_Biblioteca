<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Iniciar Sesión - Biblioteca</title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet">

    <style>
        body {
            background-color: #f5f5f5;
        }
        .login-card {
            max-width: 400px;
            margin: 100px auto;
            padding: 20px;
            border-radius: 10px;
            background-color: #fff;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }
        .logo-container {
            text-align: center;
            margin-bottom: 20px;
        }
        .logo-container img {
            max-width: 150px;
        }
        .login-title {
            text-align: center;
            margin-bottom: 20px;
            color: #0d6efd;
        }
        .captcha-container {
            margin-bottom: 15px;
            text-align: center;
        }
        .captcha-img {
            border: 1px solid #ddd;
            border-radius: 5px;
            max-width: 100%;
            margin-bottom: 10px;
        }
        .btn-refresh {
            color: #0d6efd;
            cursor: pointer;
            font-size: 20px;
            text-decoration: none;
        }
        .btn-refresh:hover {
            color: #0a58ca;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="login-card">

        <div class="logo-container">
            <img src="${pageContext.request.contextPath}/resources/img/logo.png" alt="Logo Biblioteca" class="img-fluid">
        </div>

        <h2 class="login-title">Iniciar Sesión</h2>

        <c:if test="${not empty error}">
            <div class="alert alert-danger" role="alert">${error}</div>
        </c:if>

        <form action="${pageContext.request.contextPath}/login" method="post" autocomplete="off">
            <div class="mb-3">
                <label for="username" class="form-label">Correo o Usuario</label>
                <div class="input-group">
                    <span class="input-group-text"><i class="fas fa-user"></i></span>
                    <input type="text" class="form-control" id="correo" name="correo" required autofocus autocomplete="username">
                </div>
            </div>

            <div class="mb-3">
                <label for="password" class="form-label">Contraseña</label>
                <div class="input-group">
                    <span class="input-group-text"><i class="fas fa-lock"></i></span>
                    <input type="password" class="form-control" id="password" name="password" required autocomplete="current-password">
                </div>
            </div>

            <div class="mb-3">
                <label class="form-label">Código de Seguridad</label>
                <div class="captcha-container">
                    <c:if test="${not empty captchaImage}">
                        <img src="${captchaImage}" alt="CAPTCHA" class="captcha-img">
                    </c:if>
                    <c:if test="${empty captchaImage}">
                        <p class="text-danger">No se pudo generar el CAPTCHA</p>
                    </c:if>
                    <br>
                    <a href="${pageContext.request.contextPath}/login" class="btn-refresh" title="Generar nuevo CAPTCHA">
                        <i class="fas fa-sync-alt"></i>
                    </a>

                    </a>
                </div>
                <input type="text" class="form-control" id="captcha" name="captcha" placeholder="Escribe el código de la imagen" required autocomplete="off">
            </div>

            <div class="d-grid gap-2">
                <button type="submit" class="btn btn-primary">Ingresar</button>
            </div>
        </form>

    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
