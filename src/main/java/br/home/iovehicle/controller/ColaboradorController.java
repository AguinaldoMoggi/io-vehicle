package br.home.iovehicle.controller;

import br.home.iovehicle.DTOS.ColaboradorDTO;
import br.home.iovehicle.colaborador.entities.Colaborador;
import br.home.iovehicle.services.ColaboradorService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/colaboradores")
@Slf4j
public class ColaboradorController {
    private final ColaboradorService colaboradorService;
    
    private ModelMapper modelMapper;

    public ColaboradorController(ColaboradorService colaboradorService, ModelMapper modelMapper) {
        this.colaboradorService = colaboradorService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<Colaborador> create(@RequestBody Colaborador colaborador){
        return ResponseEntity.ok().body(colaboradorService.create(colaborador));
    }
    @GetMapping
    public ResponseEntity<List<ColaboradorDTO>> getAllColaboradores(){
        List<Colaborador> colaboradors = colaboradorService.findAll();
        List<ColaboradorDTO> colaboradorDTOS = colaboradors.stream().map(c -> modelMapper.map(c, ColaboradorDTO.class)).collect(Collectors.toList());
        
        
        return ResponseEntity.ok(colaboradorDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Colaborador> getById(@PathVariable Long id){
        return ResponseEntity.ok().body(colaboradorService.findById(id));
    }

    @GetMapping("/nome/{nomeCompleto}")
    public ResponseEntity<List<Colaborador>> findByNomeCompleto(@PathVariable String nomeCompleto){
        try {
            List<Colaborador> colaboradors = colaboradorService.findByNomeCompleto(nomeCompleto);
            return ResponseEntity.ok(colaboradors);
        } catch (Exception e){
            log.error("Nome não encontrado!");
        }
        return ResponseEntity.notFound().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Colaborador> update(@PathVariable Long id, @RequestBody Colaborador colaborador){
        return  ResponseEntity.ok().body(colaboradorService.update(id, colaborador));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Colaborador> delete(@PathVariable Colaborador id){
        return ResponseEntity.ok().body(colaboradorService.delete(id));
    }

}
