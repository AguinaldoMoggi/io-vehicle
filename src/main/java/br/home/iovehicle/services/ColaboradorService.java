package br.home.iovehicle.services;

import br.home.iovehicle.colaborador.entities.Colaborador;
import br.home.iovehicle.repositories.CnhRepository;
import br.home.iovehicle.repositories.ColaboradorRepository;
import br.home.iovehicle.repositories.CnhRepository;
import br.home.iovehicle.repositories.ColaboradorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ColaboradorService {
    private final ColaboradorRepository colaboradorRepository;
    private final CnhRepository cnhRepository;

    public List<Colaborador> getAllColaborador(){
        return colaboradorRepository.findAll();
    }

    public Colaborador create(Colaborador colaborador){

        return colaboradorRepository.save(colaborador);
    }
}
