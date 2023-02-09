            <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
			<%@page contentType="text/html" pageEncoding="UTF-8"%>
			<header class="border border-white">
                <nav class="navbar navbar-expand-lg bg-dark py-3 fs-5">
                    <div class="container-fluid">

                      <a class="navbar-brand text-white mx-3 fw-bold" href="productos.jsp">INICIO</a>

                      <div class="collapse navbar-collapse" id="navbarSupportedContent">

                        <ul class="navbar-nav me-auto">
                          
                          <li class="nav-item">
                            <a class="nav-link mx-3 text-white fw-bold" href="carro.jsp">Carro</a>
                          </li>
                          <li class="nav-item">
                            <a class="nav-link mx-3 text-white fw-bold" href="listadoCompras.jsp">Listado Compras</a>
                          </li>
						  
						  <c:if test="${user.getAdmin() == 1}">
		           				<li class="nav-item">
		                            <a class="nav-link mx-3 text-primary fw-bold" href="listaUsuarios.jsp">Lista Usuarios</a>
		                        </li>
		                        <li class="nav-item">
		                            <a class="nav-link mx-3 text-primary fw-bold" href="listaProveedores.jsp">Lista Proveedores</a>
		                        </li>
		    			  </c:if>
						  
                          <li class="nav-item">
                            <a class="nav-link mx-3 text-danger fw-bold" href="ServletLogin?borrar=si">CERRAR SESIÓN</a>
                          </li>
                        </ul>
						
                      </div>

                    </div>
					<c:if test="${user.getAdmin() == 1}">
						<h4 class="h4Header text-primary bg-white border rounded-2 text-center m-2 fw-bold">${user.getNombre().toUpperCase()} (ADMIN)</h4>
					</c:if>
                    <c:if test="${user.getAdmin() == 0}">
						<h4 class="h4Header text-secondary bg-white border rounded-2 text-center m-2 fw-bold">${user.getNombre().toUpperCase()}</h4>
					</c:if>
                  </nav>
            </header>