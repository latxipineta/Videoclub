<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Detalles Pedido</title>
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
            
					<table class="col-12 my-5 border border-primary-subtle">
						<tr class="bg-success text-white">
								<th>Producto</th>
								<th>Nombre</th>
								<th>Cantidad</th>
								<th>Precio Total</th>
						</tr>
						<c:forEach items="${listaLineasPedido}" var="lineaPedido">
								<tr class="bg-success" style="--bs-bg-opacity: .1;">
									<td class="p-5"><img src="${lineaPedido.getImagenProducto()}" width="25%"></td>
									<td class="p-5">${lineaPedido.getNombreProducto()}</td>
									<td class="p-5">${lineaPedido.getCantidad()}</td>
									<td class="p-5">${lineaPedido.getPrecioTotal()}€</td>
								</tr>
			        	</c:forEach>        	
					</table>			
            </main>
  </body>
</html>