package beans;

import java.util.ArrayList;
import java.util.HashMap;

public class CarroCompra {
	private static HashMap<Integer, LineaPedido> carro = new HashMap<Integer, LineaPedido>();
	
	
	public static HashMap<Integer, LineaPedido> getCarro() {
		return carro;
	}

	public static void setCarro(HashMap<Integer, LineaPedido> carro) {
		CarroCompra.carro = carro;
	}
	
	

	/*public void aniadeLinea(LineaPedido linea) {
		if(carro != null && carro.containsKey(linea.getItem().getId())) {
			for (Integer clave : carro.keySet()) {
				LineaPedido valor = carro.get(clave);
			    if(valor.getPedido().getId() == linea.getPedido().getId()) {
			    	valor.setCantidad(valor.getCantidad()+1);
			    	carro.put(clave, valor);
			    }
			}
		}else {
			carro.put(linea.getPedido().getId(), linea);
		}
	}*/
	
	public void borraLinea(int idItem) {
		
	}
	
	public LineaPedido getLineaPedido(int iditem) {
		LineaPedido l = new LineaPedido();
		
		return l;
	}
	
	public ArrayList<LineaPedido> getLineasPedido(){
		ArrayList<LineaPedido> arrl = new ArrayList<LineaPedido>();
		
		return arrl;
	}
	
	public double total() {
		double total = 0;
		
		return total;
	}
	
	public void removeAll() {
		
	}
	
	public boolean vacio() {
		boolean vacio = true;
		
		return vacio;
	}
}
