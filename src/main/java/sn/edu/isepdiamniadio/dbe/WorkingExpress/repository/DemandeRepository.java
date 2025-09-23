package sn.edu.isepdiamniadio.dbe.WorkingExpress.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.Demande;

public interface DemandeRepository extends JpaRepository<Demande, Integer> {
}
