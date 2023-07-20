package br.home.iovehicle.controller;

import br.home.iovehicle.DTOS.ServicoDTO;
import br.home.iovehicle.DTOS.ServicoFormDTO;
import br.home.iovehicle.colaborador.entities.Servico;
import br.home.iovehicle.services.ServicoService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/servicos")
public class ServicoController {

    private ModelMapper modelMapper;
    private ServicoService servicoService;

    public ServicoController(ModelMapper modelMapper, ServicoService servicoService) {
        this.modelMapper = modelMapper;
        this.servicoService = servicoService;
    }

    @PostMapping
    public ResponseEntity<ServicoDTO> create(@RequestBody ServicoFormDTO serv){
        try{
            Servico savedService = servicoService.create(serv);
            ServicoDTO servicoDTO = modelMapper.map(savedService, ServicoDTO.class);
            return ResponseEntity.ok(servicoDTO);
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ServicoDTO>> findAll(){
        List<Servico> servicos = servicoService.findAll();
        List<ServicoDTO> servicoDTO = servicos.stream().map(c -> modelMapper.map(c, ServicoDTO.class)).collect(Collectors.toList());
        return ResponseEntity.ok(servicoDTO);
    }

}
