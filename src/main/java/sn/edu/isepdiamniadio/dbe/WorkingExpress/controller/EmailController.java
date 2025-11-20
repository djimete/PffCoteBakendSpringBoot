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
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.Email;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.services.EmailService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/emails")
@Tag(name = "Gestion des emails", description = "API pour l’envoi et la récupération des emails")
public class EmailController {

    @Autowired
    private EmailService emailService;


    @Operation(
            summary = "Envoyer un email",
            description = "Permet d’envoyer et d’enregistrer un email dans la base"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Email envoyé et enregistré avec succès"),
            @ApiResponse(responseCode = "400", description = "Données invalides"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @PostMapping("/envoyer")
    public ResponseEntity envoyerEmail(@RequestBody Email email) {
        try {
            emailService.envoyerEmail(email.getDestinataire(), email.getSujet(), email.getContenu());
            System.out.println("########## Nouvel email enregistré ########");
            System.out.println("Email : " + email);
            return ResponseEntity.status(HttpStatus.CREATED).body("Email envoyé et enregistré avec succès !");
        } catch (Exception e) {
            System.out.println("Erreur lors de l’envoi : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne !");
        }
    }


    @Operation(
            summary = "Liste des emails",
            description = "Retourne la liste de tous les emails enregistrés"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des emails trouvée"),
            @ApiResponse(responseCode = "404", description = "Aucun email trouvé"),
            @ApiResponse(responseCode = "500", description = "Erreur interne")
    })
    @GetMapping
    public ResponseEntity getAllEmails() {
        List<Email> emails = emailService.recupererEmails();
        System.out.println("########## Liste des emails ########");

        if (emails.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aucun email trouvé !");
        }
        return ResponseEntity.ok(emails);
    }


    @Operation(
            summary = "Détails d’un email",
            description = "Retourne les informations d’un email via son identifiant"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Email trouvé",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Email.class),
                            examples = @ExampleObject(value = "{ \"id\": 1, \"destinataire\": \"exemple@test.com\", \"sujet\": \"Test\", \"contenu\": \"Contenu du mail\" }")
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Email introuvable"),
            @ApiResponse(responseCode = "500", description = "Erreur interne")
    })
    @GetMapping("/{id}")
    public ResponseEntity getEmailById(@Parameter(description = "Identifiant de l’email") @PathVariable Long id) {
        Optional<Email> email = Optional.ofNullable(emailService.getEmailById(id));

        if (email.isPresent()) {
            return ResponseEntity.ok(email.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email introuvable !");
    }


    @Operation(
            summary = "Supprimer un email",
            description = "Supprime un email via son identifiant"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Email supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "Email introuvable"),
            @ApiResponse(responseCode = "500", description = "Erreur interne")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity deleteEmail(@Parameter(description = "Identifiant de l’email à supprimer") @PathVariable Long id) {
        try {
            boolean deleted = emailService.deleteEmail(id);

            if (!deleted) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email introuvable !");
            }

            System.out.println("########## Suppression de l’email ########");
            System.out.println("Email supprimé : " + id);
            return ResponseEntity.ok("Email supprimé avec succès !");
        } catch (Exception e) {
            System.out.println("Erreur lors de la suppression : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne !");
        }
    }


    @Operation(
            summary = "Rechercher des emails par destinataire",
            description = "Retourne tous les emails envoyés à une adresse donnée"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Emails trouvés"),
            @ApiResponse(responseCode = "404", description = "Aucun email trouvé pour ce destinataire"),
            @ApiResponse(responseCode = "500", description = "Erreur interne")
    })
    @GetMapping("/destinataire/{email}")
    public ResponseEntity getEmailsByDestinataire(@Parameter(description = "Adresse email du destinataire") @PathVariable String email) {
        List<Email> emails = emailService.rechercherParDestinataire(email);

        if (emails.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aucun email trouvé pour ce destinataire !");
        }
        return ResponseEntity.ok(emails);
    }
}
