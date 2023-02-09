<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Detalles Proveedor</title>
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
		    
            <!--Header común para todas las páginas excepto login y registro--> 
			<jsp:include page="cabecera.jsp"></jsp:include>

            <main class="w-100 p-5 text-white">  
            
           		<form action="ServletProveedores?proveedorCambiar=${proveedor.getNomEmpresa()}" method="post" class="row">
           		
					<table>
						<tr>
							<th>Nombre Empresa</th>
							<td>${proveedor.getNomEmpresa()}</td>
							<td><input type="text" name="nomEmpresa"></td>
						</tr>
						<tr>
							<th>Nombre responsable</th>
							<td>${proveedor.getNombreResponsable()}</td>
							<td><input type="text" name="nomResponsable"></td>
						</tr>
						<tr>
							<th>Nombre categoria</th>
							<td>${proveedor.getNombreCategoria()}</td>
							<td><input type="text" name="nomCategoria"></td>
						</tr>
						<tr>
							<th>Telefono</th>
							<td>${proveedor.getTelefono()}</td>
							<td><input type="text" name="telefono"></td>
						</tr>
						<tr>
							<th>Correo</th>
							<td>${proveedor.getCorreo()}</td>
							<td><input type="text" name="correo"></td>
						</tr>
					</table>
					
					<hr>  
                  	<input type="submit" class="w-25" name="cambiar" value="APLICAR CAMBIOS">
                  	
				</form>
				
            </main>
  </body>
</html>