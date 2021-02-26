<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<!-- fa funzionare il sito su tutti i dispositivi e le risoluzioni dello schermo -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>EbookExchange</title>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" type="text/css" href="CSS/indexCSS.css">
<link href="https://fonts.googleapis.com/css?family=Kaushan+Script" rel="stylesheet">
<script src="js/jquery-3.2.1.min.js"></script>
<%@ include file="navbar.jsp"%>
<link rel="stylesheet" href="CSS/profiloP.css" type="text/css">
<c:if test="${publisher==null}">
	<meta http-equiv="refresh" content = "0; URL='http://localhost:8080/Siw2021/login.jsp'"/>
</c:if>
</head>
<body>

	<c:set var="prezzo" value="0" scope="session" />
	<c:set var="credito" value="0" scope="session" />
	<%
		session.setAttribute("lista_ebook", null);
	%>

	<form action="profilo" method="post">
		<input type="submit" value="Visualizza i miei ebook" class="crediti">
	</form>
	<br>
	<table id="table">
		<c:if test="${not empty ebookProfilo}">
			<th>Nome Ebook</th>
			<th>Numero pagine</th>
		</c:if>
		<c:forEach items="${ebookProfilo}" var="ebook">
			<tr>
				<td>${ebook.nome}</td>
				<td>${ebook.n_pagine}</td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<form action="aggiorna" method="post" class="aggiona">
		<input type="submit" value="Visualizza crediti" class="crediti">
		<br> <label>I tuoi crediti sono: ${crediti }</label>
	</form>
	<br>
	<br>
	<form action="eliminaEbook" method="post" class="eliminaEbook">
		<br> <br> <br>
		<label> Indica il nome dell'ebook che vuoi eliminare:</label><br>
		<input type="text" name="nomeebook" class="nomeebook" required></input><br>
		<input type="submit" class="EliminaEbook" value="Elimina ebook">
	</form>
</body>
</html>