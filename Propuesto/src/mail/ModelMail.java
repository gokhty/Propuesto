package mail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;



public class ModelMail {

	private static Properties props = null;
	
	
	static{
		props = new Properties();
		
		try {
			props.load(new FileInputStream("src/mail.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public String pruebaMail(final String username, final String password, String subject, String to, String texto, String filePath) {
		Session session = null;
		Transport t = null;    
		 
		String mensaje = null;
		
		try {
			//Se obtiene la sesion de envio de correo
			session = Session.getDefaultInstance(props);
			
			//Se construye el mensaje de correo
			Message message = new MimeMessage(session);
			
			//Correo (Desde)
			message.setFrom(new InternetAddress(username));
			
			//Correo (hasta)
			String[] s = to.split(",");
			
			if(s.length ==1){
				message.setRecipients(Message.RecipientType.TO, 
								InternetAddress.parse(to));	
			}else{
				message.setRecipients(Message.RecipientType.CC,  
								InternetAddress.parse(to));
			}
			
			//Asunto
			message.setSubject(subject);
			
			//El cuerpo
			BodyPart bodyPart= new MimeBodyPart();
			bodyPart.setContent(texto, "text/html"); //Formato
			
			//Adjuntar archivo
			File f = new File(filePath);
			bodyPart.setDataHandler(new DataHandler(new FileDataSource(filePath)));
			bodyPart.setFileName(f.getName());
			
			MimeMultipart multipart = new MimeMultipart();
			multipart.addBodyPart(bodyPart);
			
			message.setContent(multipart);

			//Protocolo en envio
			t = session.getTransport("smtp");  
            t.connect(username, password);  
            
            //Se envia el Correo
            t.sendMessage(message, message.getAllRecipients()); 
			
            mensaje = "ENVIADO:  " + username;
		} catch (Exception e) {
			mensaje = "NO ENVIADO:  " + username+ " - "  + e.getMessage();
		} finally{
			try {
				if(t != null)t.close();	
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return mensaje;
	}
	
	

	
	
}
