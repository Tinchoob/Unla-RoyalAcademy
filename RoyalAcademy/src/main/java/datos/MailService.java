package datos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@SpringBootApplication
@ComponentScan("datos")
public class MailService {
	@Autowired
    private JavaMailSender MailSender;

    public void sendMail(String correo, String asunto, String mensaje) {

        SimpleMailMessage mail = new SimpleMailMessage();

    
        mail.setTo(correo);
        mail.setSubject(asunto);
        mail.setText(mensaje);

        MailSender.send(mail);
    }
}
