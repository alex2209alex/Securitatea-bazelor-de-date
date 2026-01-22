package ro.unibuc.fmi.sbd.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.unibuc.fmi.sbd.dto.AngajatDto;
import ro.unibuc.fmi.sbd.dto.CreareClientDto;
import ro.unibuc.fmi.sbd.dto.ModificareDateAngajatDto;
import ro.unibuc.fmi.sbd.entity.Rol;
import ro.unibuc.fmi.sbd.entity.Utilizator;
import ro.unibuc.fmi.sbd.repository.UtilizatorRepository;

import java.util.List;

@Service
public class UtilizatorService {
    private final UtilizatorRepository utilizatorRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public UtilizatorService(UtilizatorRepository utilizatorRepository) {
        this.utilizatorRepository = utilizatorRepository;
    }

    @Transactional
    public void creareClient(CreareClientDto dto) {
        StoredProcedureQuery query =
                entityManager.createNamedStoredProcedureQuery("Utilizator.creareCont");
        query.setParameter("nume", dto.getNume());
        query.setParameter("prenume", dto.getPrenume());
        query.setParameter("cnp", dto.getCnp());
        query.setParameter("username", dto.getUsername());
        query.setParameter("parola", dto.getParola());
        query.setParameter("rol", "CLIENT");
        query.execute();
    }

    @Transactional
    public void modificareDateAngajat(Long id, ModificareDateAngajatDto dto) {
        Utilizator utilizator = utilizatorRepository.findById(id).orElseThrow(() -> new RuntimeException("Utilizator inexistent"));
        if (utilizator.getRol() == Rol.CLIENT) {
            throw new RuntimeException("Operatie invalida");
        }
        utilizator.setNume(dto.getNume());
        utilizator.setPrenume(dto.getPrenume());
    }

    @Transactional(readOnly = true)
    public List<AngajatDto> vizualizareAngajati() {
        return utilizatorRepository.findAll().stream()
                .filter(u -> u.getRol() != Rol.CLIENT)
                .map(u -> {
                    AngajatDto angajatDto = new AngajatDto();
                    angajatDto.setId(u.getId());
                    angajatDto.setNume(u.getNume());
                    angajatDto.setPrenume(u.getPrenume());

                    Object result = entityManager.createNativeQuery(
                                    "SELECT decriptare_cnp(:cnp, :cheie, :iv) FROM dual")
                            .setParameter("cnp", u.getCnp())
                            .setParameter("cheie", u.getCheieCriptareCNP().getCheieCriptare())
                            .setParameter("iv", u.getCheieCriptareCNP().getVectorInitializare())
                            .getSingleResult();

                    angajatDto.setCnp(result != null ? result.toString() : "null");
                    angajatDto.setUsername(u.getUsername());
                    angajatDto.setRol(u.getRol());
                    return angajatDto;
                }).toList();
    }
}
