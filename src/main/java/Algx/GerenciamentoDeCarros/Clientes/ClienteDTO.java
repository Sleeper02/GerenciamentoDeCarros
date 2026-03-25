package Algx.GerenciamentoDeCarros.Clientes;

import Algx.GerenciamentoDeCarros.Carros.CarroModel;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClienteDTO {

    private Long id;
    private String nome;

    @NotBlank(message = "O CPF é obrigatório")
    @CPF(message = "O CPF digitado é inválido") // O Spring já tem um validador pronto
    private String CPF;

    private String email;
    private Integer telefone;
    private String endereco;
    private CarroModel carro;

}
