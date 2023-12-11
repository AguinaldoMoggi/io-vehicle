package br.home.iovehicle.services;

import br.home.iovehicle.DTOS.ServicoDTO;
import br.home.iovehicle.DTOS.ServicoFormDTO;
import br.home.iovehicle.DTOS.ServicosPullDTO;
import br.home.iovehicle.colaborador.entities.*;
import br.home.iovehicle.config.ModelMapperConfig;
import br.home.iovehicle.repositories.ServicoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ServicoService {

    private final VeiculoService veiculoService;
    private final ColaboradorService colaboradorService;
    private final ServicoRepository servicoRepository;
    private final ModelMapperConfig modelMapper;

    @Transactional
    public Servico create(ServicoFormDTO dto){
        Colaborador colaboradorEncontrado = colaboradorService.findById(dto.getColaboradorId());
        Veiculo veiculoEncontrado = veiculoService.findById(dto.getVeiculoId());
        CNH cnhEncontrada = colaboradorEncontrado.getCnh();
        Instant instant = Instant.now();


        if(!(colaboradorEncontrado.getDisponibilidadeColaborador() == DisponibilidadeColaborador.DISPONIVEL && instant.getEpochSecond() < cnhEncontrada.getDataValidade().atTime(10, 30).atZone(ZoneId.of("America/Sao_Paulo")).toEpochSecond())){
            log.error("Colaborador não disl.lllllllllllllllllllllllllllllllllllllllllllllllponível.");

            throw new RuntimeException("Colaborador não disponível.");
        }

        if(!(veiculoEncontrado.getDisponibilidadeVeiculo() == DisponibilidadeVeiculo.DISPONIVEL)){
            log.error("Veículo não disponível.");
            throw new RuntimeException("Veículo não disponível.");
        }

        log.info("eira porra");
        Servico servico = new Servico();
        servico.setColaborador(colaboradorEncontrado);
        servico.setVeiculo(veiculoEncontrado);
        colaboradorEncontrado.setEmRota(Boolean.TRUE);
        servico.setSituacaoDeTroca(StatusDeTroca.NORMAL);
        colaboradorEncontrado.setDisponibilidadeColaborador(DisponibilidadeColaborador.EM_ROTA);
        veiculoEncontrado.setDisponibilidadeVeiculo(DisponibilidadeVeiculo.EM_ROTA);
        Servico servicoSaved = servicoRepository.save(servico);
        colaboradorService.addServico(dto.getColaboradorId(), servicoSaved);
        veiculoService.addServico(dto.getVeiculoId(), servicoSaved);
        return servicoSaved;
    }

    public List <Servico> findAll(){
        return servicoRepository.findAll();
    }


    public ServicoDTO retorno(ServicoFormDTO dto, Long id) {
        Colaborador colaboradorEncontrado = colaboradorService.findById(dto.getColaboradorId());
        Veiculo veiculoEncontrado = veiculoService.findById(dto.getVeiculoId());
        Servico servicoEncontrado = servicoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Não achei a porra do seu servico sua mula"));
        colaboradorEncontrado.setDisponibilidadeColaborador(DisponibilidadeColaborador.DISPONIVEL);
        veiculoEncontrado.setDisponibilidadeVeiculo(DisponibilidadeVeiculo.DISPONIVEL);
        LocalDateTime localDateTime = LocalDateTime.now();
        servicoEncontrado.setRegistroDeEntrada(localDateTime);
        servicoEncontrado.setConcluido(Boolean.TRUE);
        servicoRepository.save(servicoEncontrado);
        return modelMapper.modelMapper().map(servicoEncontrado, ServicoDTO.class);
    }

    public void trocaNormalEntreCarros(ServicosPullDTO dto){
        Servico servicoEncontrado1 = findById(dto.getServico1());
        Servico servicoEncontrado2 = findById(dto.getServico2());
        Veiculo carroEncontrado1 = servicoEncontrado1.getVeiculo();
        Veiculo carroEncontrado2 = servicoEncontrado2.getVeiculo();
        servicoEncontrado1.setVeiculo(carroEncontrado2);
        servicoEncontrado2.setVeiculo(carroEncontrado1);
        servicoRepository.save(servicoEncontrado1);
        servicoRepository.save(servicoEncontrado2);
    }

    private Servico findById(Long id){
        return servicoRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("não achei"));
    }
}
