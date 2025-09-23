package sn.edu.isepdiamniadio.dbe.WorkingExpress.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.Utilisateur;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.repository.RoleRepository;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.repository.UtilisateurRepository;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.security.JwtUtil;

@RestController
@RequestMapping("/api/auths")
public class AuthController {

    @Autowired
    private UtilisateurRepository utilisateurRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public String register(@RequestBody Utilisateur utilisateur) {
        utilisateur.setMotDePasse(passwordEncoder.encode(utilisateur.getMotDePasse()));
        utilisateurRepo.save(utilisateur);
        return "Utilisateur enregistré avec succès";
    }

    @PostMapping("/login")
    public String login(@RequestBody Utilisateur utilisateur) {
        Utilisateur user = utilisateurRepo.findByEmail(utilisateur.getEmail())
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

        if (passwordEncoder.matches(utilisateur.getMotDePasse(), user.getMotDePasse())) {
            return jwtUtil.generateToken(user.getEmail());
        } else {
            throw new RuntimeException("Mot de passe incorrect");
        }
    }

    @GetMapping("/refresh")
    public String refresh(@RequestParam String token) {
        if (jwtUtil.validateToken(token)) {
            String email = jwtUtil.extractEmail(token);
            return jwtUtil.generateToken(email);
        }
        throw new RuntimeException("Token invalide");
    }
}

