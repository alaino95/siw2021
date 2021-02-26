<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title></title>
</head>
<body>
	<nav class="w3-bar w3-dark-grey ">
		<a href="index.jsp" class="w3-button w3-bar-item">Home</a>
		<c:if test="${user == null && publisher==null}">
			<a href="registrazion.jsp" class="w3-button w3-bar-item">Registrati</a>
		</c:if>
		<c:if test="${user == null && publisher==null}">
			<a href="login.jsp" class="w3-button w3-bar-item ">Login</a>
		</c:if>
		<a href="loadFiles.jsp" class="w3-button w3-bar-item">Ebook</a>
		<a href="downloadFile.jsp" class="w3-button w3-bar-item">Download</a>
		<c:if test="${publisher != null}">
			<a href="profiloPublisher.jsp" class="w3-button w3-bar-item">Profilo</a> 
		</c:if>
		<c:if test="${publisher != null}">
			<label class="benvenuto">Benvenuto ${publisher}</label>
			<a class="w3-button w3-bar-item" href="login?logout=true">Logout</a>
		</c:if>
		<c:if test="${user != null}">
			<label class="benvenuto">Benvenuto ${user}</label>
			<a class="w3-button w3-bar-item" href="login?logout=true">Logout</a>
		</c:if>
	</nav>
</body>
</html>