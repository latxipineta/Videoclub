package beans;

public class Producto {
	private String nombre, descripcion, foto, proveedor, tipo, nomGenero;
	private int numUnidades, idProducto, descuento;
	private float precioVentas;
	
	public Producto(String nombre, String descripcion, String foto, String proveedor, String tipo, int numUnidades,
			String nomGenero, float precioVentas, int idProducto, int descuento) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.foto = foto;
		this.proveedor = proveedor;
		this.tipo = tipo;
		this.numUnidades = numUnidades;
		this.nomGenero = nomGenero;
		if(descuento == 50) {
			this.precioVentas = precioVentas/2;
		}else {
			this.precioVentas = precioVentas;
		}
		this.idProducto = idProducto;
		this.descuento = descuento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getProveedor() {
		return proveedor;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getNumUnidades() {
		return numUnidades;
	}

	public void setNumUnidades(int numUnidades) {
		this.numUnidades = numUnidades;
	}

	public String getNomGenero() {
		return nomGenero;
	}

	public void setNomGenero(String nomGenero) {
		this.nomGenero = nomGenero;
	}

	public float getPrecioVentas() {
		return precioVentas;
	}

	public void setPrecioVentas(float precioVentas) {
		this.precioVentas = precioVentas;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public int getDescuento() {
		return descuento;
	}

	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}
	
	
	
}
