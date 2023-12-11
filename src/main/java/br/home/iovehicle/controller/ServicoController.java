package br.home.iovehicle.controller;

import br.home.iovehicle.DTOS.ServicoDTO;
import br.home.iovehicle.DTOS.ServicoFormDTO;
import br.home.iovehicle.DTOS.ServicosPullDTO;
import br.home.iovehicle.colaborador.entities.Servico;
import br.home.iovehicle.services.ServicoService;
import jakarta.servlet.annotation.HttpMethodConstraint;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/servicos")
@Slf4j
public class ServicoController {

    private final ModelMapper modelMapper;
    private final ServicoService servicoService;

    public ServicoController(ModelMapper modelMapper, ServicoService servicoService) {
        this.modelMapper = modelMapper;
        this.servicoService = servicoService;
    }

    @PostMapping
    public ResponseEntity<ServicoDTO> create(@RequestBody ServicoFormDTO dto){
        try{
            //Com o dtoForm foi enviado para o a serrvice create, com isso ela criou o serivoço retornando um
            //objetvo Servico, ai com esse objetivo eu informei no modelmapper para ele transformar em um DTO,
            //pois amsbos tem os atributos com os nomes iguais
            return ResponseEntity.ok(modelMapper.map(servicoService.create(dto), ServicoDTO.class));

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

    @GetMapping("/time")
    public ResponseEntity<String> jogao(){

        LocalDateTime localDateTime = LocalDateTime.now();
        Integer time = LocalDateTime.now().getSecond();
        Instant instant = Instant.now();


        log.error("{}",localDateTime.toString());
        log.error(time.toString());
        log.error(instant.toString());
        log.error("{}",instant.getEpochSecond());

        return ResponseEntity.ok("55");
    }
    @PutMapping("/{id}")
    public ResponseEntity<ServicoDTO>update(@PathVariable Long id, @RequestBody ServicoFormDTO servicoDTO){
        return ResponseEntity.ok(servicoService.retorno(servicoDTO, id));
    }

    @PutMapping("/troca-carro")
    public ResponseEntity trocaCarro(@RequestBody ServicosPullDTO servicosPullDTO){
        servicoService.trocaNormalEntreCarros(servicosPullDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
