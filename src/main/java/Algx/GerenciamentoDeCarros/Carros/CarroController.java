package Algx.GerenciamentoDeCarros.Carros;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Cria um novo carro", description = "Rota cria um novo carro e insere no banco dados, " +
            "OBS: É necessario que exista uma marca antes para a criação do carro!")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Carro criado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Erro na criação do carro")
    })
    public ResponseEntity<String> criarCarro(
            @Parameter(description = "Usuario envia os dados do carro a ser criado no corpo da requisição")
            @Valid @RequestBody CarroDTO carroDTO){
        CarroDTO carro = carroService.criarCarro(carroDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Carro " + carro.getMarca() + " " + carro.getModelo() + " criado com sucesso!");
    }

    @GetMapping("/listar/all")
    @Operation(summary = "Lista todos os carros", description = "Rota lista todos os carros registrados no banco de dados" +
            ", junto a suas informações")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carros encontrados com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Erro, não há carros registrados no banco de dados")
    })
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
    @Operation(summary = "Lista o carro pelo seu Id", description = "Rota lista um carro pelo Id fornecido no patch da requisição")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carro encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Erro, Id do carro não encontrado nos registros")
    })
    public ResponseEntity<?> listarCarroPorId(
            @Parameter(description = "Usuario envia Id do carro pelo patch da requisição")
            @PathVariable Long id){
        if(carroService.listarCarroId(id) != null){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(carroService.listarCarroId(id));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Carro com id: " + id + " não encontrado nos nossos registros");
        }
    }

    @PatchMapping("/alterar/{id}")
    @Operation(summary = "Altera as informações do carro pelo Id", description = "Rota altera um carro pelo seu Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carro alterado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Erro, Id do carro não encontrado nos registros")
    })
    public ResponseEntity<?> alterarCarroPorId(
            @Parameter(description = "Usuario envia Id pelo patch da requisição")
            @PathVariable long id,
            @Parameter(description = "Usuario envia os dados do carro a ser atualizado no corpo da requisição")
            @RequestBody CarroDTO carroDTO){
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
    @Operation(summary = "Deleta o carro pelo seu Id", description = "Rota deleta um carro pelo seu Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carro deletado com Sucesso!"),
            @ApiResponse(responseCode = "404", description = "Erro, Id do carro não encontrado nos registros")
    })
    public ResponseEntity<String> deletarCarroId(
            @Parameter(description = "Usuario envia o Id pelo patch da requisição")
            @PathVariable Long id){
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
