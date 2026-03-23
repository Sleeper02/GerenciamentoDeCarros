package Algx.GerenciamentoDeCarros.Marca;

import Algx.GerenciamentoDeCarros.Carros.CarroModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MarcaDTO {

    private Long id;
    private String nome;
    private List<CarroModel> carros;

}
