package sn.edu.isepdiamniadio.dbe.WorkingExpress.entite;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "paiement")
public class Paiement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPaiement", nullable = false)
    private Integer idPaiement;

    @Column(name = "modePaiement", nullable = false)
    private String modePaiement;

    @Column(name = "montant", nullable = false)
    private Double montant;

    @Column(name = "datePaiement", nullable = false)
    private LocalDateTime datePaiement;

    @Column(name = "heurePaiement", nullable = false)
    private LocalTime heurePaiement;

    // Relation ManyToOne avec Client
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idClient", nullable = false)
    private sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.Client client;

    @Override
    public String toString() {
        return "Paiement{" +
                "idPaiement=" + idPaiement +
                ", modePaiement=" + modePaiement +
                ", montant=" + montant +
                ", datePaiement=" + datePaiement +
                ", heurePaiement=" + heurePaiement +
                ", client=" + (client != null ? client.getIdClient() : null) +
                '}';
    }
}
