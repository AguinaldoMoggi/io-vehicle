package br.home.iovehicle.controller;

import br.home.iovehicle.colaborador.entities.CNH;
import br.home.iovehicle.services.CnhService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cnh")
public class CnhController {

    private final CnhService cnhService;

    public CnhController(CnhService cnhService) {
        this.cnhService = cnhService;
    }

    @PostMapping
    public ResponseEntity<CNH> create(@RequestBody CNH cnh){
        return ResponseEntity.ok().body(cnhService.create(cnh));
    }
}
