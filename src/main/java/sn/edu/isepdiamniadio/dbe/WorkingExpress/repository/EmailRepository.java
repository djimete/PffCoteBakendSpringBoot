package sn.edu.isepdiamniadio.dbe.WorkingExpress.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.Email;

import java.util.List;

public interface EmailRepository extends JpaRepository<Email, Long> {
    List<Email> findByDestinataire(String destinataire);
}
