package sn.edu.isepdiamniadio.dbe.WorkingExpress.init;


import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.services.EmailService;

@Component
public class Demarage implements CommandLineRunner {
    private final EmailService emailService;

    public Demarage(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public void run(String... args) throws Exception {
        emailService.envoyerEmail("djimouansou66@gmail.com","demarage du serveur","bonjour le système vient de démarer");

    }
}
