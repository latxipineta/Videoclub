package servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.CarroCompra;
import beans.EnviarMail;
import beans.Usuario;
import conex.BDConex;
import dao.PedidoDAO;
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
        // se crea la conexion con la base de datos
        ds = new BDConex();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("login") != null){
			// cuando le das al boton de loguearse en "login.jsp"
			// si la sesion existia, se borra y se crea otra
			HttpSession sess = request.getSession(false);
			 if (sess != null) {
				 sess.invalidate();
			 }
			HttpSession session = request.getSession(true);
			
			// recogemos los parametros recogidos del formulario de "login.jsp"
			String user = request.getParameter("user");
			String password = request.getParameter("password");
			
			// buscamos si existe ese usuario en la base de datos
			Usuario c = UsuariosDAO.buscaUsuario(user, password);
			
			// si el usuario existe, se guarda en sesion y tambien el id del proximo pedido disponible que podra hacerse
			if(c != null) {
				session.setAttribute("user", c);
				session.setAttribute("idPedido", PedidoDAO.devuelveUltimoPedido());
				
				response.sendRedirect("productos.jsp");
			}else {
				// si el usuario no existe devuelve un error
				request.setAttribute("error", "Error en usuario o contraseña");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		}else if(request.getParameter("borrar") != null){
			// cuando le das al boton "borrar sesion" del navbar de la pagina se borra la sesion, se vacia el carro y vuelves a login.jsp
			HttpSession sess = request.getSession(false);
			 if (sess != null) {
				 sess.invalidate();
			 }
			 // vacias el carro
			 CarroCompra.vaciarCarro();
			 
			request.getRequestDispatcher("login.jsp").forward(request, response);
			
		}else if(request.getParameter("olvide") != null){
			// cuando le das al enlace de "olvide mi contraseña" en "login.jsp"
			// si el usuario se le olvido la contraseña, te devuelve a login y te saldra el nuevo formulario para enviarte un correo
			request.setAttribute("olvide", "si");
			
			request.getRequestDispatcher("login.jsp").forward(request, response);
			
		}else if(request.getParameter("enviarCorreo") != null){
			// cuando le das al boton de "enviar correo" en "login.jsp"
			// cuando escribas el correo para la nueva contraseña, primero se validara si existe en la base de datos un usuario con ese correo
			String correo = request.getParameter("correo");
			boolean existe = UsuariosDAO.buscaUsuario(correo);
			
			// si existe se manda un correo con la nueva contraseña y al usaurio se le cambia la contraseña en la base de datos
			if(existe) {
				String nomUsuario = UsuariosDAO.devuelveUsuario(request.getParameter("correo")).getNombre();
				String nuevaContraseña = EnviarMail.generarPassword();
				
				EnviarMail.enviarNuevaContraseña(nomUsuario, correo, nuevaContraseña);
				UsuariosDAO.actualizaContrasenia(nuevaContraseña, correo);
			}else {
				// si no existe ese usuario te devuelve un error
				request.setAttribute("error", "Ese correo no existe");
			}
			// pase lo que pase te devuele a "login.jsp" para que te vuelvas a intentar loguear
			request.getRequestDispatcher("login.jsp").forward(request, response);
			
		}else {
			// la primera vez que entre a videoclub simplemente ira a "login.jsp"
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
