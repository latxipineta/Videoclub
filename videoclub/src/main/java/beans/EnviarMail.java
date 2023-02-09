package beans;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import dao.PedidoDAO;
import dao.ProductosDAO;

public class EnviarMail {
	
		
	/** GENERAR CONTRASEÑA ALEATORIA **/
    public static String generarPassword() {
    	ArrayList<Character> arrList=caracteresPermitidos();
    	String password="";
    	int longitud= (int)(Math.random()*8)+3; //Contraseña de 3-10 caracteres
    	for(int i=0; i<longitud;i++) {
    		password+=arrList.get((int)(Math.random()*arrList.size()));
    	}
    	
    	return password;
    }
        
    /** ENVIAR PEDIDO */
    	public static void enviarPedido(HashMap<Integer, LineaPedido>carroCompra, DetallePago detallepago, String correoDestinatario) {
    		String mensaje=generarMensajePedido(carroCompra, detallepago, correoDestinatario);//generar aqui string de mensaje del pedido 
    		enviarMensaje(correoDestinatario, "COMPRA REALIZADA", mensaje);
    	}

    /** ENVIAR NUEVA CONTRASEÑA */
    public static void enviarNuevaContraseña(String nombreUsuario, String correoDestinatario, String nuevaContraseña) {
    	String pass=generarMensajePassword(nombreUsuario, nuevaContraseña);
    	enviarMensaje(correoDestinatario, "SOLICITUD CAMBIO CONTRASEÑA", pass);
    }
    
    public static void enviarNuevaContraseñaAdmin(String nombreUsuario, String correoDestinatario, String nuevaContraseña) {
    	String pass=generarMensajePasswordAdmin(nombreUsuario, nuevaContraseña);
    	enviarMensaje(correoDestinatario, "CONTRASEÑA MODIFICADA", pass);
    }
        
    /***************************************************************************************************************************/
    
    /**PRIVADO - GENERAR MENSAJE PEDIDO */
    private static String generarMensajePedido(HashMap<Integer, LineaPedido> carroCompra, DetallePago detallePago, String correoUsuario) {
    	//VARIABLE A DEVOLVER
    	String mensaje="<h1>GRACIAS POR REALIZAR SU COMPRA!</h1><h2>Nº de pedido: ";
    	
    	
    	//VARIABLES DEL MÉTODO
    	HashMap<Integer, LineaPedido>carro=carroCompra;
    	int idPedido=0;
    	ArrayList<LineaPedido>arrListLP=new ArrayList<LineaPedido>();
    	
    	//Recorrer el carro de la compra y añadir las lineas de pedido a un arraylist
    	for(Integer idProducto:carro.keySet()) {
    		arrListLP.add(carro.get(idProducto));
    		//Obtener número de pedido
    		idPedido = carro.get(idProducto).getIdPedido();
    	}
    	
    	mensaje+=idPedido+"</h2><h2>Facturado a: "+correoUsuario+"</h2>";
    	
    	
    	//Recuperar detalles del pago
    	int numeroTarjeta=detallePago.getNumeroTarjeta();
    	float precio=detallePago.getPrecio();    	
    	
    	String fecha=DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(LocalDateTime.now())+"\n";
    	
    	mensaje+="<h2>Nº de tarjeta: "+numeroTarjeta+"</h2><h2>Fecha de compra: "+fecha+"</h2><h2>Pago realizado: "+precio+"&euro;</h2>";
    	mensaje+="<table><tr><th>Producto</th><th>Cantidad</th><th>Precio total</th><tr>";
    	
    	//Recuperar valores de las lineas de pedido
    	for(LineaPedido lp : arrListLP) {   		
    		//Obtener nombre del producto
    		String nombreProducto = ProductosDAO.getProducto(lp.getIdProducto()).getNombre();
    		int cantidadProducto=lp.getCantidad();
    		float precioTotalProducto=lp.getPrecioTotal();
    		
    		mensaje+="<tr><td>"+nombreProducto+"</td><td>"+cantidadProducto+"</td><td>"+precioTotalProducto+"&euro;</td></tr>";
    	}
    	
    	mensaje+="</table><h5><span style='color:red;'>&#169; Videoclub &trade;</span>se "
    			+ "reserva el derecho a cancelar el pedido en caso de detectar anomalías "
    			+ "por parte del comprador que afectasen gravemente los términos y "
    			+ "condiciones de la empresa. El comprador por su parte puede reclamar"
    			+ " la devolución o el reembolso del pedido durante un plazo máximo "
    			+ "de 7 días desde la fecha en la que se efectúa la compra. En caso"
    			+ " de duda, contacta con nostros a través de nuestro correo "
    			+ "videoclubsensillo@gmail.com</h5>";
    	
    	
    	return mensaje;
    }
    
