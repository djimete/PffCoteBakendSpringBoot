package sn.edu.isepdiamniadio.dbe.WorkingExpress.init;


import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.services.EmailService;

@Component
public class Demarrage implements CommandLineRunner {
    private final EmailService emailService;

    public Demarrage(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public void run(String... args) throws Exception {
        emailService.envoyerEmail("djimouansou66@gmail.com","demarrage du serveur","bonjour le système vient de démarer");

    }
}
