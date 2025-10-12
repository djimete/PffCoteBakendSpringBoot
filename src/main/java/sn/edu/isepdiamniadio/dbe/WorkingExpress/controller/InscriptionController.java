package sn.edu.isepdiamniadio.dbe.WorkingExpress.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.Utilisateur;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.dto.InscriptionRequest;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.dto.ValidationRequest;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.services.UtilisateurService;

@RestController
@RequestMapping("/api/inscription")
@Tag(name = "Inscription", description = "Création et validation d’un compte utilisateur")
public class InscriptionController {
    private final UtilisateurService utilisateurService;

    public InscriptionController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @Operation(summary = "Inscription utilisateur", description = "Envoie un code de validation par e-mail")
    @PostMapping
    public ResponseEntity<String> inscrire(@RequestBody InscriptionRequest req) {
        String code = utilisateurService.inscrireUtilisateur(req);
        return ResponseEntity.ok("Code envoyé à " + req.getEmail());
    }

    @Operation(summary = "Validation e-mail", description = "Valide le code reçu et active le compte utilisateur")
    @PostMapping("/valider")
    public ResponseEntity<?> valider(@RequestBody ValidationRequest req) {
        try {
            Utilisateur u = utilisateurService.validerEmail(req);
            return ResponseEntity.ok(u);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

}
