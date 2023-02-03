<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Registro</title>
	<!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
    <link rel="stylesheet" type="text/css" href="css/estilos.css"/>
</head>
 <body class="loginRegistro">
 
 		<c:if test="${error != null}">
	           <div class="divRegistro bg-danger bg-opacity-75">
	    </c:if>
	        
	    <c:if test="${error == null}">
	           <div class="divRegistro">
	    </c:if>
	    
            <h1 class="mb-5">
            	Registrate
	            <c:if test="${error != null}">
	            		<span style="color: red;">Error</span>
	            		<span style="color: red;" class="fs-5">${error}</span>
	        	</c:if>
            </h1>
            
            <form action="ServletRegistro" method="post">

                <div>
                  Nombre: <input type="text" name="nombre" required><br><br> 
                  Apellidos: <input type="text" name="apellidos" required><br><br>
                  Direccion: <input type="text" name="direccion" required><br><br>
                  Codigo Postal: <input type="text" name="codigoPostal" required><br>
                </div>

                <div>
                  Municipio: <input type="text" name="municipio" required><br><br>
                  Ciudad: <input type="text" name="ciudad" required><br><br>
                  Pais: <input type="text" name="pais" required><br><br>
                  Email: <input type="email" name="correo" required><br><br>
                  Contraseña: <input type="password" name="password" required><br><br>
                  Telefono: <input type="tel" name="telefono" required><br><br>
                </div>

                <div class="col-12 registroBotones">
                  <hr>  
                  <input type="submit" name="registro" value="Registrarse">
                  <input type="submit" name="reset" value="Reset">
                  <a href="login.jsp" class="text-dark">Ya tienes una cuenta?? Logueate</a>
                </div>
                
            </form>

        </div>

 </body>
</html>