package Algx.GerenciamentoDeCarros.Marca;

import Algx.GerenciamentoDeCarros.Carros.CarroModel;
import org.springframework.stereotype.Component;

@Component
public class MarcaMapper {
    public MarcaModel map(MarcaDTO marcaDTO){
        MarcaModel marcaModel = new MarcaModel();
        marcaModel.setId(marcaDTO.getId());
        marcaModel.setNome(marcaDTO.getNome());
        marcaModel.setCarros(marcaDTO.getCarros());

        return marcaModel;
    }

    public MarcaDTO map(MarcaModel marcaModel){
        MarcaDTO marcaDTO = new MarcaDTO();
        marcaDTO.setId(marcaModel.getId());
        marcaDTO.setNome(marcaModel.getNome());
        marcaDTO.setCarros(marcaModel.getCarros());

        return marcaDTO;
    }

    public void atualizarMarca (MarcaDTO marcaDTO, MarcaModel marcaModelExit){
        if(marcaDTO.getNome()!=null){
            marcaModelExit.setNome(marcaDTO.getNome());
        }
        if(marcaDTO.getCarros() != null){
            marcaModelExit.setCarros(marcaDTO.getCarros());

            for(CarroModel carro : marcaModelExit.getCarros()){
                carro.setMarca(marcaModelExit);
            }
        }
    }
}
