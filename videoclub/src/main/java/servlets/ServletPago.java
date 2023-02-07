package servlets;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.CarroCompra;
import beans.Pedido;
import beans.Usuario;
import dao.PedidoDAO;

/**
 * Servlet implementation class ServletPago
 */
public class ServletPago extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletPago() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession sess = request.getSession(false);
		if(request.getParameter("formalizar") != null) {
			System.out.println("Hacer compra");
			
			Pedido p = (Pedido) sess.getAttribute("pedido");
			
			PedidoDAO.insertarPedido(p, CarroCompra.getCarro());
			
			CarroCompra.vaciarCarro();
			sess.setAttribute("listaLineasPedido", null);
			sess.setAttribute("idPedido", PedidoDAO.devuelveUltimoPedido());
			request.getRequestDispatcher("productos.jsp").forward(request, response);
			
		}else {
			request.getRequestDispatcher("carro.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
