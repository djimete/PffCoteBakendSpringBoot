package sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.dto.mapper;

import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.Client;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.Paiement;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.dto.PaiementDto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PaiementMapper {

    // Convertir Entité → DTO
    public static PaiementDto toDto(Paiement paiement) {
        if (paiement == null) return null;

        LocalDateTime datePaiement = paiement.getDatePaiement();

        return PaiementDto.builder()
                .idPaiement(paiement.getIdPaiement())
                .modePaiement(paiement.getModePaiement())
                .montant(paiement.getMontant())
                .datePaiement(datePaiement != null ? datePaiement.toLocalDate() : null)
                .idClient(paiement.getClient() != null ? paiement.getClient().getIdClient() : null)
                .build();
    }

    // Convertir DTO → Entité (avec client existant)
    public static Paiement toEntity(PaiementDto dto, Client client) {
        if (dto == null) return null;

        Paiement paiement = new Paiement();
        paiement.setIdPaiement(dto.getIdPaiement());
        paiement.setModePaiement(dto.getModePaiement());
        paiement.setMontant(dto.getMontant());

        // Conversion de la date (sans heure)
        LocalDate date = dto.getDatePaiement();
        if (date != null) {
            paiement.setDatePaiement(date.atStartOfDay());
        }

        // Association du client existant
        paiement.setClient(client);

        return paiement;
    }
}
