package sn.edu.isepdiamniadio.dbe.WorkingExpress.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.Paiement;

public interface PaiementRepository extends JpaRepository<Paiement, Integer> {
}
