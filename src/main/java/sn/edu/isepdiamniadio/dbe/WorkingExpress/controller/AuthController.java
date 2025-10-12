package sn.edu.isepdiamniadio.dbe.WorkingExpress.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.Utilisateur;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.dto.AuthRequest;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.dto.AuthResponse;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.repository.RoleRepository;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.repository.UtilisateurRepository;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.security.JwtUtil;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Gestion des auths", description = "API pour gérer les authentifications")
public class AuthController {

    @Autowired
    private UtilisateurRepository utilisateurRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping
    public String createAuth(@RequestBody Utilisateur utilisateur) {
        utilisateur.setMotDePasse(passwordEncoder.encode(utilisateur.getMotDePasse()));
        utilisateurRepo.save(utilisateur);

        // Ajout console
        System.out.println("##########  Enregistrement ! ########");
        System.out.println("Nouvel utilisateur enregistré : " + utilisateur.getEmail());

        return "Utilisateur enregistré avec succès";
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthRequest request) {

        // Ajout console
        System.out.println("##########  Tentative de Login ! ########");
        System.out.println("Email reçu : " + request.getEmail());
        System.out.println("Mot de passe reçu (avant encodage) : " + request.getMotDePasse());

        Utilisateur user = utilisateurRepo.findByEmail(request.getEmail()).orElse(null);

        if(user == null){
            System.out.println("Aucun utilisateur trouvé avec cet email !");
            return ResponseEntity.status(444).build();
        }

        if (passwordEncoder.matches(request.getMotDePasse(), user.getMotDePasse())) {
            String token = jwtUtil.generateToken(user.getEmail());

            // Ajout console
            System.out.println("Authentification réussie !");
            System.out.println("Token généré : " + token);
            AuthResponse response = new AuthResponse();
            response.setToken(token);

            return ResponseEntity.ok(response);
        } else {
            System.out.println("Mot de passe incorrect !");
            return ResponseEntity.status(444).build();
        }
    }

}
