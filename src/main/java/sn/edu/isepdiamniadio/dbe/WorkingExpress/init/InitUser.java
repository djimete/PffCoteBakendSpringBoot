package sn.edu.isepdiamniadio.dbe.WorkingExpress.init;

import org.aspectj.bridge.ICommand;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.Role;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.Utilisateur;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.repository.RoleRepository;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.repository.UtilisateurRepository;

import java.util.List;

@Component
@Order(2)
public class InitUser implements CommandLineRunner {
    private final UtilisateurRepository utilisateurRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public InitUser(UtilisateurRepository utilisateurRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if(utilisateurRepository.count()==0){
            Utilisateur user=new Utilisateur();
            user.setNom("Djimou");
            user.setPrenom("Meta");
            user.setEmail("djimou.meta081@gmail.com");
            user.setMotDePasse(passwordEncoder.encode("passer"));


            Role admin=roleRepository.findByNom("ROLE_admin").get();
            Role prestataire=roleRepository.findByNom("ROLE_prestataire").get();
            Role client=roleRepository.findByNom("ROLE_client").get();
            user.setRoles(List.of(admin,client, prestataire));

            utilisateurRepository.save(user);

            Utilisateur user2=new Utilisateur();
            user2.setNom("Diouf");
            user2.setPrenom("Fatou");
            user2.setEmail("diouf.fatou23@gmail.com");
            user2.setMotDePasse(passwordEncoder.encode("passer123"));

            user2.setRoles(List.of(prestataire));

            utilisateurRepository.save(user2);


            Utilisateur user3=new Utilisateur();
            user3.setNom("Fall");
            user3.setPrenom("Fatoumata");
            user3.setEmail("f.fatoumata12@gmail.com");
            user3.setMotDePasse(passwordEncoder.encode("passer098"));

            user3.setRoles(List.of(client, prestataire));

            utilisateurRepository.save(user3);




        }

    }
}
