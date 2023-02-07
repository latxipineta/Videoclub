package beans;

import dao.ProductosDAO;

public class LineaPedido {
	private int idProducto, idPedido, cantidad;
	private float precioTotal;
	private String nombreProducto, imagenProducto;
	
	public LineaPedido(int idProducto, int idPedido, int cantidad, float precioTotal) {
		this.idProducto = idProducto;
		this.idPedido = idPedido;
		this.cantidad = cantidad;
		this.precioTotal = precioTotal;
		this.nombreProducto = ProductosDAO.getProducto(idProducto).getNombre();
		this.imagenProducto = ProductosDAO.getProducto(idProducto).getFoto();
	}
	
	
	

	public String getImagenProducto() {
		return imagenProducto;
	}




	public void setImagenProducto(String imagenProducto) {
		this.imagenProducto = imagenProducto;
	}




	public String getNombreProducto() {
		return nombreProducto;
	}



	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}



	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public float getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(float precioTotal) {
		this.precioTotal = precioTotal;
	}

	@Override
	public String toString() {
		return "LineaPedido [idProducto=" + idProducto + ", idPedido=" + idPedido + ", cantidad=" + cantidad
				+ ", precioTotal=" + precioTotal + "]";
	}

	
	
}
