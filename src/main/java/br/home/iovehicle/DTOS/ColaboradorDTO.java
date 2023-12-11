package br.home.iovehicle.DTOS;

import br.home.iovehicle.colaborador.entities.CNH;
import br.home.iovehicle.colaborador.entities.Colaborador;
import br.home.iovehicle.colaborador.entities.Endereco;
import br.home.iovehicle.colaborador.entities.Servico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ColaboradorDTO {

    private Long id;
    private String nomeCompleto;

    private Boolean funcionarioAtivo;

    private String profissao;

    private List<ServicoDTO> colaborador;
}
