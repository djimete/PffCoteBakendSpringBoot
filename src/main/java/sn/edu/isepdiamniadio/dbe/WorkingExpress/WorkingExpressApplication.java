package sn.edu.isepdiamniadio.dbe.WorkingExpress;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@SpringBootApplication
public class WorkingExpressApplication {
	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("mail30.lwspanel.com");
				mailSender.setPort(587);

		mailSender.setUsername("no-reply@working-express.com");
		mailSender.setPassword("tR4$ktPgV_kybSu");

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");
		return mailSender;
	}

	public static void main(String[] args) {
		SpringApplication.run(WorkingExpressApplication.class, args);
	}

}
