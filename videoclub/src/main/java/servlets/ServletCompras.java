package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.LineaPedido;
import beans.Pedido;
import beans.Usuario;
import dao.PedidoDAO;

public class ServletCompras extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletCompras() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("detalles") != null) {
			// Creo un arraylist con las lineas de pedido de ese pedido
			int idPedido = Integer.parseInt(request.getParameter("detalles"));
			ArrayList<LineaPedido> arrlLineasPedido = PedidoDAO.devuelveLineasDePedido(idPedido);
			
			request.setAttribute("listaLineasPedido", arrlLineasPedido);
			
			request.getRequestDispatcher("detallesPedido.jsp").forward(request, response);
		}else {
			// creo un arraylist con las compras del usuario logueado
			HttpSession sess = request.getSession(false);
			Usuario usu = (Usuario) sess.getAttribute("user");
			ArrayList<Pedido> arrlPed = PedidoDAO.devuelvePedidosDeUsuario(usu.getEmail());
			
			request.setAttribute("listaCompras", arrlPed);
			
			request.getRequestDispatcher("listadoCompras.jsp").forward(request, response);
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
