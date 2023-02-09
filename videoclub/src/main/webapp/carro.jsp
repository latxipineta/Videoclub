<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Carro</title>
	<!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
    <link rel="stylesheet" type="text/css" href="css/estilos.css"/>
</head>
    <body class="bg-dark">
	   		<c:if test="${user == null}">
		           <jsp:forward page="login.jsp"/>
		    </c:if>
		    
		    
            <!--Header común para todas las páginas excepto login y registro--> 
			<jsp:include page="cabecera.jsp"></jsp:include>

            <main class="w-100 p-5 text-white">  
            
					<c:if test="${listaLineasPedido == null}">
				           <h1>El carro esta vacio</h1>
				   </c:if>
				   
				   <c:if test="${error != null}">
				           <h3 class="text-danger">${error}</h3>
				   </c:if>
				   
				   <c:if test="${listaLineasPedido != null}">
				   
				   		<form action="ServletCarro" method="post" class="row">
				   		
				   			<h3 class="col-3">NOMBRE del pedido (Opcional)</h3>
				   			<input type="text" name="nombrePedido" class="col-3">
				   				
			           		<table class="col-12 my-5 border border-primary-subtle">
			           			<tr class="bg-white text-dark">
			           				<th>Imagen</th>
			           				<th>Producto</th>
			           				<th>Cantidad</th>
			           				<th>Precio Total</th>
			           			</tr>
			           			<c:forEach items="${listaLineasPedido}" var="linea">
			           				<tr class="bg-secondary" style="--bs-bg-opacity: .4;">
			           					<td><img src="${linea.value.getImagenProducto()}" width="25%" class="p-1"></td>
									    <td>${linea.value.getNombreProducto()}</td>
				        				<td>${linea.value.getCantidad()}</td>
				        				<td>${linea.value.getPrecioTotal()}€</td>
				        			</tr>
							    </c:forEach>
			           		</table>
			           		<input type="submit" class="col-6 fw-bold" name="vaciarCarro" value="VACIAR CARRO">
			           		<input type="submit" class="col-6 fw-bold" name="pagar" value="TRAMITAR PEDIDO">
			           	</form>
		           		
		  		   </c:if>
				
            </main> 
  </body>
</html>