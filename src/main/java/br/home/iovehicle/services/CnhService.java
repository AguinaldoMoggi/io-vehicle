package br.home.iovehicle.services;


import br.home.iovehicle.colaborador.entities.CNH;
import br.home.iovehicle.repositories.CnhRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CnhService {

    private CnhRepository cnhRepository;

    public CNH create(CNH cnh){

        return cnhRepository.save(cnh);
    }

    public List<CNH> findByCNH (String numeroRegistro){
        return cnhRepository.findAllBynumeroRegistro(numeroRegistro);
    }
}
