package Algx.GerenciamentoDeCarros.Carros;

import Algx.GerenciamentoDeCarros.Marca.MarcaModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class CarroDTO {

    private Long id;
    private String modelo;
    private String placa;
    private MarcaModel marca;
    private String imgUrl;

}
