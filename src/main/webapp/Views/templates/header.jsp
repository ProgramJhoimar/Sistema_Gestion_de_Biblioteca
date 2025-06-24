<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${param.titulo} - Mi Aplicación</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet">
    <!-- Estilos personalizados -->
    <style>
        body {
            min-height: 100vh;
            display: flex;
            flex-direction: column;
        }

        .navbar {
            margin-bottom: 20px;
        }

        .navbar-brand {
            padding-left: 15px;
        }

        .nav-link {
            color: #ddd;
            border-radius: 0;
            transition: all 0.3s;
        }

        .nav-link:hover {
            background-color: #495057;
            color: white;
        }

        .nav-link.active {
            background-color: #0d6efd;
            color: white;
        }

        .nav-item {
            margin-left: 10px;
        }

        /* Sidebar styling */
        .sidebar {
            background-color: #343a40;
            min-height: calc(100vh - 56px);
            box-shadow: 2px 0 5px rgba(0,0,0,0.1);
        }

        .sidebar .nav-link {
            color: #ddd;
            border-radius: 0;
            transition: all 0.3s;
        }

        .sidebar .nav-link:hover {
            background-color: #495057;
            color: white;
        }

        .sidebar .nav-link.active {
            background-color: #0d6efd;
            color: white;
        }

        .sidebar .nav-link i {
            margin-right: 10px;
        }

        .main-content {
            flex: 1;
            padding: 20px;
            background-color: #f5f5f5;
        }

    </style>
</head>
<body>
    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <div class="container-fluid">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/dashboard">
                <i class="fas fa-syringe"></i> Mi Aplicación
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <!-- Selector de idioma (Opcional si lo necesitas) -->
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/languageSelector">
                            <i class="fas fa-language"></i> Idioma
                        </a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown">
                            <i class="fas fa-user-circle"></i> Usuario
                        </a>
                        <ul class="dropdown-menu dropdown-menu-end">
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/perfil">Perfil</a></li>
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/logout">Cerrar sesión</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <!-- Sidebar -->
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-3 col-lg-2 d-md-block bg-dark sidebar collapse">
                <div class="position-sticky pt-3">
                    <ul class="nav flex-column">
                        <li class="nav-item">
                            <a class="nav-link ${param.menu == 'categorias' ? 'active' : ''}" href="${pageContext.request.contextPath}/categorias">
                                <i class="fas fa-th-list"></i> Categorías
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link ${param.menu == 'libros' ? 'active' : ''}" href="${pageContext.request.contextPath}/libros">
                                <i class="fas fa-book"></i> Libros
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link ${param.menu == 'prestamos' ? 'active' : ''}" href="${pageContext.request.contextPath}/prestamos">
                                <i class="fas fa-exchange-alt"></i> Préstamos
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link ${param.menu == 'usuarios' ? 'active' : ''}" href="${pageContext.request.contextPath}/usuarios">
                                <i class="fas fa-user-cog"></i> Usuarios
                            </a>
                        </li>
                    </ul>
                </div>
            </div>

            <!-- Main content -->
            <div class="col-md-9 ms-sm-auto col-lg-10 px-md-4 main-content">
                <!-- Aquí se incluiría el contenido de cada página específica -->
            </div>
       
  

