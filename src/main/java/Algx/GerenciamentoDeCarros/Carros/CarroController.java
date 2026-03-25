package Algx.GerenciamentoDeCarros.Carros;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/carro")
public class CarroController {
    private final CarroService carroService;

    public CarroController(CarroService carroService) {
        this.carroService = carroService;
    }

    @PostMapping("/criar")
    public ResponseEntity<String> criarCarro(@Valid @RequestBody CarroDTO carroDTO){
        CarroDTO carro = carroService.criarCarro(carroDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Carro " + carro.getMarca() + " " + carro.getModelo() + " criado com sucesso!");
    }

    @GetMapping("/listar/all")
    public ResponseEntity<?> listarCarros(){
        List<CarroDTO> carroDTOS = carroService.listarCarros();

        if(!carroDTOS.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(carroDTOS);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Não carroas registrados nos nossos registros");
        }
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarCarroPorId(@PathVariable Long id){
        if(carroService.listarCarroId(id) != null){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(carroService.listarCarroId(id));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Carro com id: " + id + " não encontrado nos nossos registros");
        }
    }

    @PatchMapping("/alterar/{id}")
    public ResponseEntity<?> alterarCarroPorId(@PathVariable long id, @RequestBody CarroDTO carroDTO){
        CarroDTO carro = carroService.alterarCarroPorId(id, carroDTO);
        if(carro != null){
            Map<String, Object> resposta = new LinkedHashMap<>();
            resposta.put("Message", "Id: " + carro.getId() + " alterado com sucesso!");
            resposta.put("Dado", carro);

            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(resposta);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Id: " + id + " não encontrado nos nossos registros");
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarCarroId(@PathVariable Long id){
        if(carroService.listarCarroId(id) != null){
            CarroDTO carroDTO = carroService.listarCarroId(id);
            carroService.deletarCarroId(id);

            return ResponseEntity.status(HttpStatus.OK)
                    .body("Carro" + carroDTO.getMarca() + " " + carroDTO.getModelo() + " " + carroDTO.getPlaca() + " deletado com sucesso!");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Id: " + id + " não encontrado nos nossos registros");
        }
    }

}
