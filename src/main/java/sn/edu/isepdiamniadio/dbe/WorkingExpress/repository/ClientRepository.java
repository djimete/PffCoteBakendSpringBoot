package sn.edu.isepdiamniadio.dbe.WorkingExpress.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.Client;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    Optional<Client> findByEmail(String email);
}
