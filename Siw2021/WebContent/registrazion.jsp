<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>EbookExchange</title>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="js/jquery-3.2.1.min.js"></script>
<%@ include file="navbar.jsp" %>
<script src="js/tipoRegistrazione.js"></script>
<link rel="stylesheet" type="text/css" href="CSS/reg.css">
<link rel="stylesheet" type="text/css" href="CSS/indexCSS.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
	<br> <br>
    <div id="reg">
    <div class="container">
        <div id="login-row" class="row justify-content-center align-items-center">
			<div id="login-column" class="col-md-6">
               <div class="w3-panel w3-border w3-round-xxlarge">
                    <div id="login-box" class="col-md-12">
                            <h3 class="text-center text-info">Registrazione</h3>
						<form action="" >
					 		<input type="radio" name="gender" value="Publisher" id="publisher"> Publisher  &emsp;&emsp;&emsp;
					 		<input type="radio" name="gender" value="User" id="user"> User<br>
						</form>
						<form action="inviaDatiUtente" method="post" name="inviaDatiUtente" id="regi">
                            <div class="registra">
                            </div>
                            <div class="form-group">
                                <label for="password" class="text-info">Password:</label><br>
                                <input type="password" name="password" id="password"  class="form-control" 
                                	aria-describedby="emailHelp" placeholder="Enter Password" required><br>
                            	<input type="password" name="password1" id="password1"  class="form-control" 
                            		aria-describedby="emailHelp" placeholder="Confirm password" required>
                            </div>
                            <div class="form-group">
                                <input type="submit" name="submit" class="btn btn-info btn-md" value="Registrati">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        </div>
    </div>
</body>
