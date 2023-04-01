package br.home.iovehicle.services;


import br.home.iovehicle.colaborador.entities.CNH;
import br.home.iovehicle.repositories.CnhRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CnhService {

    private final CnhRepository cnhRepository;

    public CNH create(CNH cnh){

        return cnhRepository.save(cnh);
    }
}
