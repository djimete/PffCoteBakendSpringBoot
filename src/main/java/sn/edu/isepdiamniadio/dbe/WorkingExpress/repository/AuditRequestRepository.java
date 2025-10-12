package sn.edu.isepdiamniadio.dbe.WorkingExpress.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.AuditRequest;

public interface AuditRequestRepository extends JpaRepository<AuditRequest, Long> {
}
