package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conex.BDConex;

public class ValoracionesDAO {
	public static  ArrayList<String> ValoracionesProducto(int id) {
		ArrayList<String> arrlValoraciones = new ArrayList<String>();
		
        String sql = "SELECT * FROM valoraciones WHERE idProducto = "+id;
        try {
            Connection con = BDConex.getDataSource().getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
            	arrlValoraciones.add(rs.getString("notaIMDB"));
            	arrlValoraciones.add(rs.getString("notaFILMAFFINTY"));
            	arrlValoraciones.add(rs.getString("notaSENSACINE"));
            	arrlValoraciones.add(rs.getString("notaTMDB"));
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error en metodo ValoracionesProducto: " + ex);
        }
		
		return arrlValoraciones;
	}
}
