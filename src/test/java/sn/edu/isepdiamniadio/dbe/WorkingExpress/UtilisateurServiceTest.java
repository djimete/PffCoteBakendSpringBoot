package sn.edu.isepdiamniadio.dbe.WorkingExpress;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.Utilisateur;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.dto.InscriptionRequest;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.dto.ValidationRequest;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.repository.UtilisateurRepository;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.services.EmailService;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.services.UtilisateurService;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UtilisateurServiceTest {
    @Mock
    private UtilisateurRepository repo;
    @Mock
    private EmailService emailService;
    @InjectMocks
    private UtilisateurService utilisateurService;

    @Test
    void testInscrireUtilisateur_envoieEmail() {
        InscriptionRequest req = new InscriptionRequest();
        req.setEmail("test@example.com");

        when(repo.existsByEmail("test@example.com")).thenReturn(false);

        String code = utilisateurService.inscrireUtilisateur(req);

        assertNotNull(code);
        verify(emailService, times(1))
                .envoyerEmail(eq("test@example.com"), anyString(), contains("Votre code"));
    }

    @Test
    void testValiderEmail_succes() {
        InscriptionRequest req = new InscriptionRequest();
        req.setEmail("test@example.com");

        when(repo.existsByEmail("test@example.com")).thenReturn(false);
        utilisateurService.inscrireUtilisateur(req);

        ValidationRequest validation = new ValidationRequest();
        validation.setEmail("test@example.com");
        validation.setCode(utilisateurService
                .getCodesValidation().get("test@example.com")); // facultatif si getter

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail("test@example.com");

        when(repo.save(any(Utilisateur.class))).thenReturn(utilisateur);

        Utilisateur result = utilisateurService.validerEmail(validation);
        assertEquals("test@example.com", result.getEmail());
        verify(repo, times(1)).save(any(Utilisateur.class));
    }

    @Test
    void testValiderEmail_codeInvalide() {
        ValidationRequest req = new ValidationRequest();
        req.setEmail("wrong@example.com");
        req.setCode("123456");

        assertThrows(IllegalArgumentException.class, () -> utilisateurService.validerEmail(req));
    }
}
