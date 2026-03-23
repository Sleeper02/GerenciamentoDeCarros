package Algx.GerenciamentoDeCarros.Carros;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CarroRepository  extends JpaRepository<CarroModel, Long> {
}
