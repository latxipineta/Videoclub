package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import beans.DetallePago;
import conex.BDConex;

public class detallePagoDAO {
	
	public static void insertarDetallePago(DetallePago d) {
		
		String sql = "INSERT INTO detalle_pago(idPedido, numeroTarjeta, codigoSeguridad, precio, formaPago, fechaVencimiento) "
                + " VALUES(?, ?, ?, ?, ?, ?)";
        try {
        	Connection con = BDConex.getDataSource().getConnection();
            PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            st.setInt(1, d.getIdPedido());
            st.setInt(2, d.getNumeroTarjeta());
            st.setInt(3, d.getCodigoSeguridad());
            st.setFloat(4, d.getPrecio());
            st.setString(5, d.getFormaPago());
            st.setString(6, d.getFechaVencimiento());
            
            st.executeUpdate();
            
            st.close();
            con.close();
            
        } catch (SQLException ex) {
            System.err.println("Error en metodo insertarDetallePago: " + ex);
        }
	}
}
