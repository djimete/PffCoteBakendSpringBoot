package sn.edu.isepdiamniadio.dbe.WorkingExpress.entite;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;
    @Column(nullable = false)
    private String prenom;
    private String telephone;
    private String adresse;

    @Column(nullable = false)
    private boolean actif = false;


    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true)
    private String motDePasse;

    @ManyToMany
    private List<Role> roles;
}

