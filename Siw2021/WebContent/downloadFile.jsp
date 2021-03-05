<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>EbookExchange</title>
<link href="https://fonts.googleapis.com/css?family=Kaushan+Script" rel="stylesheet">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="CSS/downloadFileCSS.css" type="text/css">
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/trovaElementi.js"></script>
<script src="js/salvaRec.js"></script>
<%@ include file="navbar.jsp"%>
</head>
<body>
	<div class="title">
		<h2>Seleziona le proprietà degli ebook</h2>
	</div>
	<div class="form">
		<form action="ricercaNome" method="post">
			<div id="ricerca">
				<input type="text" placeholder="Search.." class="searchBar" name="searchBar"> 
				<input type="submit" value="Cerca per nome" class="search">
			</div>
		</form>
		<br>
		<form action="ricercaAutore" method="post">
			<div id="ricerca">
				<input type="text" placeholder="Search.." class="searchBar" name="searchBar"> 
				<input type="submit" value="Cerca per autore" class="search">
			</div>
		</form>
		<br>
		<form action="ebook" method="post">
			<label>Casa editrice: </label> 
			<select name="caseeditrici" id="caseeditrici">
				<option>---</option>
				<c:forEach items="${caseeditrice}" var="caseeditrice">
					<option value="${caseeditrice.nome}">${caseeditrice.nome}</option>
				</c:forEach>
			</select>
			<label>Genere: </label>
			<select name="generi" id="generi">
				<option>---</option>
				<c:forEach items="${genere}" var="genere">
					<option value="${genere.tipo}">${genere.tipo}</option>
				</c:forEach>
			</select>
			 <input type="submit" value="Cerca" class="searchC"> <br>
			<br>
			<div id="ebook">
				<table id="table">
					<c:if test="${not empty ebooks}">
						<th>Nome</th>
						<th>Numero pagine</th>
						<th>Prezzo in crediti</th>
						<th>Prezzo in euro</th>
						<th>Trama</th>
						<th>Autore</th>
					</c:if>
					<c:forEach items="${ebooks}" var="ebook">
						<tr>
							<td>${ebook.nome}</td>
							<td>${ebook.n_pagine}</td>
							<td>${ebook.prezzo_crediti} crediti</td>
							<td>${ebook.prezzo} euro</td>
							<td>${ebook.trama}</td>
							<td>${ebook.autore}</td>
							<td><input type="checkbox" class="${ebook.id}"
								onclick="salvaDati('${ebook.prezzo}','${ebook.prezzo_crediti}','${ebook.id}')"></td>
							<td><input type="button" value="Recensioni" class="rec"
								onclick="caricaRecensioni('${ebook.id}')"></td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<br>
			<div id="ebook">
				<table id="table">
					<c:if test="${not empty trovati}">
						<th>Nome</th>
						<th>Numero pagine</th>
						<th>Prezzo in crediti</th>
						<th>Prezzo in euro</th>
						<th>Trama</th>
						<th>Autore</th>
					</c:if>

					<c:forEach items="${trovati}" var="ebook">
						<tr>
							<td>${ebook.nome}</td>
							<td>${ebook.n_pagine}</td>
							<td>${ebook.prezzo_crediti} crediti</td>
							<td>${ebook.prezzo} euro</td>
							<td>${ebook.trama}</td>
							<td>${ebook.autore}</td>
							<td><input type="checkbox" class="${ebook.id}"
								onclick="salvaDati('${ebook.prezzo}','${ebook.prezzo_crediti}','${ebook.id}')"></td>
							<td><input type="button" value="Recensioni" class="rec"
								onclick="caricaRecensioni('${ebook.id}')"></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</form>
		<div id="recensioni"></div>
		<br> <br> <br>
		<div id="totale">
			<p class="paragrafo"></p>
		</div>
		<c:if test="${publisher!=null}">
			<form action="pagacrediti" method="post" class="trasferisci">
				<input type="submit" value="Paga con Crediti" class="btn">
			</form>
		</c:if>
		<form action="trasferisci" method="post" class="trasferisci">
			<button type="submit" class=" btn btn-block mybtn btn-primary tx-tfm">pagaConCarta</button>
			<br>
		</form>
	</div>
</body>
</html>