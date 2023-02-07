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