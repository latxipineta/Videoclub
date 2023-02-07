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
		    
		    
            <header>
                <nav class="navbar navbar-expand-lg bg-dark py-3 fs-5">
                    <div class="container-fluid">

                      <a class="navbar-brand text-white mx-3" href="productos.jsp">Inicio</a>

                      <div class="collapse navbar-collapse" id="navbarSupportedContent">

                        <ul class="navbar-nav me-auto">
                          <c:if test="${user.getAdmin() == 1}">
		           				<li class="nav-item">
		                            <a class="nav-link mx-3 text-white" href="listaUsuarios.jsp">Lista Usuarios</a>
		                        </li>
		                        <li class="nav-item">
		                            <a class="nav-link mx-3 text-white" href="listaProveedores.jsp">Lista Proveedores</a>
		                        </li>
		    			  </c:if>
                          <li class="nav-item">
                            <a class="nav-link mx-3 text-white" href="carro.jsp">Carro</a>
                          </li>
                          <li class="nav-item">
                            <a class="nav-link mx-3 text-white" href="listadoCompras.jsp">Listado compras</a>
                          </li>
                          <li class="nav-item">
                            <a class="nav-link mx-3 text-white" href="ServletLogin?borrar=si">Cerrar Sesion</a>
                          </li>
                        </ul>

                      </div>

                    </div>
					<c:if test="${user.getAdmin() == 1}">
						<h4 class="h4Header">${user.getNombre()}-admin</h4>
					</c:if>
                    <c:if test="${user.getAdmin() == 0}">
						<h4 class="h4Header">${user.getNombre()}</h4>
					</c:if>
                  </nav>
            </header>

            <main class="w-100 p-5 text-white">  
            
					<c:if test="${listaLineasPedido == null}">
				           <h1>El carro esta vacio</h1>
				   </c:if>
				   <c:if test="${listaLineasPedido != null}">
				   
				   		<form action="ServletCarro" method="post" class="row">
				   		
				   			<h3 class="col-3">Quieres ponerle un nombre al pedido??</h3>
				   			<input type="text" name="nombrePedido" class="col-3">
				   				
			           		<table class="col-12 my-5 border border-primary-subtle">
			           			<tr class="bg-white text-dark">
			           				<th>Imagen</th>
			           				<th>Producto</th>
			           				<th>Cantidad</th>
			           				<th>Precio Total</th>
			           			</tr>
			           			<c:forEach items="${listaLineasPedido}" var="linea">
			           				<tr>
			           					<td><img src="${linea.value.getImagenProducto()}" width="25%"></td>
									    <td>${linea.value.getNombreProducto()}</td>
				        				<td>${linea.value.getCantidad()}</td>
				        				<td>${linea.value.getPrecioTotal()}€</td>
				        			</tr>
							    </c:forEach>
			           		</table>
			           		<input type="submit" class="col-2" name="vaciarCarro" value="VACIAR CARRO">
			           		<input type="submit" class="col-2" name="pagar" value="HACER PAGO">
			           	</form>
		           		
		  		   </c:if>
				
            </main>
        
  </body>
</html>