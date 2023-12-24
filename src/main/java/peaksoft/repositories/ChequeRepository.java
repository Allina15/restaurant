package peaksoft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.models.Cheque;

public interface ChequeRepository extends JpaRepository<Cheque, Long> {
}
