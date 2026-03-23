package Algx.GerenciamentoDeCarros.Carros;

import org.springframework.stereotype.Component;

@Component
public class CarroMapper {

    public CarroModel map(CarroDTO carroDTO){

        CarroModel carroModel = new CarroModel();
        carroModel.setId(carroDTO.getId());
        carroModel.setMarca(carroDTO.getMarca());
        carroModel.setPlaca(carroDTO.getPlaca());
        carroModel.setModelo(carroDTO.getModelo());
        carroModel.setImgUrl(carroDTO.getImgUrl());

        return carroModel;
    }

    public CarroDTO map(CarroModel carroModel){

        CarroDTO carroDTO = new CarroDTO();
        carroDTO.setId(carroModel.getId());
        carroDTO.setModelo(carroModel.getModelo());
        carroDTO.setMarca(carroModel.getMarca());
        carroDTO.setPlaca(carroModel.getPlaca());
        carroDTO.setImgUrl(carroModel.getImgUrl());

        return carroDTO;
    }

    public void atualizarCarro(CarroDTO carroDTO, CarroModel carroModelExist){
        if(carroDTO.getMarca() != null){
            carroModelExist.setMarca(carroDTO.getMarca());
        }
        if(carroDTO.getModelo() != null){
            carroModelExist.setModelo(carroDTO.getModelo());
        }
        if(carroDTO.getPlaca() != null){
            carroModelExist.setPlaca(carroDTO.getPlaca());
        }
        if(carroDTO.getImgUrl()!= null){
            carroModelExist.setImgUrl(carroDTO.getImgUrl());
        }
    }
}
