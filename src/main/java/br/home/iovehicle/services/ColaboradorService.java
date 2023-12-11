package br.home.iovehicle.services;

import br.home.iovehicle.colaborador.entities.CNH;
import br.home.iovehicle.colaborador.entities.Colaborador;
import br.home.iovehicle.colaborador.entities.Servico;
import br.home.iovehicle.repositories.CnhRepository;
import br.home.iovehicle.repositories.ColaboradorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class ColaboradorService {
    private final ColaboradorRepository colaboradorRepository;
    private final CnhRepository cnhRepository;

    public List<Colaborador> findAll(){
        Colaborador colaborador = new Colaborador();
        if (colaborador.getFuncionarioAtivo()){
            return colaboradorRepository.findByFuncionarioAtivo(Boolean.TRUE);
        }
        return null;
    }

    public Colaborador findById(Long id){
        return colaboradorRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Colaborador Não Encontrado com o id: " + id));
    }
    public Colaborador create(Colaborador colaborador1){
        log.info(colaborador1);
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

    public void addServico(Long idColaborador, Servico servico){
        Colaborador colaboradorFound = findById(idColaborador);

        colaboradorFound.getServicos().add(servico);
        colaboradorRepository.save(colaboradorFound);
    }
    public Colaborador update(Long id, Colaborador colaborador) {
        Colaborador colaboradorEncontrado = colaboradorRepository.findById(id).get();
        colaboradorEncontrado.setNomeCompleto(colaborador.getNomeCompleto());
        colaboradorEncontrado.setEndereco(colaborador.getEndereco());
        colaboradorEncontrado.setProfissao(colaborador.getProfissao());
        return colaboradorRepository.save(colaboradorEncontrado);
    }

    public Colaborador delete(Colaborador colaborador){
        Optional<Colaborador> colaboradorOptional = colaboradorRepository.findById((colaborador).getId());
        if (colaboradorOptional.isPresent()){
            Colaborador colaborador1 = colaboradorOptional.get();
            colaboradorRepository.delete(colaborador1);
            cnhRepository.delete(colaborador1.getCnh());
            return colaborador1;
        }
        return null;
    }

    public List<Colaborador> findByNomeCompleto(String nomeCompleto){
        return colaboradorRepository.findByNomeCompleto(nomeCompleto);
    }
}
