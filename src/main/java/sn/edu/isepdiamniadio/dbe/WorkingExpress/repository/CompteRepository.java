package sn.edu.isepdiamniadio.dbe.WorkingExpress.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.Compte;

public interface CompteRepository extends JpaRepository<Compte, Long> {
}
