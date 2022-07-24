<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Marketplace</title>
<link rel="stylesheet" href="css/bootstrap.css" />
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="container col-10 ">
		<div class="container mt-5">
			<table class="table table-striped col-10">
				<thead class="thead-dark ">
					<tr>
						<th scope="col">ID</th>
						<th scope="col">Name</th>
						<th scope="col">Price</th>
						<th scope="col">Quantity in stock</th>
						<th scope="col">Delete</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="product" items="${productsViewer}">
						<tr>
							<td>${product.id}</td>
							<td>${product.name}</td>
							<td>${product.price}</td>
							<td>${product.quantityInStock}</td>
							<td><a href="deleteProduct?productID=${product.id}">delete</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="container mt-5">

			<table class="table table-striped col-10">
				<thead class="thead-dark ">
					<tr>
						<th scope="col">ID</th>
						<th scope="col">Name</th>
						<th scope="col">Surname</th>
						<th scope="col">Money</th>
						<th scope="col"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="user" items="${usersViewer}">
						<tr>
							<td>${user.id}</td>
							<td>${user.name}</td>
							<td>${user.surname}</td>
							<td>${user.money}</td>
							<td></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="container mt-5">
			<form:form method="POST" action="/bayProduct" enctype="form-data"
				class="form-card d-flex flex-column col-8 align-self-center">
				<p class="fs-5">Bay product</p>
				<input name="userID" type="number" placeholder="User ID" class="form-group  mt-2">
				<input name="productID" type="number" placeholder="Product ID" class="form-group  mt-2">
				<button type="submit" class="btn btn-primary mt-2 col-5">Submit</button>
			</form:form>
		</div>
	</div>
	<script src="js/bootstrap.bundle.js"></script>
</body>
</html>