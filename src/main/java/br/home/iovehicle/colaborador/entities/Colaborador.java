package br.home.iovehicle.colaborador.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Colaborador implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeCompleto;

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
}
