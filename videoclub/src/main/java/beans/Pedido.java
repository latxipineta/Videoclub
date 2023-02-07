package beans;

public class Pedido {
	private int idPedido;
	private float precio;
	private String fecha, correoUsuario, nombrePedido;
	
	public Pedido(int idPedido, float precio, String fecha, String correoUsuario, String nombrePedido) {
		this.idPedido = idPedido;
		this.precio = precio;
		this.fecha = fecha;
		this.correoUsuario = correoUsuario;
		this.nombrePedido = nombrePedido;
	}

	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getCorreoUsuario() {
		return correoUsuario;
	}

	public void setCorreoUsuario(String correoUsuario) {
		this.correoUsuario = correoUsuario;
	}

	public String getNombrePedido() {
		return nombrePedido;
	}

	public void setNombrePedido(String nombrePedido) {
		this.nombrePedido = nombrePedido;
	}
	
	
	
}
