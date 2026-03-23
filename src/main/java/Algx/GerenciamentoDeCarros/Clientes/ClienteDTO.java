package Algx.GerenciamentoDeCarros.Clientes;

import Algx.GerenciamentoDeCarros.Carros.CarroModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClienteDTO {

    private Long id;
    private String nome;
    private String CPF;
    private String email;
    private Integer telefone;
    private String endereco;
    private CarroModel carro;

}
