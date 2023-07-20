package br.home.iovehicle.services;

import br.home.iovehicle.colaborador.entities.Veiculo;
import br.home.iovehicle.repositories.VeiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VeiculoService {
    private final VeiculoRepository veiculoRepository;

    public Veiculo create(Veiculo veiculo){
        return veiculoRepository.save(veiculo);
    }

    public List<Veiculo> findAll(){
        return veiculoRepository.findAll();
    }

    public Veiculo findById(Long id){
        return veiculoRepository.findById(id).get();
    }

}
