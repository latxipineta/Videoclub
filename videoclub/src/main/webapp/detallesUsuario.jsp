<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Lista Usuarios</title>
	<!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
    <link rel="stylesheet" type="text/css" href="css/estilos.css"/>
</head>
    <body class="bg-dark">
	   		<c:if test="${user == null}">
		           <jsp:forward page="login.jsp"/>
		    </c:if>
		    
		    <c:if test="${user.getAdmin() == 0}">
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

            <main class="w-100 p-5 text-white">  
            
           		<form action="ServletUsuarios?usuarioCambiar=${usuario.getEmail()}" method="post" class="row">
           		
					<table>
						<tr>
							<th>Nombre</th>
							<td>${usuario.getNombre()}</td>
							<td><input type="text" name="nombre"></td>
						</tr>
						<tr>
							<th>Apellidos</th>
							<td>${usuario.getApellidos()}</td>
							<td><input type="text" name="apellidos"></td>
						</tr>
						<tr>
							<th>Direccion</th>
							<td>${usuario.getDireccion()}</td>
							<td><input type="text" name="direccion"></td>
						</tr>
						<tr>
							<th>Codigo Postal</th>
							<td>${usuario.getCodigo_postal()}</td>
							<td><input type="text" name="codigo_postal"></td>
						</tr>
						<tr>
							<th>Municipio</th>
							<td>${usuario.getMunicipio()}</td>
							<td><input type="text" name="municipio"></td>
						</tr>
						<tr>
							<th>Ciudad</th>
							<td>${usuario.getCiudad()}</td>
							<td><input type="text" name="ciudad"></td>
						</tr>
						<tr>
							<th>Pais</th>
							<td>${usuario.getPais()}</td>
							<td><input type="text" name="pais"></td>
						</tr>
						<tr>
							<th>Correo</th>
							<td>${usuario.getEmail()}</td>
						</tr>
						<tr>
							<th>Password</th>
							<td>${usuario.getPasssword()}</td>
							<td><input type="text" name="password"></td>
						</tr>
						<tr>
							<th>Telefono</th>
							<td>${usuario.getTelefono()}</td>
							<td><input type="text" name="telefono"></td>
						</tr>
						<tr>
							<th>Admin</th>
							<c:if test="${usuario.getAdmin() == 0}">
						           <td>Usuario</td>
						    </c:if>
						    <c:if test="${usuario.getAdmin() == 1}">
						           <td>Asministrador</td>
						    </c:if>
						    <c:if test="${usuario.getAdmin() == 0}">
						          <td><input type="checkbox" name="admin" value="admin"></td>
						    </c:if>
						    <c:if test="${usuario.getAdmin() == 1}">
						           <td><input type="checkbox" name="admin" value="admin" checked></td>
						    </c:if>
						</tr>
					</table>
					
					<hr>  
                  	<input type="submit" class="w-25" name="cambiar" value="APLICAR CAMBIOS">
                  	
				</form>
				
            </main>
        
  </body>
</html>