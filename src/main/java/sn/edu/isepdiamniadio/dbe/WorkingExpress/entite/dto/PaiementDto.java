package sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
public class PaiementDto {
    private Integer idPaiement;
    private String modePaiement;
    private double montant;
    private LocalDate datePaiement;
    private Integer idClient; //  Id du client associé
}
