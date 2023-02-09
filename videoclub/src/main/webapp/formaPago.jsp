<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Forma de Pago</title>
	<!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
    <link rel="stylesheet" type="text/css" href="css/estilos.css"/>
</head>
    <body class="bg-dark">
	   		<c:if test="${user == null}">
		           <jsp:forward page="login.jsp"/>
		    </c:if>
		    
		    <c:if test="${pedido == null}">
		           <jsp:forward page="ServletPago"/>
		    </c:if>
		    
            <!--Header común para todas las páginas excepto login y registro--> 
			<jsp:include page="cabecera.jsp"></jsp:include>

            <main class="w-25 m-5 p-5 text-white bg-secondary border rounded-3">  
				   		
				   		<h1>Forma de Pago</h1>
				   		<form action="ServletPago" method="post" class="row">
				   			
							Forma de Pago: <select name="select">
												  <option value="visa">Bisa</option>
												  <option value="mastercard">MasterCar</option>
												  <option value="bitcoin" selected>Bytkoin</option>
											</select>
							Número de tarjeta: <input type="text" name="numTarjeta">
							Fecha de vencimiento: <input type="text" name="fechaVencimiento">
							Código de seguridad: <input type="text" name="codigoSeguridad">
							
							<h3>Precio total: ${pedido.getPrecio()}€</h3>
				   			
				   			<input type="submit" class="col-8 my-1" name="cancelar" value="CANCELAR">
			           		<input type="submit" class="col-8 my-1" name="formalizar" value="FORMALIZAR COMPRA">
			           	</form>
            </main>
  </body>
</html>