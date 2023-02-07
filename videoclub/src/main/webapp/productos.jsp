<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Productos</title>
	<!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
    <link rel="stylesheet" type="text/css" href="css/estilos.css"/>
</head>
    <body class="bodyCatalogo">
	   		<c:if test="${user == null}">
		           <jsp:forward page="login.jsp"/>
		    </c:if>
		    
		    <c:if test="${arrlProductos == null}">
		           <jsp:forward page="ServletProductos"/>
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

            <main class="mainCatalogo w-100 p-5">  
                  <!--Encabezado--> 
                    <h1 class="text-white encabezadoCatalogo">VIDEOCLUB</h1>

                    <!-- Menu -->
                    <div class="dropdown mt-5 d-flex justify-content-center">
                        <button class="btn bg-success-subtle dropdown-toggle w-50 mt-3" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                          	Generos
                        </button>

                        <ul class="dropdown-menu w-50">
                        	<li><a href="ServletProductos" class="dropdown-item" style="text-decoration: none;">Ver Todos</a></li>
                          <c:forEach items="${arrlGeneros}" var="genero">
                          		<li><a href="ServletProductos?idGenero=${genero.getIdGenero()}" class="dropdown-item" style="text-decoration: none;">${genero.getNombre()}</a></li>
		        		  </c:forEach>
                        </ul>
                    </div>

                    <!--Imagenes-->
                    <div class="m-5 p-5 text-white row">
	                    <c:forEach items="${arrlProductos}" var="producto">
			        		<div class="col-3 my-5 divProducto">
	                            <img src="${producto.getFoto()}" width="75%">
	                            <p class="mt-3 w-75">${producto.getNombre()}</p>
				        		<a href="ServletProductos?id=${producto.getIdProducto()}" class="text-dark w-75 p-1 bg-light border rounded-2" style="text-decoration: none;">DETALLES</a>
				        		<c:if test="${producto.getNumUnidades() < 10}">
							    		<c:if test="${producto.getNumUnidades() == 0}">
							    			<p class="bg-danger bg-opacity-75 fw-bold m-0 mt-1 p-1 rounded-1">Sin Stock</p>
							    		</c:if>
							    		<c:if test="${producto.getNumUnidades() > 0}">
							    			<p class="bg-danger bg-opacity-75 fw-bold m-0 mt-1 p-1 rounded-1">Quedan pocas!</p>
							    		</c:if>
							    </c:if>
							    <c:if test="${producto.getDescuento() > 0}">
							    		<p class="text-success-emphasis bg-success-subtle fw-bold m-0 mt-1 p-1 rounded-1">Oferta del 50%!</p>
							    </c:if>
                        	</div>
		        		</c:forEach>
                    </div>
            </main>
        
  </body>
</html>