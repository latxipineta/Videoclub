package servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Usuario;
import conex.BDConex;
import dao.UsuariosDAO;

/**
 * Servlet implementation class ServletRegistro
 */
public class ServletRegistro extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BDConex ds;
       
    public ServletRegistro() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ds = new BDConex();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("registro") != null){
			String nombre = request.getParameter("nombre");
			String apellidos = request.getParameter("apellidos");
			String direccion = request.getParameter("direccion");
			String codigoPostal = request.getParameter("codigoPostal");
			String municipio = request.getParameter("municipio");
			String ciudad = request.getParameter("ciudad");
			String pais = request.getParameter("pais");
			String correo = request.getParameter("correo");
			String password = request.getParameter("password");
			String telefono = request.getParameter("telefono");
			boolean valido = true;
			
			if(codigoPostal.length() == 5) {
				for (int i = 0; i < codigoPostal.length(); i++) {
					if(codigoPostal.charAt(i)<'0' || codigoPostal.charAt(i)>'9') {
						request.setAttribute("error", "El codigo postal tiene que ser numerico");
						request.getRequestDispatcher("registro.jsp").forward(request, response);
						valido = false;
					}
				}
			}else {
				request.setAttribute("error", "El codigo postal tiene que tener 5 numeros");
				request.getRequestDispatcher("registro.jsp").forward(request, response);
				valido = false;
			}
			
			
			UsuariosDAO ud = new UsuariosDAO();
			boolean existe = ud.buscaUsuario(correo);
			
			
			if(existe) {
				request.setAttribute("error", "No se pudo registrar el usuario");
				request.getRequestDispatcher("registro.jsp").forward(request, response);
			}else if(!existe && valido){
				Usuario usu = new Usuario(nombre, apellidos, direccion, Integer.parseInt(codigoPostal), municipio, ciudad, pais, correo, password, telefono, 0);
				
				ud.insertaUsuario(usu);
				
				request.setAttribute("mensaje", "Usuario registrado");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
