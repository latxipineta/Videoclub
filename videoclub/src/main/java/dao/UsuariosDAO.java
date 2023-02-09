package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import beans.Usuario;
import conex.BDConex;

public class UsuariosDAO {
	
	public static Usuario buscaUsuario(String nombre, String password) {
		Usuario usu = null;
		
		String sql = "SELECT * FROM usuario WHERE password = '"+password+"' and nombre = '"+nombre+"'";
		
        try {
        	Connection con = BDConex.getDataSource().getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                usu = new Usuario(	rs.getString("nombre"), 
			                		rs.getString("apellidos"), 
			                		rs.getString("direccion"), 
			                		rs.getInt("codigoPostal"), 
			                		rs.getString("municipio"), 
			                		rs.getString("ciudad"), 
			                		rs.getString("pais"), 
			                		rs.getString("correo"), 
			                		rs.getString("password"), 
			                		rs.getString("telefono"), 
			                		rs.getInt("admin")
			                		);
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error en metodo buscaUsuario: " + ex);
        }
		
		return usu;
	}
	
	public static boolean buscaUsuario(String correo) {
		boolean existe = false;
		
		String sql = "SELECT * FROM usuario WHERE correo = '"+correo+"'";
		
        try {
        	Connection con = BDConex.getDataSource().getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
            	existe = true;
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error en metodo buscaUsuario: " + ex);
        }
		
		return existe;
	}
	
	public static Usuario devuelveUsuario(String correo) {
		Usuario usu = null;
		
		String sql = "SELECT * FROM usuario WHERE correo = '"+correo+"'";
		
        try {
        	Connection con = BDConex.getDataSource().getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
            	 usu = new Usuario(	rs.getString("nombre"), 
	                		rs.getString("apellidos"), 
	                		rs.getString("direccion"), 
	                		rs.getInt("codigoPostal"), 
	                		rs.getString("municipio"), 
	                		rs.getString("ciudad"), 
	                		rs.getString("pais"), 
	                		rs.getString("correo"), 
	                		rs.getString("password"), 
	                		rs.getString("telefono"), 
	                		rs.getInt("admin")
	                		);
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error en metodo devuelveUsuario: " + ex);
        }
		
		return usu;
	}
	
	public static void insertaUsuario(Usuario usu) {
		
		String sql = "INSERT INTO usuario(nombre, apellidos, direccion, codigoPostal, municipio, ciudad, pais, correo, password, telefono, admin) "
                + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
        	Connection con = BDConex.getDataSource().getConnection();
            PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            st.setString(1, usu.getNombre());
            st.setString(2, usu.getApellidos());
            st.setString(3, usu.getDireccion());
            st.setInt(4, usu.getCodigo_postal());
            st.setString(5, usu.getMunicipio());
            st.setString(6, usu.getCiudad());
            st.setString(7, usu.getPais());
            st.setString(8, usu.getEmail());
            st.setString(9, usu.getPasssword());
            st.setString(10, usu.getTelefono());
            st.setInt(11, usu.getAdmin());
            
            st.executeUpdate();
            
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error en metodo insertaUsuario: " + ex);
        }
	}
	
	public static int cuantosUsuarios() {
		int num = 0;
		
        String sql = "SELECT count(*) FROM usuario";
        try {
            Connection con = BDConex.getDataSource().getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                num = rs.getInt(1);
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error en metodo cuantosUsuarios: " + ex);
        }
		
		return num;
	}
	
	public static ArrayList<Usuario> listaTodosUsuarios() {
		ArrayList<Usuario> arrlUsuarios = new ArrayList<Usuario>();
		
        String sql = "SELECT * FROM usuario";
        try {
            Connection con = BDConex.getDataSource().getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
            	Usuario usu = new Usuario(	rs.getString("nombre"), 
                		rs.getString("apellidos"), 
                		rs.getString("direccion"), 
                		rs.getInt("codigoPostal"), 
                		rs.getString("municipio"), 
                		rs.getString("ciudad"), 
                		rs.getString("pais"), 
                		rs.getString("correo"), 
                		rs.getString("password"), 
                		rs.getString("telefono"), 
                		rs.getInt("admin")
                		);
                
            	arrlUsuarios.add(usu);
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error en metodo listaTodosUsuarios: " + ex);
        }
		
		return arrlUsuarios;
	}
	
	public static void actualizarUsuario(Usuario usu) {
		String sql = "UPDATE usuario SET nombre = '"+usu.getNombre()+"' ,"
								  + "apellidos = '"+usu.getApellidos()+"' ,"
								  + "direccion = '"+usu.getDireccion()+"' ,"
								  + "codigoPostal = "+usu.getCodigo_postal()+" ,"
								  + "municipio = '"+usu.getMunicipio()+"' ,"
								  + "ciudad = '"+usu.getCiudad()+"' ,"
								  + "pais = '"+usu.getPais()+"' ,"
								  + "password = '"+usu.getPasssword()+"' ,"
								  + "telefono = '"+usu.getAdmin()+"' ,"
								  + "admin = "+usu.getAdmin()+" "
								  + "WHERE correo = '"+usu.getEmail()+"'"; 
        try {
        	Connection con = BDConex.getDataSource().getConnection();
            Statement st = con.createStatement();
            
            st.executeUpdate(sql);
            
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error en metodo actualizarUsuario: " + ex);
        }
	}
	
	public static void actualizaContrasenia(String nuevaContrasenia, String correo) {
		String sql = "UPDATE usuario SET password = '"+nuevaContrasenia+"'"
								  + "WHERE correo = '"+correo+"'"; 
        try {
        	Connection con = BDConex.getDataSource().getConnection();
            Statement st = con.createStatement();
            
            st.executeUpdate(sql);
            
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error en metodo actualizarUsuario: " + ex);
        }
	}
}
