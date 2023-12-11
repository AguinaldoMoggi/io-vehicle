package br.home.iovehicle.colaborador.entities;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
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
public class Colaborador implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeCompleto;

    private Boolean funcionarioAtivo = Boolean.TRUE;

    private String dataNascimento;

    private String cpf;

    private String rg;

    private String nis;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Endereco endereco;

    private Boolean pcd;

    private String profissao;

    @OneToOne(cascade = CascadeType.PERSIST)
    private CNH cnh;

    @OneToMany(mappedBy = "colaborador", cascade = {CascadeType.ALL})
    private List<Servico> servicos;

    private Boolean emRota;

    @Enumerated(EnumType.STRING)
    private DisponibilidadeColaborador disponibilidadeColaborador;

}

