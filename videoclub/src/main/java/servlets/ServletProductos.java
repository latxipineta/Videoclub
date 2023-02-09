package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Genero;
import beans.Producto;
import dao.GenerosDAO;
import dao.ProductosDAO;
import dao.ValoracionesDAO;

/**
 * Servlet implementation class ServletProductos
 */
public class ServletProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public ServletProductos() {
        // TODO Auto-generated constructor stub
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("id") != null) {
			// cuando en "productos.jsp" le das a un producto, se recupera su id
			int id = Integer.parseInt(request.getParameter("id"));
			
			//Sacar el producto por su id y sus valoraciones
			Producto pro = ProductosDAO.getProducto(id);
			ArrayList<String> arrlValo = ValoracionesDAO.ValoracionesProducto(id);
			
			request.setAttribute("producto", pro);
			request.setAttribute("valoraciones", arrlValo);
			
			request.getRequestDispatcher("detallesProducto.jsp").forward(request, response);
		}else if(request.getParameter("idGenero") != null){
			// cuando en el menu de "productos.jsp" eliges un genero
			//Sacar todos los productos del genero especificado
			int id = Integer.parseInt(request.getParameter("idGenero"));
			ArrayList<Producto> arrlPro = ProductosDAO.ProductosPorGenero(id);
			
			//Sacar todos los generos
			ArrayList<Genero> arrlGeneros = GenerosDAO.listaTodosGeneros();
			
			request.setAttribute("arrlProductos", arrlPro);
			request.setAttribute("arrlGeneros", arrlGeneros);
			
			request.getRequestDispatcher("productos.jsp").forward(request, response);
		}else {
			//Sacar todos los productos
			ArrayList<Producto> arrlPro = ProductosDAO.listaTodosProductos();
			
			//Sacar todos los generos
			ArrayList<Genero> arrlGeneros = GenerosDAO.listaTodosGeneros();
			
			request.setAttribute("arrlProductos", arrlPro);
			request.setAttribute("arrlGeneros", arrlGeneros);
			
			request.getRequestDispatcher("productos.jsp").forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
