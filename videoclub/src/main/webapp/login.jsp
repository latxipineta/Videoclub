<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Login</title>
	<!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
    <link rel="stylesheet" type="text/css" href="css/estilos.css"/>
</head>
  <body class="loginRegistro">
  
  		<c:if test="${error != null}">
	           <div class="divLogin bg-danger bg-opacity-75">
	    </c:if>
	        
	    <c:if test="${error == null}">
	           <div class="divLogin">
	    </c:if>

            <h1 class="mb-5">
            	Login
	            <c:if test="${error != null}">
	            		<span style="color: red;">Error</span>
	        	</c:if>
            </h1>

            <form action="ServletLogin" method="post">

                Usuario: <input type="text" name="user" required><br><br>
                Contraseña: <input type="password" name="password" required><br><br> 
                
                <hr>
                <input type="submit" name="login" value="Login">
                <input type="submit" name="reset" value="Reset">
                <br><br>

                <a href="registro.jsp" class="text-dark">No tienes una cuenta??   Registrate</a>

            </form>

        </div>

  </body>
</html>