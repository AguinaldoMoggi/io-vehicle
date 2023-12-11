package br.home.iovehicle.colaborador.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "servico")
public class Servico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_colaborador")
    private Colaborador colaborador;

    @ManyToOne
    @JoinColumn(name = "id_veiculo")
    private Veiculo veiculo;

    private LocalDateTime registroDeSaida;

    private LocalDateTime registroDeEntrada;

    private Boolean concluido = Boolean.FALSE;

    private Boolean emRota = Boolean.FALSE;

    private StatusDeTroca situacaoDeTroca;


    @PrePersist
    public void create(){
        registroDeSaida = LocalDateTime.now();
    }
}

