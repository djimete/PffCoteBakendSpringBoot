package sn.edu.isepdiamniadio.dbe.WorkingExpress.entite;


import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "service")
public class ExpressService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idService", nullable = false)
    private Integer idService;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "typeService", nullable = false)
    private String typeService;

    @Column(name = "description")
    private String description;

    // Relation ManyToOne avec Prestataire
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPrestataire", nullable = false)
    private sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.Prestataire prestataire;

    // Relation ManyToMany avec Client
    @ManyToMany(mappedBy = "services")
    private Set<sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.Client> clients;

    @Override
    public String toString() {
        return "Service{" +
                "idService=" + idService +
                ", nom='" + nom + '\'' +
                ", typeService='" + typeService + '\'' +
                ", description='" + description + '\'' +
                ", prestataire=" + (prestataire != null ? prestataire.getIdPrestataire() : null) +
                '}';
    }
}
