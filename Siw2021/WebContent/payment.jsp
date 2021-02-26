<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>EbookExchange</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" type="text/css" href="CSS/indexCSS.css">
<link rel="stylesheet" type="text/css" href="CSS/paymentCSS.css">
<%@ include file="navbar.jsp" %>
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/annullaPagamento.js"></script>
<script src="js/tornaHome.js"></script>
</head>
<body>
	<div class="row">
		<div class="col-75">
			<div class="container">
				<div class="col-50">
					<form action="pagamento" method="post" class="pagamento">
						<h3>Pagamento carta di credito</h3>
						<label for="fname">Carte accettate</label>
						<div class="icon-container">
							<input type="radio" name="gender" value="visa" required> 
							<i class="fa fa-cc-visa" style="color: navy;"></i> 
							<input type="radio" name="gender" value="amex"> 
							<i class="fa fa-cc-amex" style="color: blue;"></i> 
							<input type="radio" name="gender" value="mastercard"> 
							<i class="fa fa-cc-mastercard" style="color: red;"></i> 
							<input type="radio" name="gender" value="orange"> 
							<i class="fa fa-cc-discover" style="color: orange;"></i>
						</div>
						<label for="fname"><i class="fa fa-user"></i> Intestatario</label>
						<input type="text" id="intestatario" name="intestatario" placeholder="nome cognome" required>
						<label for="ccnum">Numero carta di credito</label> 
						<input type="text" id="numero" name="numero" placeholder="numero carta" required>
						<label for="expmonth">Scadenza</label>
						<input type="text" id="scadenza" name="scadenza" placeholder="scadenza" required>
						<label for="cvv">CVV</label> 
						<input type="text" id="cvv" name="cvv" placeholder="cvv" required>
						<div class="row">
							<br>
							<br>
							<input type="submit" value="Continua" class="btn">
						</div>
					</form>
				</div>
			</div>
			<br>
			<div class="col-25">
				<div class="container">
					<h4>
						Carrello <span class="price" style="color: black">
						<i class="fa fa-shopping-cart"></i> <!-- <b>2</b> -->
						</span>
					</h4>
						Totale <span class="price" style="color: black"> <b>€${prezzo}</b></span>
				</div>
			</div>
		</div>
	</div>
	<input type="submit" class="btnA" value="Annulla" onclick="annulla()">
	<input type="submit" class="btnH" value="Torna alla Home" onclick="home()">
</body>
</html>