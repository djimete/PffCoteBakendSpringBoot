package sn.edu.isepdiamniadio.dbe.WorkingExpress.entite;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "demande")
public class Demande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDemande", nullable = false)
    private Integer idDemande;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "dateMessage", nullable = false)
    private LocalDateTime dateMessage;

    @Column(name = "dateRetourDemande")
    private LocalDateTime dateRetourDemande;

    @Column(name = "statusDemande", nullable = false)
    private String statusDemande;

    // ✅ Relation ManyToOne avec Client
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idClient", nullable = false)
    private Client client;

    // ✅ Relation ManyToOne avec Prestataire
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPrestataire", nullable = false)
    private Prestataire prestataire;

    @Override
    public String toString() {
        return "Demande{" +
                "idDemande=" + idDemande +
                ", message='" + message + '\'' +
                ", dateMessage=" + dateMessage +
                ", dateRetourDemande=" + dateRetourDemande +
                ", statusDemande='" + statusDemande + '\'' +
                ", client=" + (client != null ? client.getIdClient() : null) +
                ", prestataire=" + (prestataire != null ? prestataire.getIdPrestataire() : null) +
                '}';
    }
}
