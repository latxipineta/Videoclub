package servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Usuario;
import conex.BDConex;
import dao.UsuariosDAO;

/**
 * Servlet implementation class ServletLogin
 */
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BDConex ds;
       
    public ServletLogin() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ds = new BDConex();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("login") != null){
			HttpSession sess = request.getSession(false);
			 if (sess != null) {
				 sess.invalidate();
			 }
			HttpSession session = request.getSession(true);
			
			String user = request.getParameter("user");
			String password = request.getParameter("password");
			
			UsuariosDAO ud = new UsuariosDAO();
			Usuario c = ud.buscaUsuario(user, password);
			
			if(c != null) {
				session.setAttribute("user", c);
				
				response.sendRedirect("productos.jsp");
			}else {
				request.setAttribute("error", "Error en usuario o contraseña");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		}else if(request.getParameter("borrar") != null){
			HttpSession sess = request.getSession(false);
			 if (sess != null) {
				 sess.invalidate();
			 }
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
