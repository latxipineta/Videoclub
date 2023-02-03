package beans;

public class Genero {
	private int idGenero;
	private String nombre;
	
	
	public Genero(int idGenero, String nombre) {
		this.idGenero = idGenero;
		this.nombre = nombre;
	}
	
	public int getIdGenero() {
		return idGenero;
	}
	public void setIdGenero(int idGenero) {
		this.idGenero = idGenero;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
