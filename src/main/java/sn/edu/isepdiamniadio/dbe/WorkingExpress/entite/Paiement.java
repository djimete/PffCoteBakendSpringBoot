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


    // 🔹 Relation ManyToOne avec Client
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Override
    public String toString() {
        return "Paiement{" +
                "idPaiement=" + idPaiement +
                ", modePaiement='" + modePaiement + '\'' +
                ", montant=" + montant +
                ", datePaiement=" + datePaiement +
                ", client=" + (client != null ? client.getIdClient() : null) +
                '}';
    }
}
