package br.home.iovehicle.services;

import br.home.iovehicle.DTOS.ServicoFormDTO;
import br.home.iovehicle.colaborador.entities.Colaborador;
import br.home.iovehicle.colaborador.entities.Servico;
import br.home.iovehicle.colaborador.entities.Veiculo;
import br.home.iovehicle.repositories.ServicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicoService {
    private VeiculoService veiculoService;

    private ColaboradorService colaboradorService;

    private ServicoRepository servicoRepository;

    public ServicoService(VeiculoService veiculoService, ColaboradorService colaboradorService, ServicoRepository servicoRepository) {
        this.veiculoService = veiculoService;
        this.colaboradorService = colaboradorService;
        this.servicoRepository = servicoRepository;
    }

    public Servico create(ServicoFormDTO servicoFormDTO){
        Colaborador colaboradorEncontrado = colaboradorService.findById(servicoFormDTO.getColaborador());
        Veiculo veiculoEncontrado = veiculoService.findById(servicoFormDTO.getVeiculo());

        Servico servico = new Servico();

        servico.setColaborador(colaboradorEncontrado);
        servico.setVeiculo(veiculoEncontrado);

        colaboradorEncontrado.getServicos().add(servico);
        servico.setVeiculo(veiculoEncontrado);

        veiculoEncontrado.getServicos().add(servico);
        servico.setColaborador(colaboradorEncontrado);

        return servicoRepository.save(servico);
    }

    public List <Servico> findAll(){
        return servicoRepository.findAll();
    }
}
