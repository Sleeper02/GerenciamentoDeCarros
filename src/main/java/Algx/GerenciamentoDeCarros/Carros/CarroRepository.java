package Algx.GerenciamentoDeCarros.Carros;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarroRepository  extends JpaRepository<CarroModel, Long> {
    boolean existsByPlaca(String placa);
    List<CarroModel> findByClienteIsNull();
}
