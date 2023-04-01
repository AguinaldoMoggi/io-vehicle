package br.home.iovehicle.repositories;

import br.home.iovehicle.colaborador.entities.CNH;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CnhRepository extends JpaRepository<CNH, Long> {

}
