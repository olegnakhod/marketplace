<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
<link rel="stylesheet" href="css/bootstrap.css" />
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="container d-flex flex-column  justify-content-center col-10">
		<div class="container  d-flex flex-column  justify-content-center col-8 mt-5">
			<form:form method="POST" action="/addUser" enctype="form-data"
				class="form-card d-flex flex-column col-8 align-self-center">
				<p class="fs-5">Add user</p>
				<input name="name" type="text" placeholder="Name" class="form-group mt-2">
				<input name="surname" type="text" placeholder="Surname" class="form-group mt-2">
				<input name="money" type="number" placeholder="money" class="form-group mt-2">
				<button type="submit" class="btn btn-primary mt-2">Submit</button>
			</form:form>
		</div>

		<div class="container d-flex flex-column  justify-content-center col-8 mt-5">
			<form:form method="POST" action="/addProduct" enctype="form-data"
				class="form-card d-flex flex-column col-8 align-self-center">
				<p class="fs-5">Add product</p>
				<input name="name" type="text" placeholder="Name" class="form-group  mt-2">
				<input name="price" type="number" placeholder="Price" class="form-group mt-2">
				<input name="quantityInStock" type="number" placeholder="Quantity in stock" class="form-group  mt-2">
				<button type="submit" class="btn btn-primary  mt-2">Submit</button>
			</form:form>
		</div>

	</div>
	<script src="js/bootstrap.bundle.js"></script>
</body>
</html>