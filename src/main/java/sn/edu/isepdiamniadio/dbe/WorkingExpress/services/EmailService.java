package sn.edu.isepdiamniadio.dbe.WorkingExpress.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.Email;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.repository.EmailRepository;

import java.util.List;

@Service
public class EmailService {

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private JavaMailSender mailSender;

    private void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        message.setBcc("djimou.meta081@gmail.com","awa1999fall@gmail.com","adiarasall208@gmail.com","awasegnane67@gmail.com");
        message.setFrom("\"Working Express\"<no-reply@working-express.com>");
        System.out.println("############# Sending email...");
        mailSender.send(message);
    }

    // Enregistrer un email + simuler l’envoi
    public void envoyerEmail(String destinataire, String sujet, String contenu) {
        Email email = new Email(destinataire, sujet, contenu);
        email.setStatut("en_cours");
        emailRepository.save(email);
        try {
            sendSimpleMessage(destinataire,sujet,contenu);
            email.setStatut("ok");
        }catch (Exception ex){
            email.setStatut("error");
        }finally {
            emailRepository.save(email);
        }



        System.out.println("Email envoyé à " + destinataire + " : " + contenu);
    }

    // Récupérer tous les emails
    public List<Email> recupererEmails() {
        return emailRepository.findAll();
    }

    // Récupérer un email par ID
    public Email getEmailById(Long id) {
        return emailRepository.findById(id).orElse(null);
    }

    // Supprimer un email
    public boolean deleteEmail(Long id) {
        if (emailRepository.existsById(id)) {
            emailRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Rechercher par destinataire
    public List<Email> rechercherParDestinataire(String destinataire) {
        return emailRepository.findByDestinataire(destinataire);
    }
}
