package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Usuario;
import dao.UsuariosDAO;

/**
 * Servlet implementation class ServletUsuarios
 */
public class ServletUsuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletUsuarios() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UsuariosDAO uDAO = new UsuariosDAO();
		
		if(request.getParameter("detalles") != null) {
			Usuario usu = uDAO.devuelveUsuario(request.getParameter("detalles"));
			request.setAttribute("usuario", usu);
			
			request.getRequestDispatcher("detallesUsuario.jsp").forward(request, response);
		}else if(request.getParameter("cambiar") != null) {
			Usuario usu = uDAO.devuelveUsuario(request.getParameter("usuarioCambiar"));
			
			String nombre = request.getParameter("nombre");
			String apellidos = request.getParameter("apellidos");
			String direccion = request.getParameter("direccion");
			String codigo_postal = request.getParameter("codigo_postal");
			String municipio = request.getParameter("municipio");
			String ciudad = request.getParameter("ciudad");
			String pais = request.getParameter("pais");
			String password = request.getParameter("password");
			String telefono = request.getParameter("telefono");
			
			if(!nombre.isEmpty()) {
				usu.setNombre(nombre);
			}
			if(!apellidos.isEmpty()) {
				usu.setApellidos(apellidos);
			}
			if(!direccion.isEmpty()) {
				usu.setDireccion(direccion);
			}
			if(!codigo_postal.isEmpty()) {
				usu.setCodigo_postal(Integer.parseInt(codigo_postal));
			}
			if(!municipio.isEmpty()) {
				usu.setMunicipio(municipio);
			}
			if(!ciudad.isEmpty()) {
				usu.setCiudad(ciudad);
			}
			if(!pais.isEmpty()) {
				usu.setPais(pais);
			}
			if(!password.isEmpty()) {
				usu.setPasssword(password);
			}
			if(!telefono.isEmpty()) {
				usu.setTelefono(telefono);
			}
			if(request.getParameter("admin") != null) {
				usu.setAdmin(1);
			}
			if(request.getParameter("admin") == null) {
				usu.setAdmin(0);
			}
			
			uDAO.actualizarUsuario(usu);
			
			ArrayList<Usuario> arrlUsuarios = uDAO.listaTodosUsuarios();
			request.setAttribute("listaUsuarios", arrlUsuarios);
			
			request.getRequestDispatcher("listaUsuarios.jsp").forward(request, response);
		}else {
			ArrayList<Usuario> arrlUsuarios = uDAO.listaTodosUsuarios();
			request.setAttribute("listaUsuarios", arrlUsuarios);
			
			request.getRequestDispatcher("listaUsuarios.jsp").forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
