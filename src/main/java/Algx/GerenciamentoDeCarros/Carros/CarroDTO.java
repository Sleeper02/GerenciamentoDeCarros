package Algx.GerenciamentoDeCarros.Carros;

import Algx.GerenciamentoDeCarros.Marca.MarcaModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class CarroDTO {

    private Long id;
    private String modelo;
    @NotBlank(message = "A placa não pode ser vazia")
    @Size(min = 7, max = 7, message = "A placa deve ter exatamente 7 caracteres")
    private String placa;
    private MarcaModel marca;
    private String imgUrl;

}
