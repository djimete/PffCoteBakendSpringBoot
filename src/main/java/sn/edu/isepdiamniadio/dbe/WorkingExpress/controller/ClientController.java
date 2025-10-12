package sn.edu.isepdiamniadio.dbe.WorkingExpress.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.Client;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.services.ClientService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clients")
@Tag(name = "Gestion des clients", description = "API pour gérer les clients (ajout, modification, suppression, consultation)")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Operation(
            summary = "Créer un client",
            description = "Cette méthode permet d’enregistrer un nouveau client"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Client créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Les données envoyées sont invalides"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        Client saved = clientService.createClient(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @Operation(
            summary = "Liste des clients",
            description = "Cette méthode retourne la liste de tous les clients enregistrés"
    )
    @ApiResponse(responseCode = "200", description = "Liste des clients trouvée")
    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientService.getAllClients();
        return ResponseEntity.ok(clients);
    }

    @Operation(
            summary = "Détails d’un client",
            description = "Permet de récupérer un client à partir de son identifiant"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client trouvé",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Client.class),
                            examples = @ExampleObject(
                                    value = "{ \"id\": 1, \"nom\": \"Diallo\", \"email\": \"diallo@gmail.com\", \"telephone\": \"+221771234567\" }"
                            )
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Client introuvable")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(
            @Parameter(description = "Identifiant du client à récupérer") @PathVariable Integer id) {

        Optional<Client> client = Optional.ofNullable(clientService.getClientById(id));
        return client.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Operation(
            summary = "Modifier un client",
            description = "Permet de mettre à jour les informations d’un client existant"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client mis à jour avec succès"),
            @ApiResponse(responseCode = "404", description = "Client introuvable")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(
            @Parameter(description = "Identifiant du client à modifier") @PathVariable Integer id,
            @RequestBody Client client) {

        Client updated = clientService.updateClient(id, client);
        return ResponseEntity.ok(updated);
    }

    @Operation(
            summary = "Supprimer un client",
            description = "Permet de supprimer un client par son identifiant"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "Client introuvable")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(
            @Parameter(description = "Identifiant du client à supprimer") @PathVariable Integer id) {

        clientService.deleteClient(id);
        return ResponseEntity.ok().build();
    }
}
