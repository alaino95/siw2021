<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>EbookExchange</title>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js"></script>
<script src="js/jquery-3.2.1.min.js" type="text/javascript"></script>
<script src="js/tipoLogin.js" type="text/javascript"></script>
<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" 
	integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
<link href="https://fonts.googleapis.com/css?family=Kaushan+Script" rel="stylesheet">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" type="text/css" href="CSS/logCSS.css">
<link rel="stylesheet" type="text/css" href="CSS/indexCSS.css">
<%@ include file="navbar.jsp" %>
</head>
<body>
	<c:set var="prezzo" value="0" scope="session" />
	<c:set var="credito" value="0" scope="session" />
	<%
		session.setAttribute("lista_ebook", null);
	%>
	<div class="container">
		<div class="row">
			<div class="col-md-5 mx-auto">
			<div id="first">
				<div class="myform form ">
					<div class="logo mb-3">
						<div class="col-md-12 text-center">
							<h1>Login</h1>
						</div>
						<form action="" >
 							<input type="radio" name="gender" value="Publisher" id="publisher"> Publisher  &emsp;&emsp;&emsp;
 						 	<input type="radio" name="gender" value="User" id="user"> User<br>
						</form> 
					</div>
					<form action="login" method="post" class="login">
						<div id="form-group1">
						</div> 
                        	<div class="form-group">
                              	<label for="exampleInputEmail1">Password</label>
                              	<input type="password" name="password" id="password"  class="form-control" 
                              		aria-describedby="emailHelp" placeholder="Enter Password" required>
                           </div>
                           <div class="form-group">
                              <p class="text-center">By signing up you accept our <a href="#">Terms Of Use</a></p>
                           </div>
                           <div class="col-md-12 text-center ">
                              <button type="submit" class=" btn btn-block mybtn btn-primary tx-tfm">Login</button>
                           </div>
					</form>          
				</div>
			</div>
		</div>
      </div>    
   </div>       
</body>
</html>