package br.home.iovehicle.controller;

import br.home.iovehicle.colaborador.entities.CNH;
import br.home.iovehicle.services.CnhService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cnh")
@Slf4j
@RequiredArgsConstructor
public class CnhController {

    private final CnhService cnhService;

    @PostMapping
    public ResponseEntity<CNH> create(@RequestBody CNH cnh){
        return ResponseEntity.ok().body(cnhService.create(cnh));
    }

    @GetMapping("/numero-cnh/{numeroRegistro}")
    public ResponseEntity<CNH> findByCNH(@PathVariable String numeroRegistro){
        try {
            CNH cnh = cnhService.findByNumeroRegistro(numeroRegistro);
            return ResponseEntity.ok(cnh);
        }catch (RuntimeException e){
            log.error("numero de cnh não encontrado");
        }
        return ResponseEntity.notFound().build();
    }
}
