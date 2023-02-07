package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import beans.LineaPedido;
import beans.Pedido;
import conex.BDConex;

public class PedidoDAO {
	public static ArrayList<Pedido> devuelvePedidosDeUsuario(String correo) {
		ArrayList<Pedido> arrlPedidos = new ArrayList<Pedido>();
		
        String sql = "SELECT * FROM pedido WHERE correoUsuario = '"+correo+"'";	
        try {
            Connection con = BDConex.getDataSource().getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
            	 Pedido p = new Pedido(	rs.getInt("idPedido"), 
				                		rs.getFloat("precio"), 
				                		rs.getString("fecha"), 
				                		rs.getString("correoUsuario"), 
				                		rs.getString("nombrePedido")
				                		);
            	 arrlPedidos.add(p);
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error en metodo devuelveUltimoPedido: " + ex);
        }
		
		return arrlPedidos;
	}
	
	public static ArrayList<LineaPedido> devuelveLineasDePedido(int idPedido) {
		ArrayList<LineaPedido> arrlLineasPedido = new ArrayList<LineaPedido>();
		
        String sql = "SELECT * FROM linea_pedido WHERE idPedido = "+idPedido;	
        try {
            Connection con = BDConex.getDataSource().getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
            	LineaPedido p = new LineaPedido( rs.getInt("idProducto"), 
						                		 rs.getInt("idPedido"), 
						                		 rs.getInt("cantidad"), 
						                		 rs.getFloat("precioTotal")
						                		);
            	arrlLineasPedido.add(p);
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error en metodo devuelveLineasDePedido: " + ex);
        }
		
		return arrlLineasPedido;
	}
	
	public static int devuelveUltimoPedido() {
		int id = 0;
		
        String sql = "SELECT max(idPedido) as idPedido FROM pedido";
        try {
            Connection con = BDConex.getDataSource().getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
            	id = rs.getInt("idPedido"); 
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error en metodo devuelveUltimoPedido: " + ex);
        }
		
		return id+1;
	}
	
	public static void insertarPedido(Pedido p, HashMap<Integer, LineaPedido> carro) {
		
		String sql = "INSERT INTO pedido(idPedido, precio, fecha, correoUsuario, nombrePedido) "
                + " VALUES(?, ?, ?, ?, ?)";
        try {
        	Connection con = BDConex.getDataSource().getConnection();
            PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            st.setInt(1, p.getIdPedido());
            st.setFloat(2, p.getPrecio());
            st.setString(3, p.getFecha());
            st.setString(4, p.getCorreoUsuario());
            st.setString(5, p.getNombrePedido());
            
            st.executeUpdate();
            
            st.close();
            con.close();
            
            for (Integer clave : carro.keySet()) {
    			LineaPedido valor = carro.get(clave);
    			insertarLineasPedido(valor);
    		}
            
        } catch (SQLException ex) {
            System.err.println("Error en metodo insertarPedido: " + ex);
        }
	}
	
	public static void insertarLineasPedido(LineaPedido lineaPedido) {
		
		String sql = "INSERT INTO linea_pedido(idProducto, idPedido, cantidad, precioTotal) "
                + " VALUES(?, ?, ?, ?)";
        try {
        	Connection con = BDConex.getDataSource().getConnection();
            PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            st.setInt(1, lineaPedido.getIdProducto());
            st.setInt(2, lineaPedido.getIdPedido());
            st.setInt(3, lineaPedido.getCantidad());
            st.setFloat(4, lineaPedido.getPrecioTotal());
            
            st.executeUpdate();
            
            st.close();
            con.close();
            
        } catch (SQLException ex) {
            System.err.println("Error en metodo insertarLineasPedido: " + ex);
        }
	}
}
