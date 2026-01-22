package ro.unibuc.fmi.sbd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.unibuc.fmi.sbd.entity.Credit;

public interface CreditRepository extends JpaRepository<Credit, Long> {
}
