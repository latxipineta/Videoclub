package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.LineaPedido;
import beans.Pedido;
import beans.Producto;
import dao.ProductosDAO;

/**
 * Servlet implementation class ServletDetalleProducto
 */
@WebServlet("/ServletDetalleProducto")
public class ServletDetalleProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletDetalleProducto() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("compra") != null){
			int id = Integer.parseInt(request.getParameter("compra"));
			
			ProductosDAO p = new ProductosDAO();
			Producto pro = p.getProducto(id);
			request.setAttribute("productos.jsp", pro);
			
			HttpSession sess = request.getSession(false);
			 if (sess != null) {
				 if(sess.getAttribute("pedido") != null) {
					 LineaPedido l = null;
				 }else {
					 LineaPedido l = null;

					 
				 }
			 }
			
			request.getRequestDispatcher("productos.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
