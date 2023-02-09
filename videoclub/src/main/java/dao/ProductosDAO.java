package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import beans.Producto;
import beans.Proveedor;
import conex.BDConex;

public class ProductosDAO {
	public static ArrayList<Producto> listaTodosProductos() {
		ArrayList<Producto> arrlProductos = new ArrayList<Producto>();
		
        String sql = "SELECT * FROM producto";
        try {
            Connection con = BDConex.getDataSource().getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
            	Producto pro = new Producto(	rs.getString("nombre"), 
						                		rs.getString("descripcion"), 
						                		rs.getString("foto"), 
						                		rs.getString("proveedor"), 
						                		rs.getString("tipo"),
						                		rs.getInt("numUnidades"),
						                		GenerosDAO.devuelveNombreGenero(rs.getInt("idGenero")),
						                		rs.getFloat("precioVentas"),
						                		rs.getInt("idProducto"),
						                		rs.getInt("descuento")
						                		);
                
            	arrlProductos.add(pro);
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error en metodo listaTodosProductos: " + ex);
        }
		
		return arrlProductos;
	}
	
	public static ArrayList<Producto> ProductosPorGenero(int id) {
		ArrayList<Producto> arrlProductos = new ArrayList<Producto>();
		
        String sql = "SELECT * FROM producto WHERE idGenero = "+id;
        try {
            Connection con = BDConex.getDataSource().getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
            	Producto pro = new Producto(	rs.getString("nombre"), 
						                		rs.getString("descripcion"), 
						                		rs.getString("foto"), 
						                		rs.getString("proveedor"), 
						                		rs.getString("tipo"),
						                		rs.getInt("numUnidades"),
						                		GenerosDAO.devuelveNombreGenero(rs.getInt("idGenero")),
						                		rs.getFloat("precioVentas"),
						                		rs.getInt("idProducto"),
						                		rs.getInt("descuento")
						                		);
                
            	arrlProductos.add(pro);
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error en metodo ProductosPorGenero: " + ex);
        }
		
		return arrlProductos;
	}
	
	public static Producto getProducto(int id) {
		Producto p = null;
		
        String sql = "SELECT * FROM producto where idProducto = "+id;
        try {
            Connection con = BDConex.getDataSource().getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
            	p = new Producto(	rs.getString("nombre"), 
						                		rs.getString("descripcion"), 
						                		rs.getString("foto"), 
						                		rs.getString("proveedor"), 
						                		rs.getString("tipo"),
						                		rs.getInt("numUnidades"),
						                		GenerosDAO.devuelveNombreGenero(rs.getInt("idGenero")),
						                		rs.getInt("precioVentas"),
						                		rs.getInt("idProducto"),
						                		rs.getInt("descuento")
						                		);
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error en metodo getProducto: " + ex);
        }
		
		return p;
	}
	
	public static void actualizarProveedor(Proveedor pro, String clave) {
		String sql = "UPDATE producto SET proveedor = '"+pro.getNomEmpresa()+"' "
								  + "WHERE proveedor = '"+clave+"'"; 
        try {
        	Connection con = BDConex.getDataSource().getConnection();
            Statement st = con.createStatement();
            
            st.executeUpdate(sql);
            
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error en metodo actualizarProveedor: " + ex);
        }
	}
}
