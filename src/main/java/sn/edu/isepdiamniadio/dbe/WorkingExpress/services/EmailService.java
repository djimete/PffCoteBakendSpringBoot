package sn.edu.isepdiamniadio.dbe.WorkingExpress.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.Email;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.repository.EmailRepository;

import java.util.List;

@Service
public class EmailService {

    @Autowired
    private EmailRepository emailRepository;

    // Enregistrer un email + simuler l’envoi
    public void envoyerEmail(String destinataire, String sujet, String contenu) {
        Email email = new Email(destinataire, sujet, contenu);
        emailRepository.save(email);

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
