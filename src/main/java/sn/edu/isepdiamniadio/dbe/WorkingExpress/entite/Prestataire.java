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
@Table(name = "prestataire")
public class Prestataire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPrestataire", nullable = false)
    private Integer idPrestataire;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "prenom", nullable = false)
    private String prenom;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "adresse", nullable = false)
    private String adresse;

    @Column(name = "telephone")
    private String telephone;

}
