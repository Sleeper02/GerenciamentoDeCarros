package Algx.GerenciamentoDeCarros.Marca;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MarcaRepository extends JpaRepository<MarcaModel, Long> {
    boolean existsByNomeIgnoreCase(String nome);
}
