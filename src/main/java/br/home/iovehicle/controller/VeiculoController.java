package br.home.iovehicle.controller;

import br.home.iovehicle.DTOS.VeiculoDTO;
import br.home.iovehicle.colaborador.entities.Colaborador;
import br.home.iovehicle.colaborador.entities.Veiculo;
import br.home.iovehicle.services.VeiculoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    private final VeiculoService veiculoService;

    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @PostMapping
    public ResponseEntity<Veiculo> create(@RequestBody Veiculo veiculo){
        return ResponseEntity.ok().body(veiculoService.create(veiculo));
    }

    @GetMapping
    public ResponseEntity<List<VeiculoDTO>> findAll(){
        return ResponseEntity.ok().body(veiculoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(veiculoService.findById(id));
    }


}
