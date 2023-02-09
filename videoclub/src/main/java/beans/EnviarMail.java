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
	
		
	/** GENERAR CONTRASE�A ALEATORIA **/
    public static String generarPassword() {
    	ArrayList<Character> arrList=caracteresPermitidos();
    	String password="";
    	int longitud= (int)(Math.random()*8)+3; //Contrase�a de 3-10 caracteres
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

    /** ENVIAR NUEVA CONTRASE�A */
    public static void enviarNuevaContrase�a(String nombreUsuario, String correoDestinatario, String nuevaContrase�a) {
    	String pass=generarMensajePassword(nombreUsuario, nuevaContrase�a);
    	enviarMensaje(correoDestinatario, "SOLICITUD CAMBIO CONTRASE�A", pass);
    }
    
    public static void enviarNuevaContrase�aAdmin(String nombreUsuario, String correoDestinatario, String nuevaContrase�a) {
    	String pass=generarMensajePasswordAdmin(nombreUsuario, nuevaContrase�a);
    	enviarMensaje(correoDestinatario, "CONTRASE�A MODIFICADA", pass);
    }
        
    /***************************************************************************************************************************/
    
    /**PRIVADO - GENERAR MENSAJE PEDIDO */
    private static String generarMensajePedido(HashMap<Integer, LineaPedido> carroCompra, DetallePago detallePago, String correoUsuario) {
    	//VARIABLE A DEVOLVER
    	String mensaje="<h1>GRACIAS POR REALIZAR SU COMPRA!</h1><h2>N� de pedido: ";
    	
    	
    	//VARIABLES DEL M�TODO
    	HashMap<Integer, LineaPedido>carro=carroCompra;
    	int idPedido=0;
    	ArrayList<LineaPedido>arrListLP=new ArrayList<LineaPedido>();
    	
    	//Recorrer el carro de la compra y a�adir las lineas de pedido a un arraylist
    	for(Integer idProducto:carro.keySet()) {
    		arrListLP.add(carro.get(idProducto));
    		//Obtener n�mero de pedido
    		idPedido = carro.get(idProducto).getIdPedido();
    	}
    	
    	mensaje+=idPedido+"</h2><h2>Facturado a: "+correoUsuario+"</h2>";
    	
    	
    	//Recuperar detalles del pago
    	int numeroTarjeta=detallePago.getNumeroTarjeta();
    	float precio=detallePago.getPrecio();    	
    	
    	String fecha=DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(LocalDateTime.now())+"\n";
    	
    	mensaje+="<h2>N� de tarjeta: "+numeroTarjeta+"</h2><h2>Fecha de compra: "+fecha+"</h2><h2>Pago realizado: "+precio+"&euro;</h2>";
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
    			+ "reserva el derecho a cancelar el pedido en caso de detectar anomal�as "
    			+ "por parte del comprador que afectasen gravemente los t�rminos y "
    			+ "condiciones de la empresa. El comprador por su parte puede reclamar"
    			+ " la devoluci�n o el reembolso del pedido durante un plazo m�ximo "
    			+ "de 7 d�as desde la fecha en la que se efect�a la compra. En caso"
    			+ " de duda, contacta con nostros a trav�s de nuestro correo "
    			+ "videoclubsensillo@gmail.com</h5>";
    	
    	
    	return mensaje;
    }
    
    /**PRIVADO - GENERAR MENSAJE CONTRASE�A **/
    private static String generarMensajePassword(String nombreUsuario, String nuevaContrase�a) {
    	return "<h1>Hola "+nombreUsuario+"!</h1><br><h3>Te env�amos este correo porque nos has solicitado un cambio de contrase�a.<br>Tu nueva contrase�a es:</h3><h1><br><strong>"+nuevaContrase�a+"</strong></h1><br>";
    }
    
    private static String generarMensajePasswordAdmin(String nombreUsuario, String nuevaContrase�a) {
    	return "<h1>Hola "+nombreUsuario+"!</h1><br><h3>Por motivos de seguridad un ADMINISTRADOR de la web ha modificado tu contrase�a.<br>Tu nueva contrase�a es:</h3><h1><br><strong>"+nuevaContrase�a+"</strong></h1><br>";
    }
    
    
    /** PRIVADO - METODO GENERICO PARA ENVIAR MENSAJE POR CORREO  */
    private static void enviarMensaje(String correoDestinatario, String asunto, String mensaje) {
    	// DESTINATARIO
        String destinatario = correoDestinatario;

        //INFOMRACI�N VIDEOCLUB + VARIABLES DE COMPROBACI�N
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
    	
    	//A�adir (# $ % &)
    	for (int i = 35; i < 39; i++) {
			arrList.add((char)i);
		}
    	
    	//A�adir (0-9)
    	for (int i = 48; i < 58; i++) {
			arrList.add((char)i);
		}
    	
    	//A�adir (A-Z)
    	for (int i = 65; i < 91; i++) {
			arrList.add((char)i);
		}
    	
    	//A�adir (a-z)
    	for (int i = 97; i < 123; i++) {
			arrList.add((char)i);
		}
    	
    	return arrList;
    }
	
	
   public static void main(String[] args) {

	   /**ENVIAR MENSAJE CON NUEVA CONTRASE�A -> SOLICITUD CONTRASE�A OLVIDADA*/
	   //enviarNuevaContrase�a("Txipi","losreyesdesegunda@gmail.com", generarPassword());
	   
	   /**ENVIAR MENSAJE CON CONTRASE�A MODIFICADA POR ADMINISTRADOR*/
	   //enviarNuevaContrase�aAdmin("I","ikerperezcarcamo1921@gmail.com", "laQuePongaElAdmin");
	   enviarNuevaContrase�aAdmin("Buenas noches","losreyesdesegunda@gmail.com", "DejoTodoParaVerAlDeportivoo");
   }
}
