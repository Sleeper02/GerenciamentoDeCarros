package Algx.GerenciamentoDeCarros.Clientes;

import Algx.GerenciamentoDeCarros.Carros.CarroModel;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CPF;
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

    @NotBlank(message = "O email é obrigatório")
    private String email;

    @NotBlank(message = "O Telefone é obrigatório")
    @Pattern(regexp = "^\\d{10,11}$", message = "O telefone deve conter apenas números e ter entre 10 e 11 dígitos (com DDD)")
    private String telefone;
    private String endereco;
    private CarroModel carro;

}
