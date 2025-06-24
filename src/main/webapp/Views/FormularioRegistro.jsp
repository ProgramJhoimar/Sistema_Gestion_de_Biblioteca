<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Registrar Usuario</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2>Registro de Usuario</h2>

    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>

    <c:if test="${not empty mensaje}">
        <div class="alert alert-success">${mensaje}</div>
    </c:if>

    <form action="${pageContext.request.contextPath}/usuario" method="post">
        <!-- PARAMETRO PARA QUE SE EJECUTE EL REGISTRO -->
        <input type="hidden" name="accion" value="registrar">

        <div class="mb-3">
            <label>Nombre</label>
            <input type="text" name="nombre" class="form-control" required>
        </div>

        <div class="mb-3">
            <label>Documento</label>
            <input type="text" name="documento" class="form-control" required>
        </div>

        <div class="mb-3">
            <label>Correo</label>
            <input type="email" name="correo" class="form-control" required>
        </div>

        <div class="mb-3">
            <label>Teléfono</label>
            <input type="text" name="telefono" class="form-control" required>
        </div>

        <div class="mb-3">
            <label>Contraseña</label>
            <input type="password" name="password" class="form-control" required>
        </div>

        <div class="d-grid">
            <button type="submit" class="btn btn-primary">Registrar</button>
        </div>
    </form>
</div>
</body>
</html>
