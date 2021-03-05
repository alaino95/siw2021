<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>EbookExchange</title>
<link href="https://fonts.googleapis.com/css?family=Kaushan+Script" rel="stylesheet">
<script src="js/jquery-3.2.1.min.js"></script>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" type="text/css" href="CSS/indexCSS.css">
<%@ include file="navbar.jsp"%>
</head>
<body>

	<c:set var="prezzo" value="0" scope="session" />
	<c:set var="credito" value="0" scope="session" />
	<%
		session.setAttribute("lista_ebook", null);
	%>
	
	<section class="w3-container w3-center w3-content" style="max-width: 600px">
		<h2 class="w3-wide ">EbookExchange</h2>
		<p class="w3-opacity">
			<i>I libri che vuoi, quando e dove vuoi.</i>
		</p>
		<p class="descrizione">Benvenuto su EbookExchange, dove è possibile trovare
			tanti libri, in formato pdf, pronti per essere scaricati e letti su 
			qualsiasi dispositivo.
		</p>
	</section>
	<section>
		<img class="background w3-container w3-center w3-content" src="images/sfondo.jpg" style="width: 600px">
		<img class="background w3-container w3-center w3-content" src="images/sfondo2.jpg" style="width: 600px"> 
		<img class="background w3-container w3-center w3-content" src="images/sfondo3.jpg" style="width: 600px">
	</section>
	<script>
		//Cambia immagine ogni 4 secondi
		var myIndex = 0;
		carousel();

		function carousel() {
			var i;
			var x = document.getElementsByClassName("background");
			for (i = 0; i < x.length; i++) {
				x[i].style.display = "none";
			}
			myIndex++;
			if (myIndex > x.length) {
				myIndex = 1
			}
			x[myIndex - 1].style.display = "block";
			setTimeout(carousel, 4000);
		}
	</script>
</body>
</html>