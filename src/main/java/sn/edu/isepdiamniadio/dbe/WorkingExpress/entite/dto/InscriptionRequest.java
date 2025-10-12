package sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InscriptionRequest {
    private String email;
    private String motDePasse;
    private String nom;
}
