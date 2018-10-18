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
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/*
 * para permitir envio de correo por gmail
 * https://myaccount.google.com/lesssecureapps
 */

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
	
	//Se envie varios archivos
	public String pruebaMail(final String username, final String password, String subject, String to, String texto, String filePath) {
		Session session = null;
		Transport t = null;    
		 
		String mensaje = null;
		
		try {
			//Se obtiene la sesion de envio de correo
			session = Session.getDefaultInstance(props);
			
			//Se construye el mensaje de correo
			//message-->Es el objeto donde se configura el mensaje
			Message message = new MimeMessage(session);
			
			//Destinatario
			//Los tipos son TO,BCC,CC
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			
			//Asunto
			message.setSubject(subject);
			
			//Remitente
			message.setFrom(new InternetAddress(username));
		
			//Recipiente
			Multipart multipart = new MimeMultipart();
			
			//Texto(En un BodyPart)
			BodyPart bodyTexto = new MimeBodyPart();
			bodyTexto.setContent(texto,"text/html");
			multipart.addBodyPart(bodyTexto);
			
			//Attachment(En otro BodyPart)
			String[] rutas = filePath.split(",");
			for (String x : rutas) {
				File f = new File(x);
				BodyPart bodyFile = new MimeBodyPart();
				bodyFile.setDataHandler(new DataHandler(new FileDataSource(f)));
				bodyFile.setFileName(f.getName());
				multipart.addBodyPart(bodyFile);
			}
			//Se añade el recipiente al mensaje
			message.setContent(multipart);
			
			//Envío
			t = session.getTransport("smtp");
			t.connect(username, password);
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
	
	
	//Se envíe un archivo PDF
	public String pruebaMail_v02(final String username, final String password, String subject, String to, String texto, String filePath) {
		Session session = null;
		Transport t = null;    
		 
		String mensaje = null;
		
		try {
			//Se obtiene la sesion de envio de correo
			session = Session.getDefaultInstance(props);
			
			//Se construye el mensaje de correo
			//message-->Es el objeto donde se configura el mensaje
			Message message = new MimeMessage(session);
			
			//Destinatario
			//Los tipos son TO,BCC,CC
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			
			//Asunto
			message.setSubject(subject);
			
			//Remitente
			message.setFrom(new InternetAddress(username));
			
			//Texto(En un BodyPart)
			BodyPart bodyTexto = new MimeBodyPart();
			bodyTexto.setContent(texto,"text/html");
			
			//Attachment(En otro BodyPart)
			File f = new File(filePath);
			BodyPart bodyFile = new MimeBodyPart();
			bodyFile.setDataHandler(new DataHandler(new FileDataSource(f)));
			bodyFile.setFileName(f.getName());
			
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(bodyTexto);
			multipart.addBodyPart(bodyFile);
			message.setContent(multipart);
			
			//Envío
			t = session.getTransport("smtp");
			t.connect(username, password);
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
	
	//Envío simple de correo
	public String pruebaMail_v01(final String username, final String password, String subject, String to, String texto, String filePath) {
		Session session = null;
		Transport t = null;    
		 
		String mensaje = null;
		
		try {
			//Se obtiene la sesion de envio de correo
			session = Session.getDefaultInstance(props);
			
			//Se construye el mensaje de correo
			//message-->Es el objeto donde se configura el mensaje
			Message message = new MimeMessage(session);
			
			//Destinatario
			//Los tipos son TO,BCC,CC
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			
			//Asunto
			message.setSubject(subject);
			
			//Texto
			message.setText(texto);
			
			//Remitente
			message.setFrom(new InternetAddress(username));
			
			//Envío
			t = session.getTransport("smtp");
			t.connect(username, password);
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
