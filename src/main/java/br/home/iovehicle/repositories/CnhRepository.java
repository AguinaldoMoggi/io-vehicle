package br.home.iovehicle.repositories;

import br.home.iovehicle.colaborador.entities.CNH;
import br.home.iovehicle.colaborador.entities.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CnhRepository extends JpaRepository<CNH, Long> {
    List<CNH> findAllBynumeroRegistro(String numeroRegistro);

    Optional<CNH> findByNumeroRegistro(String numeroRegistro);
}
