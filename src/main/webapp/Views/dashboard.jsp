<%-- 
    Document   : dashboard
    Created on : 23/06/2025, 10:44:00 p. m.
    Author     : jhoim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="templates/header.jsp">
    <jsp:param name="titulo" value="nav.dashboard" />
    <jsp:param name="menu" value="dashboard" />
    <jsp:param name="icono" value="fas fa-tachometer-alt" />
</jsp:include>
 <script>
        window.onload = function () {
            window.location.href = "<%=request.getContextPath()%>/LibroServlet?action=pendientes";
        };
    </script>

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

    <hr class="my-5">

    <h2 class="text-center mb-4 text-white">Libros Prestados</h2>

    <div class="row">
        <c:if test="${not empty sessionScope.librosPrestados}">
            <c:forEach var="libro" items="${sessionScope.librosPrestados}">
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

        <c:if test="${empty sessionScope.librosPrestados}">
            <div class="col-12 text-center">
                <p class="text-white">No hay libros prestados en este momento.</p>
            </div>
        </c:if>
    </div>


   



</div>

<jsp:include page="templates/footer.jsp"/>
