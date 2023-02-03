package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import beans.Genero;
import beans.Usuario;
import conex.BDConex;

public class GenerosDAO {
	public ArrayList<Genero> listaTodosGeneros() {
		ArrayList<Genero> arrlGeneros = new ArrayList<Genero>();
		
        String sql = "SELECT * FROM genero";
        try {
            Connection con = BDConex.getDataSource().getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
            	Genero gen = new Genero(	rs.getInt("idGenero"), 
					                		rs.getString("nombre")
					                		);
                
            	arrlGeneros.add(gen);
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error en metodo listaTodosGeneros: " + ex);
        }
		
		return arrlGeneros;
	}
	
	static public String devuelveNombreGenero(int idGenero) {
		String nomGenero = "";
		
        String sql = "SELECT nombre FROM genero WHERE idGenero = "+idGenero;
        try {
            Connection con = BDConex.getDataSource().getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
            	nomGenero = rs.getString("nombre");
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error en metodo devuelveNombreGenero: " + ex);
        }
		
		return nomGenero;
	}
}
