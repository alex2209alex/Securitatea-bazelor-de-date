package ro.unibuc.fmi.sbd.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.unibuc.fmi.sbd.dto.ContDto;
import ro.unibuc.fmi.sbd.service.ContService;

import java.util.List;

@RestController
public class ContController {
    private final ContService contService;

    public ContController(ContService contService) {
        this.contService = contService;
    }

    @PostMapping(value = "/conturi")
    @PreAuthorize("hasAnyAuthority('CLIENT')")
    public void creareCont() {
        contService.creareCont();
    }

    @GetMapping(value = "/conturi")
    @PreAuthorize("hasAnyAuthority('CLIENT', 'ANGAJAT', 'MANAGER')")
    public List<ContDto> vizualizareConturi() {
        return contService.vizualizareConturi();
    }
}
