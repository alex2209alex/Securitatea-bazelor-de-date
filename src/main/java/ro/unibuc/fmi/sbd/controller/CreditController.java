package ro.unibuc.fmi.sbd.controller;

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
    public void creareCerereCredit(@RequestBody CreareCreditDto creareCreditDto) {
        creditService.creareCerereCredit(creareCreditDto);
    }

    @PutMapping("/credite/{id}/aprobare")
    public void aprobareCredit(@PathVariable("id") Long id) {
        creditService.aprobareCredit(id);
    }

    @PutMapping("/credite/{id}/respingere")
    public void respingereCredit(@PathVariable("id") Long id) {
        creditService.respingereCredit(id);
    }

    @GetMapping("/credite")
    public List<CreditDto> vizualizareCredite() {
        return creditService.vizualizareCredite();
    }
}
