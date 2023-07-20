package br.home.iovehicle.controller;

import br.home.iovehicle.colaborador.entities.CNH;
import br.home.iovehicle.services.CnhService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/cnh")
@Slf4j
public class CnhController {

    private final CnhService cnhService;

    public CnhController(CnhService cnhService) {
        this.cnhService = cnhService;
    }

    @PostMapping
    public ResponseEntity<CNH> create(@RequestBody CNH cnh){
        return ResponseEntity.ok().body(cnhService.create(cnh));
    }

    @GetMapping("/numerocnh/{numeroRegistro}")
    public ResponseEntity<List<CNH>> findByCNH(@PathVariable String numeroRegistro){
        try {
            List<CNH> cnhs = cnhService.findByCNH(numeroRegistro);
            return ResponseEntity.ok(cnhs);
        }catch (Exception e){
            log.error("numero de cnh não encontrado");
        }
        return ResponseEntity.notFound().build();
    }
}
