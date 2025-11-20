package sn.edu.isepdiamniadio.dbe.WorkingExpress.entite;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Entity
@Table(name = "emails")
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String destinataire;

    private String sujet;

    @Column(length = 5000)
    private String contenu;

    public Email() {}

    public Email(String destinataire, String sujet, String contenu) {
        this.destinataire = destinataire;
        this.sujet = sujet;
        this.contenu = contenu;
    }

    public Long getId() {
        return id;
    }

    public String getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(String destinataire) {
        this.destinataire = destinataire;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    @Override
    public String toString() {
        return "Email{" +
                "id=" + id +
                ", destinataire='" + destinataire + '\'' +
                ", sujet='" + sujet + '\'' +
                ", contenu='" + contenu + '\'' +
                '}';
    }
}
