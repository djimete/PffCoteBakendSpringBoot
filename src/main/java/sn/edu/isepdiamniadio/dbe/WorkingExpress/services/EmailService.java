package sn.edu.isepdiamniadio.dbe.WorkingExpress.services;

import org.springframework.stereotype.Service;

@Service
public class EmailService {
    public void envoyerEmail(String destinataire, String sujet, String contenu) {
        System.out.println(" Email envoyé à " + destinataire + " : " + contenu);
        // Dans un vrai projet : utiliser JavaMailSender
    }
}
