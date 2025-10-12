package sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.dto;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class ClientDto {
    private Integer idClient;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;

    // On peut inclure les IDs des services associés
    private List<Integer> serviceIds;
}
