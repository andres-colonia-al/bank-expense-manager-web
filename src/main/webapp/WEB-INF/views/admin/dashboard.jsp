<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/views/common/navbar.jsp" %>
<%@ page import="java.util.List, com.acolonia.spring.frontend.model.TransactionDto" %>

<!DOCTYPE html>
<html>
<head>
    <title>Módulo de Administración - Transacciones</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h1 class="mb-4">Módulo de Administración - Transacciones</h1>

        <!-- Filtros -->
        <div class="card mb-4">
            <div class="card-body">
                <h5 class="card-title">Filtros</h5>

                <form action="<c:url value='/admin/filter/category'/>" method="GET" class="mb-3">
                    <div class="input-group">
                        <select name="category" class="form-select" required>
                            <option value="" selected disabled>Filtro por categoría</option>
                            <c:forEach var="category" items="${categories}">
                                <option value="${category}">${category}</option>
                            </c:forEach>
                        </select>
                        <button class="btn btn-primary" type="submit">Filtrar</button>
                    </div>
                </form>
                <form action="<c:url value='/admin/filter/department'/>" method="GET" class="mb-3">
                    <div class="input-group">
                        <select name="deptId" class="form-select" required>
                            <option value="" selected disabled>Filtro por Departamento</option>
                            <c:forEach var="department" items="${departments}">
                                <option value="${department.code}">${department}</option>
                            </c:forEach>
                        </select>
                        <button class="btn btn-primary" type="submit">Filtrar</button>
                    </div>
                </form>
                <form action="<c:url value='/admin/dashboard'/>" method="GET">
                    <button class="btn btn-secondary w-100" type="submit">Ver Todas las Transacciones</button>
                </form>
            </div>
        </div>

        <!-- Lista de Transacciones -->
        <div class="card mt-4">
            <div class="card-body">
                <h3 class="text-center mb-4">Listado de Transacciones</h3>
                <table class="table table-hover table-bordered align-middle">
                    <thead class="table-dark text-center">
                        <tr>
                            <th>ID</th>
                            <th>Nombre</th>
                            <th>Descripción</th>
                            <th>Monto</th>
                            <th>Categoría</th>
                            <th>Fecha y Hora</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:if test="${not empty transactions}">
                            <c:forEach var="transaction" items="${transactions}">
                                <tr>
                                    <td class="text-center">${transaction.idTransaction}</td>
                                    <td class="text-center">${transaction.nameTransaction}</td>
                                    <td class="text-center">${transaction.descriptionTransaction}</td>
                                    <td class="text-center">${transaction.amountTransaction}</td>
                                    <td class="text-center">${transaction.category}</td>

                                    <fmt:parseDate value="${transaction.dateTime}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="parsedDate" />
                                    <td class="text-center">
                                        <fmt:formatDate value="${parsedDate}" pattern="dd MMM yyyy, hh:mm:ss a" />
                                    </td>

                                    <td class="text-center">
                                        <form action="<c:url value='/admin/deleteTransaction'/>" method="POST" style="display:inline;">
                                            <input type="hidden" name="idTransaction" value="${transaction.idTransaction}" />
                                            <button class="btn btn-danger btn-sm" type="submit">Eliminar</button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:if>
                        <c:if test="${empty transactions}">
                            <tr>
                                <td colspan="7" class="text-center">No se encontraron transacciones.</td>
                            </tr>
                        </c:if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>