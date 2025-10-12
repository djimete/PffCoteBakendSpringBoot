package sn.edu.isepdiamniadio.dbe.WorkingExpress.entite;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idClient", nullable = false)
    private Integer idClient;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "prenom", nullable = false)
    private String prenom;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "adresse", nullable = false)
    private String adresse;

    // Relation ManyToMany avec ExpressService si nécessaire
    // @ManyToMany ...
}
