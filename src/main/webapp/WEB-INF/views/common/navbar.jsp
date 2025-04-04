<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
    <title>Módulo de Administración - Transacciones</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
</head>

<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-4 shadow">
        <div class="container-fluid">
            <a class="navbar-brand fw-bold" href="${pageContext.request.contextPath}/user/dashboard">
                <i class="bi bi-currency-dollar me-2"></i>Gestión de Gastos
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a class="nav-link ${pageContext.request.servletPath.contains('/user/') ? 'active' : ''}"
                            href="${pageContext.request.contextPath}/user/dashboard">
                            <i class="bi bi-receipt me-1"></i>Transacciones
                        </a>
                    </li>
                    <c:if test="${sessionScope.user.roles.contains('ADMIN')}">
                        <li class="nav-item">
                            <a class="nav-link ${pageContext.request.servletPath.contains('/admin/') ? 'active' : ''}"
                                href="${pageContext.request.contextPath}/admin/dashboard">
                                <i class="bi bi-gear me-1"></i>Administración
                            </a>
                        </li>
                    </c:if>
                </ul>
                <ul class="navbar-nav ms-auto">
                    <c:if test="${not empty sessionScope.user}">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/auth/logout">
                                <i class="bi bi-box-arrow-right me-2"></i>Cerrar Sesión
                            </a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" id="userDropdown">
                                <i class="bi bi-person-circle me-1"></i>${sessionScope.user.nameUser}
                            </a>
                        </li>
                    </c:if>
                </ul>
            </div>
        </div>
    </nav>
</body>

</html>