    /**PRIVADO - GENERAR MENSAJE CONTRASEÑA **/
    private static String generarMensajePassword(String nombreUsuario, String nuevaContraseña) {
    	return "<h1>Hola "+nombreUsuario+"!</h1><br><h3>Te envíamos este correo porque nos has solicitado un cambio de contraseña.<br>Tu nueva contraseña es:</h3><h1><br><strong>"+nuevaContraseña+"</strong></h1><br>";
    }
    
    private static String generarMensajePasswordAdmin(String nombreUsuario, String nuevaContraseña) {
    	return "<h1>Hola "+nombreUsuario+"!</h1><br><h3>Por motivos de seguridad un ADMINISTRADOR de la web ha modificado tu contraseña.<br>Tu nueva contraseña es:</h3><h1><br><strong>"+nuevaContraseña+"</strong></h1><br>";
    }
    
    
    /** PRIVADO - METODO GENERICO PARA ENVIAR MENSAJE POR CORREO  */
    private static void enviarMensaje(String correoDestinatario, String asunto, String mensaje) {
    	// DESTINATARIO
        String destinatario = correoDestinatario;

        //INFOMRACIÓN VIDEOCLUB + VARIABLES DE COMPROBACIÓN
        String usuario = "videoclubsensillo@gmail.com";
        final String nombreUsuario = "videoclubsensillo";
        final String password = "dklyozrmcmzxepaq";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "25");

        Session session = Session.getInstance(props,
           new javax.mail.Authenticator() {
              protected PasswordAuthentication getPasswordAuthentication() {
                 return new PasswordAuthentication(nombreUsuario, password);
              }
           });

        try {

           // Create a default MimeMessage object.
           Message message = new MimeMessage(session);

           // Set From: header field of the header.
           message.setFrom(new InternetAddress(usuario));

           // Set To: header field of the header.
           message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));

           // Set Subject: header field
           message.setSubject(asunto);

           // This mail has 2 part, the BODY and the embedded image
           MimeMultipart multipart = new MimeMultipart("related");

           // first part (the html)
           BodyPart messageBodyPart = new MimeBodyPart();
           String htmlText = mensaje+"<img src=\"cid:image\">";
           messageBodyPart.setContent(htmlText, "text/html");
           multipart.addBodyPart(messageBodyPart);

           // second part (the image)
//           messageBodyPart = new MimeBodyPart();
//           DataSource fds = new FileDataSource("../../../imagenes/logoCorreo.PNG");
//
//           messageBodyPart.setDataHandler(new DataHandler(fds));
//           messageBodyPart.setHeader("Content-ID", "<image>");
//           multipart.addBodyPart(messageBodyPart);
           message.setContent(multipart);
           Transport.send(message);

           System.out.println("Correo enviado correctamente");

        } catch (MessagingException e) {
           throw new RuntimeException(e);
        }
    }
    
    /** PRIVADO -  GUARDAR CARACTERES PERMITIDOS */
    private static ArrayList<Character> caracteresPermitidos(){
    	ArrayList<Character> arrList=new ArrayList<Character>();
    	
    	//Añadir (# $ % &)
    	for (int i = 35; i < 39; i++) {
			arrList.add((char)i);
		}
    	
    	//Añadir (0-9)
    	for (int i = 48; i < 58; i++) {
			arrList.add((char)i);
		}
    	
    	//Añadir (A-Z)
    	for (int i = 65; i < 91; i++) {
			arrList.add((char)i);
		}
    	
    	//Añadir (a-z)
    	for (int i = 97; i < 123; i++) {
			arrList.add((char)i);
		}
    	
    	return arrList;
    }
	
	
   public static void main(String[] args) {

	   /**ENVIAR MENSAJE CON NUEVA CONTRASEÑA -> SOLICITUD CONTRASEÑA OLVIDADA*/
	   //enviarNuevaContraseña("Txipi","losreyesdesegunda@gmail.com", generarPassword());
	   
	   /**ENVIAR MENSAJE CON CONTRASEÑA MODIFICADA POR ADMINISTRADOR*/
	   //enviarNuevaContraseñaAdmin("I","ikerperezcarcamo1921@gmail.com", "laQuePongaElAdmin");
	   enviarNuevaContraseñaAdmin("Buenas noches","losreyesdesegunda@gmail.com", "DejoTodoParaVerAlDeportivoo");
   }
}
