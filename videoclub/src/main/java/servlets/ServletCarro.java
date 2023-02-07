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
import dao.PedidoDAO;
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
			ProductosDAO pDAO = new ProductosDAO();
			
			int idProducto = Integer.parseInt(request.getParameter("compra"));
			int idPedido = (int) sess.getAttribute("idPedido");
			int cantidad = 1;
			float precioTotal = pDAO.getProducto(idProducto).getPrecioVentas();
			
			LineaPedido lp = new LineaPedido(idProducto, idPedido, cantidad, precioTotal);
			
			CarroCompra.aniadeLinea(lp);
			
			sess.setAttribute("listaLineasPedido", CarroCompra.getCarro());
			
			request.getRequestDispatcher("productos.jsp").forward(request, response);
			
		}else if(request.getParameter("vaciarCarro") != null) {
			CarroCompra.vaciarCarro();
			sess.setAttribute("listaLineasPedido", null);
			request.getRequestDispatcher("carro.jsp").forward(request, response);
			
		}else if(request.getParameter("pagar") != null) {
			int idPedido = (int) sess.getAttribute("idPedido");
			//Sacar precio total
			float precio = CarroCompra.devuelvePrecioTotal();
			//Sacar fecha actual
            String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            //Sacar correo de usuario
            Usuario usu = (Usuario) sess.getAttribute("user");
            String correoUsuario = usu.getEmail();
            //Sacar nombrePedido
            String nombrePedido = "Pedido";
			if(!request.getParameter("nombrePedido").isEmpty()) {
				nombrePedido = request.getParameter("nombrePedido");
			}
			
			Pedido p = new Pedido(idPedido, precio, fecha, correoUsuario, nombrePedido);
			
			sess.setAttribute("pedido", p);
			
			response.sendRedirect("formaPago.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
