package br.home.iovehicle.DTOS;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServicoDTO {
    private Long id;

    private ColaboradorDTO colaborador;

    private VeiculoDTO veiculo;

    private LocalDateTime registroDeSaida;
}
