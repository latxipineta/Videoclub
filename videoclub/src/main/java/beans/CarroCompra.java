package beans;

import java.util.HashMap;

public class CarroCompra {
	private static HashMap<Integer, LineaPedido> carro = new HashMap<Integer, LineaPedido>();
	
	public static void aniadeLinea(LineaPedido linea) {
		if(carro != null && carro.containsKey(linea.getIdProducto())) {
			for (Integer clave : carro.keySet()) {
				LineaPedido valor = carro.get(clave);
				//si el producto ya existe
			    if(valor.getIdProducto() == linea.getIdProducto()) {
			    	//le sumo 1 a la cantidad y sumo el precio total
			    	valor.setCantidad(valor.getCantidad()+1);
			    	valor.setPrecioTotal(valor.getPrecioTotal()+linea.getPrecioTotal());
			    	//lo añado al carro
			    	carro.put(clave, valor);
			    }
			}
		}else {
			//Si no existia ese producto en el carro lo añado
			carro.put(linea.getIdProducto(), linea);
		}
	}
	
	public static float devuelvePrecioTotal() {
		float precioTotal = 0;
		
		for (Integer clave : carro.keySet()) {
			LineaPedido valor = carro.get(clave);
			precioTotal = precioTotal + valor.getPrecioTotal();
		}
		
		return precioTotal;
	}
	
	
	public static void vaciarCarro() {
		carro.clear();
	}
	
	
	public static HashMap<Integer, LineaPedido> getCarro() {
		return carro;
	}

	public static void setCarro(HashMap<Integer, LineaPedido> carro) {
		CarroCompra.carro = carro;
	}
}
