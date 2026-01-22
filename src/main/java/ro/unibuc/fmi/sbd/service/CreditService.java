package ro.unibuc.fmi.sbd.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.unibuc.fmi.sbd.dto.CreareCreditDto;
import ro.unibuc.fmi.sbd.dto.CreditDto;
import ro.unibuc.fmi.sbd.entity.*;
import ro.unibuc.fmi.sbd.repository.ContRepository;
import ro.unibuc.fmi.sbd.repository.CreditRepository;
import ro.unibuc.fmi.sbd.repository.UtilizatorRepository;

import java.util.List;

@Service
public class CreditService {
    private final CreditRepository creditRepository;
    private final ContRepository contRepository;
    private final UtilizatorRepository utilizatorRepository;

    public CreditService(CreditRepository creditRepository, ContRepository contRepository, UtilizatorRepository utilizatorRepository) {
        this.creditRepository = creditRepository;
        this.contRepository = contRepository;
        this.utilizatorRepository = utilizatorRepository;
    }

    @Transactional
    public void creareCerereCredit(CreareCreditDto creareCreditDto) {
        Cont cont = contRepository.findByIban(creareCreditDto.getIban()).orElseThrow(() -> new RuntimeException("Iban incorect"));
        cont.getUtilizatori().stream().filter(utilizator -> utilizator.getId() == 1)
                .findFirst().orElseThrow(() -> new RuntimeException("Iban incorect"));
        Credit credit = new Credit();
        credit.setContUtilizator(cont);
        credit.setSuma(creareCreditDto.getSuma());
        credit.setStatus(StatusCredit.ASTEAPTA_APROBARE);
        creditRepository.save(credit);
    }

    @Transactional
    public void aprobareCredit(Long id) {
        Credit credit = creditRepository.findById(id).orElseThrow(() -> new RuntimeException("Credit inexistent"));
        if (credit.getSuma() <= 5000) {

        } else {

        }
        if (credit.getStatus() != StatusCredit.ASTEAPTA_APROBARE) {
            throw new RuntimeException("Credit nu poate fi aprobat");
        }
        List<Cont> conturi = contRepository.findByTipCont(TipCont.INTERN);
        Cont cont = SelectorContDeRestituire.selectContRandom(conturi);
        credit.setStatus(StatusCredit.APROBAT);
        credit.setContRestituire(cont);
    }

    @Transactional
    public void respingereCredit(Long id) {
        Credit credit = creditRepository.findById(id).orElseThrow(() -> new RuntimeException("Credit inexistent"));
        if (credit.getStatus() != StatusCredit.ASTEAPTA_APROBARE) {
            throw new RuntimeException("Credit nu poate fi respins");
        }
        credit.setStatus(StatusCredit.RESPINS);
    }

    @Transactional
    public List<CreditDto> vizualizareCredite() {
        Rol rol = Rol.MANAGER;
        switch (rol) {
            case Rol.CLIENT -> {
                Utilizator utilizator = utilizatorRepository.findById(1L).orElseThrow(() -> new RuntimeException("Utilizator inexistent"));
                return utilizator.getConturi().stream()
                        .map(Cont::getCrediteLivrateInCont)
                        .flatMap(List::stream)
                        .map(credit -> {
                            CreditDto creditDto = new CreditDto();
                            creditDto.setId(credit.getId());
                            creditDto.setSuma(credit.getSuma());
                            creditDto.setIbanUtilizator(credit.getContUtilizator().getIban());
                            creditDto.setIbanRestituire(credit.getContRestituire() == null ? "" : credit.getContRestituire().getIban());
                            creditDto.setStatus(credit.getStatus());
                            return creditDto;
                        })
                        .toList();
            }
            case Rol.ANGAJAT -> {
                return creditRepository.findAll().stream()
                        .map(credit -> {
                            CreditDto creditDto = new CreditDto();
                            creditDto.setId(credit.getId());
                            creditDto.setSuma(credit.getSuma());
                            creditDto.setIbanUtilizator(credit.getContUtilizator().getIban());
                            creditDto.setIbanRestituire(credit.getContRestituire() == null ? "" : credit.getContRestituire().getIban());
                            creditDto.setStatus(credit.getStatus());
                            return creditDto;
                        })
                        .filter(creditDto -> creditDto.getSuma() <= 5000)
                        .toList();
            }
            case Rol.MANAGER -> {
                return creditRepository.findAll().stream()
                        .map(credit -> {
                            CreditDto creditDto = new CreditDto();
                            creditDto.setId(credit.getId());
                            creditDto.setSuma(credit.getSuma());
                            creditDto.setIbanUtilizator(credit.getContUtilizator().getIban());
                            creditDto.setIbanRestituire(credit.getContRestituire() == null ? "" : credit.getContRestituire().getIban());
                            creditDto.setStatus(credit.getStatus());
                            return creditDto;
                        })
                        .toList();
            }
            default -> throw new RuntimeException("Access interzis");
        }
    }
}
