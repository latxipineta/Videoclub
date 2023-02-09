package servlets;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.CarroCompra;
import beans.LineaPedido;
import beans.Pedido;
import beans.Usuario;
import dao.ProductosDAO;

/**
 * Servlet implementation class ServletCarro
 */
public class ServletCarro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletCarro() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession sess = request.getSession(false);
		if(request.getParameter("compra") != null) {
			// cuando le das a comprar un producto en "detallesProducto.jsp", se crea una linea de pedido
			int idProducto = Integer.parseInt(request.getParameter("compra"));
			int idPedido = (int) sess.getAttribute("idPedido");
			int cantidad = 1;
			float precioTotal = ProductosDAO.getProducto(idProducto).getPrecioVentas();
			
			LineaPedido lp = new LineaPedido(idProducto, idPedido, cantidad, precioTotal);
			
			// se añade la linea de pedido al carro
			CarroCompra.aniadeLinea(lp);
			
			sess.setAttribute("listaLineasPedido", CarroCompra.getCarro());
			
			request.getRequestDispatcher("productos.jsp").forward(request, response);
			
		}else if(request.getParameter("vaciarCarro") != null) {
			// cuando en "carro.jsp" le das a vaciar carro, se vacia
			CarroCompra.vaciarCarro();
			sess.setAttribute("listaLineasPedido", null);
			
			request.getRequestDispatcher("carro.jsp").forward(request, response);
			
		}else if(request.getParameter("pagar") != null) {
			// cuando en "carro.jsp" le das a pagar, se crea el pedido
			int idPedido = (int) sess.getAttribute("idPedido");
			float precio = CarroCompra.devuelvePrecioTotal();
            String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            Usuario usu = (Usuario) sess.getAttribute("user");
            String correoUsuario = usu.getEmail();
            String nombrePedido = "Pedido";
			if(!request.getParameter("nombrePedido").isEmpty()) {
				nombrePedido = request.getParameter("nombrePedido");
			}
			
			Pedido p = new Pedido(idPedido, precio, fecha, correoUsuario, nombrePedido);
			
			// guardamos el pedido en sesion
			sess.setAttribute("pedido", p);
			
			response.sendRedirect("formaPago.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
