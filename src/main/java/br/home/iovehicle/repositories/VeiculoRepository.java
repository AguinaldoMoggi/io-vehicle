package br.home.iovehicle.repositories;

import br.home.iovehicle.colaborador.entities.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeiculoRepository extends JpaRepository <Veiculo, Long> {

}
