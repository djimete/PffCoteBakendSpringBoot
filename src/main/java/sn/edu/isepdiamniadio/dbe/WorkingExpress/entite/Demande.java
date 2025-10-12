package sn.edu.isepdiamniadio.dbe.WorkingExpress.entite;

import jakarta.persistence.*;
import lombok.*;

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

    @Column(name = "statusDemande", nullable = false)
    private String statusDemande;

    @Override
    public String toString() {
        return "Demande{" +
                "idDemande=" + idDemande +
                ", message='" + message + '\'' +
                ", statusDemande='" + statusDemande + '\'' +
                '}';
    }
}
