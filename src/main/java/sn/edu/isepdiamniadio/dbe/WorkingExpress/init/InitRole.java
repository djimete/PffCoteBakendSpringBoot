package sn.edu.isepdiamniadio.dbe.WorkingExpress.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.Role;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.repository.RoleRepository;

@Component
@Order(1)
public class InitRole implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public InitRole(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("#### initialisation des roles");
        if(roleRepository.count()==0){
            System.out.println("pas de role en base,insertion de nouveau role");
            Role admin=new Role();
            admin.setNom("ROLE_admin");
            roleRepository.save(admin);

            Role prestataire=new Role();
            prestataire.setNom("ROLE_prestataire");
            roleRepository.save(prestataire);
            Role client=new Role();
            client.setNom("ROLE_client");
            roleRepository.save(client);


        }else {
            System.out.println("il existe déja des roles en base, pas d'insertion à faire");
        }
    }
}
