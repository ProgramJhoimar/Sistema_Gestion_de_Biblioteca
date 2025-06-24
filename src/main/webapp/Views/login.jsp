<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Iniciar Sesión - Biblioteca</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet">

    <style>
        body {
            background: linear-gradient(135deg, #0d6efd, #6610f2);
            color: #fff;
            height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
        }
        .login-card {
            background-color: #fff;
            color: #333;
            border-radius: 15px;
            padding: 30px;
            width: 100%;
            max-width: 400px;
            box-shadow: 0 0 20px rgba(0,0,0,0.2);
        }
        .login-card .form-control {
            border-radius: 10px;
        }
        .login-title {
            color: #0d6efd;
            font-weight: bold;
            margin-bottom: 25px;
            text-align: center;
        }
        .btn-primary {
            background-color: #0d6efd;
            border: none;
            border-radius: 10px;
            padding: 10px;
            font-size: 16px;
        }
        .btn-primary:hover {
            background-color: #0b5ed7;
        }
    </style>
</head>
<body>

<div class="login-card">

    <div class="text-center mb-4">
        <img src="${pageContext.request.contextPath}/resources/img/logo.png" alt="Logo Biblioteca" class="img-fluid" style="max-width: 120px;">
    </div>

    <h2 class="login-title">Iniciar Sesión</h2>

    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>

    <form action="${pageContext.request.contextPath}/login" method="post" autocomplete="off">
        <div class="mb-3">
            <label for="username" class="form-label">Correo o Usuario</label>
            <div class="input-group">
                <span class="input-group-text"><i class="fas fa-user"></i></span>
                <input type="text" class="form-control" id="username" name="username" required autofocus autocomplete="username">
            </div>
        </div>

        <div class="mb-3">
            <label for="password" class="form-label">Contraseña</label>
            <div class="input-group">
                <span class="input-group-text"><i class="fas fa-lock"></i></span>
                <input type="password" class="form-control" id="password" name="password" required autocomplete="current-password">
            </div>
        </div>

        <div class="d-grid">
            <button type="submit" class="btn btn-primary">Ingresar</button>
        </div>

        <div class="d-grid">
            <a href="FormularioRegistro.jsp" class="btn btn-btn-outline-primary">Registrarse</a>
        </div>
    </form>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
