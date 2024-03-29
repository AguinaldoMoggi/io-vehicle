package br.home.iovehicle.DTOS;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServicoFormDTO {

    @NonNull
    private Long colaboradorId;

    @NonNull
    private Long veiculoId;

    private Boolean emRota;
}
