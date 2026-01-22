package ro.unibuc.fmi.sbd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.unibuc.fmi.sbd.entity.Utilizator;

public interface UtilizatorRepository extends JpaRepository<Utilizator, Long> {
}
