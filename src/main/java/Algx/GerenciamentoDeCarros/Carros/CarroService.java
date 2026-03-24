package Algx.GerenciamentoDeCarros.Carros;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarroService {
    private  CarroRepository carroRepository;
    private  CarroMapper carroMapper;

    public CarroService(CarroRepository carroRepository, CarroMapper carroMapper) {
        this.carroRepository = carroRepository;
        this.carroMapper = carroMapper;
    }

    public CarroDTO criarCarro(CarroDTO carroDTO){
        CarroModel carroModel = carroMapper.map(carroDTO);
        carroModel = carroRepository.save(carroModel);

        return carroMapper.map(carroModel);
    }

    public List<CarroDTO> listarCarros(){
        List<CarroModel> carroModels = carroRepository.findAll();

        return carroModels.stream()
                .map(carroMapper :: map)
                .collect(Collectors.toList());
    }

    public CarroDTO listarCarroId(Long id){
        Optional<CarroModel> carroModel = carroRepository.findById(id);
        return carroModel.map(carroMapper :: map).orElse(null);
    }

    public void deletarCarroId(Long id){
        if(carroRepository.existsById(id)){
            carroRepository.deleteById(id);
        }else{
            return;
        }
    }

    public CarroDTO alterarCarroPorId(Long id, CarroDTO carroDTO){
        Optional<CarroModel> carroModel = carroRepository.findById(id);
        if(carroModel.isPresent()){
            CarroModel carro = carroModel.get();
            carroMapper.atualizarCarro(carroDTO, carro);
            carro = carroRepository.save(carro);
            return carroMapper.map(carro);
        }
        return null;
    }
}
