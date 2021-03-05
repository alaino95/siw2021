<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>EbookExchange</title>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="CSS/loadFileCSS.css" type="text/css">
<link rel="stylesheet" type="text/css" href="CSS/indexCSS.css">
<link href="https://fonts.googleapis.com/css?family=Kaushan+Script" rel="stylesheet">
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/annullaCaricamento.js"></script>
<script src="js/trovaElementi.js"></script>
<%@ include file="navbar.jsp" %>
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
	<br>
	<div class="titleForm">
		<h2>Carica Ebook</h2>
	</div>
	<div class="form">
		<br>
		<form action="carica" method="post" class="carica">
			<label>Scegli i file:</label> <br>
			<input type="file" id="carica" name="files" accept=".pdf" multiple files required></input> <br>
			<br>
			<label>Scegli il genere:</label>
			<select name="generi" id="generi">
				<option>---</option>
				<c:forEach items="${genere}" var="genere">
					<option value="${genere.tipo}">${genere.tipo}</option>
				</c:forEach>
			</select> <br>
			<br>
			<label>Scegli la casa editrice:</label>
			<select name="caseeditrici" id="caseeditrici">
				<option>---</option>
				<c:forEach items="${caseeditrice}" var="caseeditrice">
					<option value="${caseeditrice.nome}">${caseeditrice.nome}</option>
				</c:forEach>
			</select> <br>
			<br>
			<label>Inserisci l'autore dell'ebook:</label> <br>
			<textarea name="autore" cols=30 rows=5 required></textarea> <br>
			<br> 
			<label>Inserisci la trama dell'ebook:</label> <br>
			<textarea name="trama" cols=30 rows=5 required></textarea> <br>
			<br> 
			<input type="submit" class="caricaE" value="Carica ebook">
		</form>
		<br>
		<input type="submit" class="Annulla" value="Annulla" onclick="annulla()">
	</div>
</body>
</html>