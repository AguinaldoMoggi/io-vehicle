package br.home.iovehicle.colaborador.entities;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CNH implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idColaborador;

    private String numeroRegistro;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    private Boolean transportePassageiros;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dataValidade;

}
