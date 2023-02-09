package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.EnviarMail;
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
		if(request.getParameter("detalles") != null) {
			// cuando en "listaUsuarios.jsp" pulsas en un usuario, recogemos el usuario y vamos a sus detalles
			Usuario usu = UsuariosDAO.devuelveUsuario(request.getParameter("detalles"));
			request.setAttribute("usuario", usu);
			
			request.getRequestDispatcher("detallesUsuario.jsp").forward(request, response);
			
		}else if(request.getParameter("cambiar") != null) {
			// cuando en "detallesUsuario.jsp"
			// se guarda el usuario que hemos intentado editar
			Usuario usu = UsuariosDAO.devuelveUsuario(request.getParameter("usuarioCambiar"));
			
			String nombre = request.getParameter("nombre");
			String apellidos = request.getParameter("apellidos");
			String direccion = request.getParameter("direccion");
			String codigo_postal = request.getParameter("codigo_postal");
			String municipio = request.getParameter("municipio");
			String ciudad = request.getParameter("ciudad");
			String pais = request.getParameter("pais");
			String password = request.getParameter("password");
			String telefono = request.getParameter("telefono");
			
			// si hay algun atributo distinto a los que ya tenia se cambia por el nuevo
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
				boolean valido = true;
				
				if(codigo_postal.length() == 5) {
					for (int i = 0; i < codigo_postal.length(); i++) {
						if(codigo_postal.charAt(i)<'0' || codigo_postal.charAt(i)>'9') {
							valido = false;
						}
					}
				}else {
					valido = false;
				}
				
				if(valido) {
					usu.setCodigo_postal(Integer.parseInt(codigo_postal));
				}
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
				// se le envia email con la nueva contraseña
				EnviarMail.enviarNuevaContraseñaAdmin(usu.getNombre(), usu.getEmail(), password);
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
			
			// se actualisa en la bd
			UsuariosDAO.actualizarUsuario(usu);
			
			// hacemos un arraylist con todos los usuarios y volvemos a la lista de usuarios
			ArrayList<Usuario> arrlUsuarios = UsuariosDAO.listaTodosUsuarios();
			request.setAttribute("listaUsuarios", arrlUsuarios);
			
			request.getRequestDispatcher("listaUsuarios.jsp").forward(request, response);
		}else {
			// cuando se entra por primera vez a "listaUsuarios.jsp"
			// hacemos un arraylist con todos los usuarios y volvemos a la lista de usuarios
			ArrayList<Usuario> arrlUsuarios = UsuariosDAO.listaTodosUsuarios();
			request.setAttribute("listaUsuarios", arrlUsuarios);
			
			request.getRequestDispatcher("listaUsuarios.jsp").forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
