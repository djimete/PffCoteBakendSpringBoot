package sn.edu.isepdiamniadio.dbe.WorkingExpress.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.Client;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.Paiement;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.dto.PaiementDto;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.dto.mapper.PaiementMapper;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.services.ClientService;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.services.PaiementService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Component
@Order(3)
public class InitPaiement implements CommandLineRunner {

    private final PaiementService paiementService;
    private final ClientService clientService; // 🔹 pour récupérer un client existant

    public InitPaiement(PaiementService paiementService, ClientService clientService) {
        this.paiementService = paiementService;
        this.clientService = clientService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (paiementService.findAll().isEmpty() && !clientService.getAllClients().isEmpty()) {

            // Récupérer un client existant (ici on prend le premier client pour l'exemple)
            Client client = clientService.getAllClients().get(0);

            // Création des paiements
            List<PaiementDto> paiements = List.of(
                    PaiementDto.builder()
                            .modePaiement("cash")
                            .montant(45000)
                            .datePaiement(LocalDate.of(2025, 9, 8))
                            .idClient(client.getIdClient())
                            .build(),

                    PaiementDto.builder()
                            .modePaiement("card")
                            .montant(75000)
                            .datePaiement(LocalDate.of(2025, 9, 9))
                            .idClient(client.getIdClient())
                            .build(),

                    PaiementDto.builder()
                            .modePaiement("mobile")
                            .montant(30000)
                            .datePaiement(LocalDate.of(2025, 9, 10))
                            .idClient(client.getIdClient())
                            .build()
            );

            // Conversion DTO → Entity et sauvegarde
            for (PaiementDto dto : paiements) {
                Paiement entity = PaiementMapper.toEntity(dto, client); // yy passer le client
                paiementService.save(entity);
            }

            System.out.println("Initialisation des paiements terminée !");
        }
    }
}
