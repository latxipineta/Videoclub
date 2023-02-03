package beans;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Proveedor {
	private String nomEmpresa, nombreResponsable, nombreCategoria, telefono, correo;
	
	public Proveedor(String nomEmpresa, String nombreResponsable, String nombreCategoria, String telefono, String correo) {
		this.nomEmpresa = nomEmpresa;
		this.nombreResponsable = nombreResponsable;
		this.nombreCategoria = nombreCategoria;
		this.telefono = telefono;
		this.correo = correo;
	}




	public String getNomEmpresa() {
		return nomEmpresa;
	}




	public void setNomEmpresa(String nomEmpresa) {
		this.nomEmpresa = nomEmpresa;
	}




	public String getNombreResponsable() {
		return nombreResponsable;
	}




	public void setNombreResponsable(String nombreResponsable) {
		this.nombreResponsable = nombreResponsable;
	}




	public String getNombreCategoria() {
		return nombreCategoria;
	}




	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}




	public String getTelefono() {
		return telefono;
	}




	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}




	public String getCorreo() {
		return correo;
	}




	public void setCorreo(String correo) {
		this.correo = correo;
	}




	/** CARGAR PROVEEDORES DEL FICHERO */
	static public ArrayList<Proveedor> cargarProveedores(String nomFich) {
		ArrayList<Proveedor> arrlProve = new ArrayList<Proveedor>();
		try {
			BufferedReader br=new BufferedReader(new FileReader(nomFich));
			String linea=br.readLine();
			
			while(linea!=null) {
				String[] proveedor=linea.split(";");
				if(proveedor.length==5) {
					Proveedor p = new Proveedor(proveedor[0],proveedor[1],proveedor[2],proveedor[3],proveedor[4]);
					arrlProve.add(p);
				}
				linea=br.readLine();
			}
			
			br.close();
			return arrlProve;
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
		return arrlProve;
	}	
	
	public static Proveedor dameProveedor(String nombreEmpresa, String nomFich) {
		ArrayList<Proveedor> arrlProve=cargarProveedores(nomFich);
		for(Proveedor p:arrlProve) {
			if(p.getNomEmpresa().equals(nombreEmpresa)) {
				return p;
			}
		}
		return null;
	}
	
	/** ACTUALIZAR FICHERO */
	public static void actualizarFichero(String nombreEmpresa, Proveedor proveedor, String nomFich) {
		try {
			ArrayList<Proveedor> arrlProve=cargarProveedores(nomFich);
			ArrayList<String> arrNombresExistentes=dameNombresDeEmpresa(nomFich);
			PrintWriter pw=new PrintWriter(new FileWriter(nomFich));
			String linea="";
			for(Proveedor proveedoresExistentes:arrlProve) {
				if(proveedoresExistentes.getNomEmpresa().equals(nombreEmpresa)) {
					if(!arrNombresExistentes.contains(proveedor.getNomEmpresa())) {
						linea+=proveedor.getNomEmpresa()+";"+proveedor.getNombreResponsable()+";"+proveedor.getNombreCategoria()+";"+proveedor.getTelefono()+";"+proveedor.getCorreo()+"\n";
					}else {
						System.err.println("ERROR - Ya existe una empresa con ese nombre");
						linea+=proveedoresExistentes.getNomEmpresa()+";"+proveedoresExistentes.getNombreResponsable()+";"+proveedoresExistentes.getNombreCategoria()+";"+proveedoresExistentes.getTelefono()+";"+proveedoresExistentes.getCorreo()+"\n";
					}
				}else {
					linea+=proveedoresExistentes.getNomEmpresa()+";"+proveedoresExistentes.getNombreResponsable()+";"+proveedoresExistentes.getNombreCategoria()+";"+proveedoresExistentes.getTelefono()+";"+proveedoresExistentes.getCorreo()+"\n";
				}		
			}
			pw.print(linea.trim());
			pw.close();		
		} catch (IOException e) {
		}
	}
	
	/** PRIVADO - ARRAYLIST CON LOS NOMBRES DE LAS EMPRESAS */
	public static ArrayList<String> dameNombresDeEmpresa(String nomFich){
		ArrayList<Proveedor> arrlProve=cargarProveedores(nomFich);
		ArrayList<String>nombre=new ArrayList<String>();

		for(Proveedor p: arrlProve) {
			System.out.println("AÑADIDO - "+p.getNomEmpresa());
			nombre.add(p.getNomEmpresa());
		}
		
		return nombre;		
	}
}
