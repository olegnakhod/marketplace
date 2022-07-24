<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bucket</title>
<link rel="stylesheet" href="css/bootstrap.css" />
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="container d-flex flex-column  justify-content-center col-10">
		<div class="container">
			<c:choose>
				<c:when test="${mode == 'VIEW_USER'}">
					<div class="d-flex justify-content-center mt-3 col-10">
						<table class="table table-striped col-10">
							<thead class="thead-dark ">
								<tr>
									<th scope="col">ID</th>
									<th scope="col">Name</th>
									<th scope="col">Surname</th>
									<th scope="col">Money</th>
									<th scope="col">View product</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="user" items="${usersViewer}">
									<tr>
										<td>${user.id}</td>
										<td>${user.name}</td>
										<td>${user.surname}</td>
										<td>${user.money}</td>
										<td><a href="viewAllProductForUser?userId=${user.id}">viewProduct</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</c:when>
				<c:when test="${mode == 'VIEW_PRODUCT'}">
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
									<td><a href="deleteBucket?productID=${product.id}">delete</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
		</div>
		<a href="bucket"> back</a>
		</c:when>
		</c:choose>
	</div>
	</div>

	<script src="js/bootstrap.bundle.js"></script>
</body>
</html>