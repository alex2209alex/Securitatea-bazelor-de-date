package ro.unibuc.fmi.sbd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.unibuc.fmi.sbd.entity.Cont;
import ro.unibuc.fmi.sbd.entity.TipCont;

import java.util.List;
import java.util.Optional;

public interface ContRepository extends JpaRepository<Cont, Long> {
    Optional<Cont> findByIban(String iban);

    List<Cont> findByTipCont(TipCont tipCont);
}
