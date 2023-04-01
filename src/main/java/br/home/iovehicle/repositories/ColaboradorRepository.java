package br.home.iovehicle.repositories;

import br.home.iovehicle.colaborador.entities.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColaboradorRepository extends JpaRepository <Colaborador, Long> {

}

