package ro.unibuc.fmi.sbd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.unibuc.fmi.sbd.entity.Utilizator;

import java.util.Optional;

public interface UtilizatorRepository extends JpaRepository<Utilizator, Long> {
    Optional<Utilizator> findByUsername(String username);
}
