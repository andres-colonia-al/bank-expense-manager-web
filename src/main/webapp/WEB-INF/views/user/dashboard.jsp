<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/common/navbar.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard de Transacciones</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
    <div class="container mt-5">

        <!-- Título -->
        <div class="mb-5 text-center">
            <h1>Dashboard de Transacciones</h1>
            <hr>
        </div>

        <!-- Formulario para agregar transacción -->
        <div class="card shadow-sm mb-5">
            <div class="card-body">
                <h3 class="card-title mb-4">Agregar Transacción</h3>
                <form action="<c:url value='/user/addTransaction'/>" method="post" class="row g-3">

                    <div class="col-md-6">
                        <label for="nameTransaction" class="form-label">Nombre de la Transacción</label>
                        <input type="text" name="nameTransaction" class="form-control" required>
                    </div>

                    <div class="col-md-6">
                        <label for="descriptionTransaction" class="form-label">Descripción</label>
                        <input type="text" name="descriptionTransaction" class="form-control" required>
                    </div>

                    <div class="col-md-6">
                        <label for="amountTransaction" class="form-label">Monto</label>
                        <input type="number" name="amountTransaction" class="form-control" step="0.01" required>
                    </div>

                    <div class="col-md-6">
                        <label for="category" class="form-label">Categoría</label>
                        <select name="category" class="form-select" required>
                            <option value="" selected disabled>Selecciona una categoría</option>
                            <c:forEach var="category" items="${categories}">
                                <option value="${category}">${category}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="col-12 text-center">
                        <button type="submit" class="btn btn-primary mt-3">Agregar Transacción</button>
                    </div>
                </form>
            </div>
        </div>

        <!-- Tabla de transacciones -->
        <div class="card mt-4">
            <div class="card-body">
                <h3 class="text-center mb-4">Listado de Transacciones</h3>
                <table class="table table-striped table-bordered align-middle">
                    <thead class="table-dark text-center">
                        <tr>
                            <th>Nombre</th>
                            <th>Descripción</th>
                            <th>Monto</th>
                            <th>Categoría</th>
                            <th>Fecha y Hora</th>
                            <th>Acción</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="transaction" items="${transactions}">
                            <tr>
                                <td class="text-center">${transaction.nameTransaction}</td>
                                <td class="text-center">${transaction.descriptionTransaction}</td>
                                <td class="text-center">${transaction.amountTransaction}</td>
                                <td class="text-center">${transaction.category}</td>
                                <td class="text-center">
                                    <fmt:parseDate value="${transaction.dateTime}" pattern="yyyy-MM-dd'T'HH:mm:ss"
                                        var="parsedDate" />
                                    <fmt:formatDate value="${parsedDate}" pattern="dd MMM yyyy, hh:mm:ss a" />
                                </td>
                                <td class="text-center">
                                    <form action="<c:url value='/user/deleteTransaction'/>" method="post">
                                        <input type="hidden" name="idTransaction" value="${transaction.idTransaction}">
                                        <button type="submit" class="btn btn-danger btn-sm">Eliminar</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>