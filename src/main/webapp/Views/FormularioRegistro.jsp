<%-- 
    Document   : FormularioRegistro
    Created on : 20/06/2025, 9:24:25 a. m.
    Author     : jhoim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>

<jsp:include page="templates/header.jsp">
    <jsp:param name="titulo" value="${param.accion == 'editar' ? 'Editar' : 'Nuevo Personal Médico'}" />
    <jsp:param name="menu" value="usuarios" />
    <jsp:param name="icono" value="fas fa-user-md" />
</jsp:include>
<!-- Formulario de Usuario -->
<div class="card shadow mb-4">
    <div class="card-header py-3">
        <h6 class="m-0 font-weight-bold text-primary">
            ${usuario.idUsuario > 0 ? 'Editar Usuario' : 'Registrar Nuevo Usuario'}
        </h6>
    </div>
    <div class="card-body">
        <form action="${pageContext.request.contextPath}/usuarios" method="post" id="formUsuario">
            <c:if test="${usuario.idUsuario > 0}">
                <input type="hidden" name="idUsuario" value="${usuario.idUsuario}" />
            </c:if>

            <div class="mb-3">
                <label for="nombre" class="form-label">Nombre <span class="text-danger">*</span></label>
                <input type="text" class="form-control" id="nombre" name="nombre" value="${usuario.nombre}" required>
            </div>

            <div class="mb-3">
                <label for="documento" class="form-label">Documento <span class="text-danger">*</span></label>
                <input type="text" class="form-control" id="documento" name="documento" value="${usuario.documento}" required>
            </div>

            <div class="mb-3">
                <label for="correo" class="form-label">Correo <span class="text-danger">*</span></label>
                <input type="email" class="form-control" id="email" name="email" value="${usuario.email}" required>
            </div>

            <div class="mb-3">
                <label for="telefono" class="form-label">Teléfono <span class="text-danger">*</span></label>
                <input type="text" class="form-control" id="telefono" name="telefono" value="${usuario.telefono}" required>
            </div>

            <div class="mb-3">
                <label for="password" class="form-label">Contraseña <span class="text-danger">*</span></label>
                <input type="password" class="form-control" id="password" name="password"
                       ${usuario.idUsuario > 0 ? 'placeholder="Dejar en blanco para mantener la actual"' : 'required'}>
            </div>

            <div class="mt-4 text-center">
                <button type="submit" class="btn btn-primary">
                    <i class="fas fa-save"></i> Guardar
                </button>
                <a href="${pageContext.request.contextPath}/usuarios" class="btn btn-secondary ms-2">
                    <i class="fas fa-arrow-left"></i> Cancelar
                </a>
            </div>
        </form>
    </div>
</div>
           <script>
    document.getElementById('formUsuario').addEventListener('submit', function(event) {
        const nombre = document.getElementById('nombre').value.trim();
        const documento = document.getElementById('documento').value.trim();
        const email = document.getElementById('email').value.trim();
        const telefono = document.getElementById('telefono').value.trim();
        const password = document.getElementById('password').value;
        const isEdit = ${usuario.idUsuario > 0 ? 'true' : 'false'};

        if (!nombre || !documento || !email || !telefono || (!isEdit && !password)) {
            event.preventDefault();
            alert('Por favor, complete todos los campos obligatorios.');
            return false;
        }

        const emailPattern = /^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$/;
        if (!emailPattern.test(email)) {
            event.preventDefault();
            alert('El formato del correo electrónico no es válido.');
            return false;
        }

        if (!/^\\d+$/.test(documento)) {
            event.preventDefault();
            alert('El documento debe contener solo números.');
            return false;
        }

        if (!/^\\d+$/.test(telefono)) {
            event.preventDefault();
            alert('El teléfono debe contener solo números.');
            return false;
        }

        if (!isEdit && password.length < 8 ) {
            event.preventDefault();
            alert('La contraseña debe tener al menos 6 caracteres.');
            return false;
        }

        return true;
    });
</script>
         

