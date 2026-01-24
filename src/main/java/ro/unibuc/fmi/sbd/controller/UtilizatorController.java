package ro.unibuc.fmi.sbd.controller;

import jakarta.annotation.security.PermitAll;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ro.unibuc.fmi.sbd.dto.AngajatDto;
import ro.unibuc.fmi.sbd.dto.CreareClientDto;
import ro.unibuc.fmi.sbd.dto.ModificareDateAngajatDto;
import ro.unibuc.fmi.sbd.service.UtilizatorService;

import java.util.List;

@RestController
public class UtilizatorController {
    private final UtilizatorService utilizatorService;

    public UtilizatorController(UtilizatorService utilizatorService) {
        this.utilizatorService = utilizatorService;
    }

    @PostMapping(value = "/clienti")
    public void creareClient(@RequestBody CreareClientDto dto) {
        utilizatorService.creareClient(dto);
    }

    @PutMapping(value = "/utilizatori/{id}")
    @PreAuthorize("hasAnyAuthority('ANGAJAT_HR')")
    public void modificareDateAngajat(@PathVariable("id") Long id, @RequestBody ModificareDateAngajatDto dto) {
        utilizatorService.modificareDateAngajat(id, dto);
    }

    @GetMapping(value = "/utilizatori")
    @PreAuthorize("hasAnyAuthority('ANGAJAT_HR')")
    public List<AngajatDto> vizualizareAngajati() {
        return utilizatorService.vizualizareAngajati();
    }
}
