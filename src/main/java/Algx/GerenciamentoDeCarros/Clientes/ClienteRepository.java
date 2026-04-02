package Algx.GerenciamentoDeCarros.Clientes;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {
    boolean existsByCPF(String cpf);
    boolean existsByEmail(String email);
}
