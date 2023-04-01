package br.home.iovehicle.controller;

import br.home.iovehicle.colaborador.entities.Colaborador;
import br.home.iovehicle.services.ColaboradorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/colaboradores")
public class ColaboradorController {
    private final ColaboradorService colaboradorService;

    public ColaboradorController(ColaboradorService colaboradorService) {
        this.colaboradorService = colaboradorService;
    }
    @PostMapping
    public ResponseEntity<Colaborador> create(@RequestBody Colaborador colaborador){
        return ResponseEntity.ok().body(colaboradorService.create(colaborador));
    }
    @GetMapping
    public ResponseEntity<List<Colaborador>> getAllColaboradores(){
        return ResponseEntity.ok().body(colaboradorService.getAllColaborador());

    }

}
