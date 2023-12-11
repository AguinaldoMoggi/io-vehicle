package br.home.iovehicle.services;


import br.home.iovehicle.colaborador.entities.CNH;
import br.home.iovehicle.repositories.CnhRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CnhService {

    private CnhRepository cnhRepository;

    public CNH create(CNH cnh){

        return cnhRepository.save(cnh);
    }

    public CNH findByNumeroRegistro(String numeroRegistro){
        return cnhRepository.findByNumeroRegistro(numeroRegistro).orElseThrow(()->
                new EntityNotFoundException("Não achei prora nenhuma de CNH com esse numerod e registro: "+numeroRegistro));
    }
}
