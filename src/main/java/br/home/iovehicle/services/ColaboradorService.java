package br.home.iovehicle.services;

import br.home.iovehicle.colaborador.entities.CNH;
import br.home.iovehicle.colaborador.entities.Colaborador;
import br.home.iovehicle.repositories.CnhRepository;
import br.home.iovehicle.repositories.ColaboradorRepository;
import br.home.iovehicle.repositories.CnhRepository;
import br.home.iovehicle.repositories.ColaboradorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class ColaboradorService {
    private final ColaboradorRepository colaboradorRepository;
    private final CnhRepository cnhRepository;

    public List<Colaborador> getAllColaborador(){
        return colaboradorRepository.findAll();
    }

    public Colaborador create(Colaborador colaborador1){
        if (colaborador1.getCnh() == null){
            return colaboradorRepository.save(colaborador1);
        }
        CNH cnh = colaborador1.getCnh();
        colaborador1.setCnh(null);
        Colaborador colaboradorSaved = colaboradorRepository.save(colaborador1);
        cnh.setIdColaborador(colaboradorSaved.getId());
        CNH cnhSaved = cnhRepository.save(cnh);
        colaboradorSaved.setCnh(cnhSaved);
        return colaboradorRepository.save(colaboradorSaved);
    }

    public Colaborador update(Long id, Colaborador colaborador) {
        Colaborador colaboradorEncontrado = colaboradorRepository.findById(id).get();
        colaboradorEncontrado.setNomeCompleto(colaborador.getNomeCompleto());
        colaboradorEncontrado.setEndereco(colaborador.getEndereco());
        colaboradorEncontrado.setProfissao(colaborador.getProfissao());
        return colaboradorRepository.save(colaboradorEncontrado);
    }

}
