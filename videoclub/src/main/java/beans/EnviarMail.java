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
    	//metodo

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
    	//metodo
    
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
   }
}
