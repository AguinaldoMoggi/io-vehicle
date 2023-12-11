package br.home.iovehicle.colaborador.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Veiculo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String carName;

    private String modelo;

    private String carPlaca;

    private String renavam;

    @OneToMany(mappedBy = "veiculo", cascade = {CascadeType.ALL})
    private List<Servico> servicos;

    private DisponibilidadeVeiculo disponibilidadeVeiculo;

}
