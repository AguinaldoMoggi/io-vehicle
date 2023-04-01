package br.home.iovehicle.colaborador.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CNH {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idColaborador;

    private String numeroRegistro;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    private Boolean transportePassageiros;

    private LocalDateTime dataValidade;


}
