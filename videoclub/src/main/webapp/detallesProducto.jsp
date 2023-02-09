<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Detalle Producto</title>
	<!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
    <link rel="stylesheet" type="text/css" href="css/estilos.css"/>
</head>
    <body class="bodyDetalles">
	   		<c:if test="${user == null}">
		           <jsp:forward page="login.jsp"/>
		    </c:if>
		    
		    <c:if test="${producto == null}">
		           <jsp:forward page="productos.jsp"/>
		    </c:if>
		    
            <!--Header común para todas las páginas excepto login y registro--> 
			<jsp:include page="cabecera.jsp"></jsp:include>

            <main class="w-100 p-2">  
                    <!--Imagenes-->
                    <h1 class="h1Detalles">${producto.getNombre()}</h1>
                    <div class="divDetalleItem">
                            <img src="${producto.getFoto()}" width="90%">
                            <div class="pt-5">
                            	<h2 class=" w-100 ">Precio : ${producto.getPrecioVentas()}€</h2>
                            	<h2 class="mt-1 w-100 ">Genero : ${producto.getNomGenero()}</h2>
                                <p class="mt-2 w-75 ">${producto.getDescripcion()}</p>
                                <hr>
                                <table>
                                	<tr>
                                		<th>IMDB</th>
                                		<th>FILMAFFINITY</th>
                                		<th>SENSACINE</th>
                                		<th>TMDB</th>
                                	</tr>
                                	<tr>
                                		<c:forEach items="${valoraciones}" var="valoracion">
							        		<td>${valoracion}</td>
						        		</c:forEach>
                                	</tr>
                                </table>
                                <br>
                                <c:if test="${producto.getNumUnidades() > 0}">
							           <a href="ServletCarro?compra=${producto.getIdProducto()}" class="text-dark w-25 p-1 bg-light border rounded-2 text-center" style="text-decoration: none;">COMPRAR</a>
							    </c:if>
							    <c:if test="${producto.getNumUnidades() == 0}">
							    		<p class="text-danger fs-2 fw-bold">Sin Stock</p>
							    </c:if>
                            </div>
                    </div>
            </main>
        
  </body>
</html>