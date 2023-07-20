package br.home.iovehicle.repositories;

import br.home.iovehicle.colaborador.entities.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Repository
public interface ColaboradorRepository extends JpaRepository <Colaborador, Long> {

    List<Colaborador> findByNomeCompleto(String nomeCompleto);
    List<Colaborador> findByFuncionarioAtivo(Boolean funcionarioAtivo);
}

