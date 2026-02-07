package sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InscriptionRequest {
    private String email;
    private String motDePasse;
    private String nom;
    private String prenom;
    private String telephone;
    private String adresse;
}
