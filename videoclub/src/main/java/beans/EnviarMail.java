package beans;

import java.util.ArrayList;
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
    	//metodo

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
    	//metodo
    
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
           messageBodyPart = new MimeBodyPart();
           DataSource fds = new FileDataSource("../../webapp/imagenes/logoCorreo.PNG");

           messageBodyPart.setDataHandler(new DataHandler(fds));
           messageBodyPart.setHeader("Content-ID", "<image>");
           multipart.addBodyPart(messageBodyPart);
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
   }
}
