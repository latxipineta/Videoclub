package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.CarroCompra;
import beans.DetallePago;
import beans.EnviarMail;
import beans.Pedido;
import beans.Usuario;
import dao.PedidoDAO;
import dao.detallePagoDAO;

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
			// creamos el pedido que vamos a insertar y el carro que contiene las lineas de pedido
			Pedido p = (Pedido) sess.getAttribute("pedido");
			
			// creamos el detalle de pago que vamos a insertar
			int idPedido = p.getIdPedido();
			int numTarjeta = Integer.parseInt(request.getParameter("numTarjeta"));
			int codigoSegu = Integer.parseInt(request.getParameter("codigoSeguridad"));
			float precioTotal = p.getPrecio();
			String formaPago = request.getParameter("select");
			String fechaVenci = request.getParameter("fechaVencimiento");
			
			boolean valido = validar(codigoSegu+"", 3);
					if(valido) {
						valido = validar(fechaVenci, 5);
					}
					
			if(!valido) {
				// si no es valido se vuelve a carro con un mensaje de error
				request.setAttribute("error", "formato incorrecto");
				request.getRequestDispatcher("carro.jsp").forward(request, response);
			}else {
				// si es valido se inserta el pedido y el carro con las lineas de pedido
				PedidoDAO.insertarPedido(p, CarroCompra.getCarro());
				
				// se crea el detalle del pago
				DetallePago d = new DetallePago(idPedido, numTarjeta, codigoSegu, precioTotal, formaPago, fechaVenci);
				
				// se inserta el detalle del pago
				detallePagoDAO.insertarDetallePago(d);
				
				//enviar email a usuario
				Usuario u = (Usuario) sess.getAttribute("user");
				EnviarMail.enviarPedido(CarroCompra.getCarro(), d, u.getEmail());
				
				// una vez insertado, vaciamos el carro, las lineas de pedido y el ultimo pedido disponible ahora sera uno nuevo
				CarroCompra.vaciarCarro();
				sess.setAttribute("listaLineasPedido", null);
				sess.setAttribute("idPedido", PedidoDAO.devuelveUltimoPedido());
				request.getRequestDispatcher("productos.jsp").forward(request, response);
			}
			
		}else if(request.getParameter("cancelar") != null) {
			// si se le ha dado al boton de cancelar, no se inserta nada y se vuelve a carro
			request.getRequestDispatcher("carro.jsp").forward(request, response);
		}else {
			// la primera vez que entre simplemente se le pasara el pedido para ver el presio total
			Pedido p = (Pedido) sess.getAttribute("pedido");
			
			sess.setAttribute("pedido", p);
			request.getRequestDispatcher("formaPago.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
    private boolean validar(String str, int tam) {
    	if(str.length() != tam) {
    		return false;
    	}
    	
    	for (int i = 0; i < str.length(); i++) {
			if( (str.charAt(i)<'0' || str.charAt(i)>'9') && str.charAt(i) != '/') {
				return false;
			}
		}
    	
    	return true;
    }

}
