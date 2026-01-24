package ro.unibuc.fmi.sbd.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ro.unibuc.fmi.sbd.dto.CreareCreditDto;
import ro.unibuc.fmi.sbd.dto.CreditDto;
import ro.unibuc.fmi.sbd.service.CreditService;

import java.util.List;

@RestController
public class CreditController {
    private final CreditService creditService;

    public CreditController(CreditService creditService) {
        this.creditService = creditService;
    }

    @PostMapping("/credite")
    @PreAuthorize("hasAnyAuthority('CLIENT')")
    public void creareCerereCredit(@RequestBody CreareCreditDto creareCreditDto) {
        creditService.creareCerereCredit(creareCreditDto);
    }

    @PutMapping("/credite/{id}/aprobare")
    @PreAuthorize("hasAnyAuthority('ANGAJAT', 'MANAGER')")
    public void aprobareCredit(@PathVariable("id") Long id) {
        creditService.aprobareCredit(id);
    }

    @PutMapping("/credite/{id}/respingere")
    @PreAuthorize("hasAnyAuthority('ANGAJAT', 'MANAGER')")
    public void respingereCredit(@PathVariable("id") Long id) {
        creditService.respingereCredit(id);
    }

    @GetMapping("/credite")
    @PreAuthorize("hasAnyAuthority('CLIENT', 'ANGAJAT', 'MANAGER')")
    public List<CreditDto> vizualizareCredite() {
        return creditService.vizualizareCredite();
    }
}
