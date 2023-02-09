package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Proveedor;
import dao.ProductosDAO;

/**
 * Servlet implementation class ServletProveedores
 */
public class ServletProveedores extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletProveedores() {
        super();
    }
    
    private String validarNombres(String nomFich, String nombreNuevo, String nomAntiguo) {
    	ArrayList<String> arrl = Proveedor.dameNombresDeEmpresa(nomFich);
    	
    	if(arrl.contains(nombreNuevo)) {
    		return nomAntiguo;
    	}
    	return nombreNuevo;
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String rutaFich = request.getRealPath("Proveedores/"+getInitParameter("nomFich"));
		
		if(request.getParameter("detalles") != null) {
			// cuando en "listaProveedores.jsp" pulsas en un proveedor
			//devuelve el proveedor del que mostraremos detalles
			Proveedor p = Proveedor.dameProveedor(request.getParameter("detalles"), rutaFich);
			request.setAttribute("proveedor", p);
			
			request.getRequestDispatcher("detallesProveedor.jsp").forward(request, response);
		}else if(request.getParameter("proveedorCambiar") != null){
			// cuando en "detallesProveedor.jsp" cambias campos
			// recuperamos el proveedor que hemos intentado editar
			Proveedor p = Proveedor.dameProveedor(request.getParameter("proveedorCambiar"), rutaFich);
			
			String nombreAntiguo = p.getNomEmpresa();
			
			String nomEmpresa = request.getParameter("nomEmpresa");
				// validamos que no existe un nombre de empresa igual
				   nomEmpresa = validarNombres(rutaFich, nomEmpresa, nombreAntiguo);
			String nomResponsable = request.getParameter("nomResponsable");
			String nomCategoria = request.getParameter("nomCategoria");
			String telefono = request.getParameter("telefono");
			String correo = request.getParameter("correo");
			
			// comprobamos que los campos no estan vacios
			if(!nomEmpresa.isEmpty()) {
				p.setNomEmpresa(nomEmpresa);
			}
			if(!nomResponsable.isEmpty()) {
				p.setNombreResponsable(nomResponsable);
			}
			if(!nomCategoria.isEmpty()) {
				p.setNombreCategoria(nomCategoria);
			}
			if(!telefono.isEmpty()) {
				p.setTelefono(telefono);
			}
			if(!correo.isEmpty()) {
				p.setCorreo(correo);
			}
			
			// se actualiza en base de datos, en caso de que no se haya intentado cambiar nada, seguira teniendo los mismos atributos
			ProductosDAO.actualizarProveedor(p, nombreAntiguo);
			
			// se actualiza en el fichero
			Proveedor.actualizarFichero(nombreAntiguo, p, rutaFich);
			
			//mostrar todos los proveedores
			ArrayList<Proveedor> arrlProveedores = Proveedor.cargarProveedores(rutaFich);
			request.setAttribute("listaProveedores", arrlProveedores);
			
			request.getRequestDispatcher("listaProveedores.jsp").forward(request, response);
		}else {
			// cuando se entra por primera vez a "listaProveedor.jsp"
			// mostrar todos los proveedores
			ArrayList<Proveedor> arrlProveedores = Proveedor.cargarProveedores(rutaFich);
			request.setAttribute("listaProveedores", arrlProveedores);
			
			request.getRequestDispatcher("listaProveedores.jsp").forward(request, response);
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
