package beans;

public class Usuario {
	private String nombre, apellidos, direccion, municipio, ciudad, pais, email, passsword, telefono;
	private int admin, codigo_postal;
	
	public Usuario(String nombre, String apellidos, String direccion, int codigo_postal, String municipio,
			String ciudad, String pais, String email, String passsword, String telefono, int admin) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.codigo_postal = codigo_postal;
		this.municipio = municipio;
		this.ciudad = ciudad;
		this.pais = pais;
		this.email = email;
		this.passsword = passsword;
		this.telefono = telefono;
		this.admin = admin;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getCodigo_postal() {
		return codigo_postal;
	}

	public void setCodigo_postal(int codigo_postal) {
		this.codigo_postal = codigo_postal;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasssword() {
		return passsword;
	}

	public void setPasssword(String passsword) {
		this.passsword = passsword;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public int getAdmin() {
		return admin;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
	}
	
	
	
	
}
