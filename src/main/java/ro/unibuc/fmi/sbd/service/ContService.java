package ro.unibuc.fmi.sbd.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.unibuc.fmi.sbd.dto.ContDto;
import ro.unibuc.fmi.sbd.entity.Cont;
import ro.unibuc.fmi.sbd.entity.Rol;
import ro.unibuc.fmi.sbd.entity.TipCont;
import ro.unibuc.fmi.sbd.entity.Utilizator;
import ro.unibuc.fmi.sbd.repository.ContRepository;
import ro.unibuc.fmi.sbd.repository.UtilizatorRepository;

import java.util.List;

@Service
public class ContService {
    private final UtilizatorRepository utilizatorRepository;
    private final ContRepository contRepository;

    public ContService(UtilizatorRepository utilizatorRepository, ContRepository contRepository) {
        this.utilizatorRepository = utilizatorRepository;
        this.contRepository = contRepository;
    }

    @Transactional
    public void creareCont() {
        Utilizator utilizator = utilizatorRepository.findById(1L).orElseThrow(() -> new RuntimeException(""));
        Cont cont = new Cont();
        cont.setSuma(0.);
        cont.setTipCont(TipCont.EXTERN);
        cont.setIban(GeneratorIban.genereazaIban());
        utilizator.getConturi().add(cont);
    }

    @Transactional(readOnly = true)
    public List<ContDto> vizualizareConturi() {
        Rol rol = Rol.MANAGER;

        if (rol == Rol.CLIENT) {
            Utilizator utilizator = utilizatorRepository.findById(1L).orElseThrow(() -> new RuntimeException(""));
            return utilizator.getConturi().stream()
                    .map(cont -> {
                        ContDto contDto = new ContDto();
                        contDto.setId(cont.getId());
                        contDto.setIban(cont.getIban());
                        contDto.setSuma(cont.getSuma());
                        contDto.setTipCont(cont.getTipCont());
                        return contDto;
                    })
                    .toList();
        } else if (rol == Rol.ANGAJAT || rol == Rol.MANAGER) {
            return contRepository.findAll().stream()
                    .map(cont -> {
                        ContDto contDto = new ContDto();
                        contDto.setId(cont.getId());
                        contDto.setIban(cont.getIban());
                        contDto.setSuma(cont.getSuma());
                        contDto.setTipCont(cont.getTipCont());
                        return contDto;
                    })
                    .toList();
        }
        throw new RuntimeException("Access interzis");
    }
}
