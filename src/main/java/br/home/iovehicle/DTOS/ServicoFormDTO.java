package br.home.iovehicle.DTOS;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServicoFormDTO {

    private Long colaborador;

    private Long veiculo;
}
