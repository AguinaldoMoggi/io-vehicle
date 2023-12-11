package br.home.iovehicle.services;

import br.home.iovehicle.DTOS.VeiculoDTO;
import br.home.iovehicle.colaborador.entities.Servico;
import br.home.iovehicle.colaborador.entities.Veiculo;
import br.home.iovehicle.config.ModelMapperConfig;
import br.home.iovehicle.repositories.VeiculoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VeiculoService {
    private final VeiculoRepository veiculoRepository;
    private final ModelMapperConfig mapper;
    public Veiculo create(Veiculo veiculo){
        return veiculoRepository.save(veiculo);
    }

    public List<VeiculoDTO> findAll(){
        return veiculoRepository.findAll().stream().map(this::from).toList();
    }

    public Veiculo findById(Long id){
        return veiculoRepository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException("Veiculo não encontrado: " + id)
        );
    }

    public void addServico(Long idVeiculo, Servico servico){
        Veiculo veiculoFound = findById(idVeiculo);
        veiculoFound.getServicos().add(servico);
    }

    private VeiculoDTO from(Veiculo veiculo){
        return mapper.modelMapper().map(veiculo, VeiculoDTO.class);
    }
}
