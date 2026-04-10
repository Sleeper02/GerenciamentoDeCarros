package Algx.GerenciamentoDeCarros.Marca;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/marca")
public class MarcaController {
    private final MarcaService marcaService;

    public MarcaController(MarcaService marcaService) {
        this.marcaService = marcaService;
    }

    @PostMapping("/criar")
    @Operation(summary = "Cria uma nova marca", description = "Rota cria uma nova marca e insere no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Marca criada com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Erro na criação da marca")
    })
    public ResponseEntity<?> criarMarca(
            @Parameter(description = "Usuario envia os dados da marca a ser criado no corpo da requisição")
            @RequestBody MarcaDTO marcaDTO){
        MarcaDTO marca = marcaService.criarMarca(marcaDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Marca " + marca.getNome() + " criada com sucesso!");
    }

    @GetMapping("/listar/all")
    @Operation(summary = "Lista todas as marcas", description = "Rota lista todas as marcas registradas no banco de dados" +
            ", junto a suas informações")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Marcas encontradas com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Erro, não há marcas registradas no banco de dados")
    })
    public ResponseEntity<?> listarMarca(){
        List<MarcaDTO> marcaDTOS = marcaService.listarMarcas();

        if(!marcaDTOS.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(marcaDTOS);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Não há marcas registradas nos nossos registros");
        }
    }

    @GetMapping("listar/{id}")
    @Operation(summary = "Lista a marca pelo seu Id", description = "Rota lista uma marca pelo Id fornecido no patch da requisição")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Marca encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Erro, Id da marca não encontrada nos registros")
    })
    public ResponseEntity<?> listarMarcaId(
            @Parameter(description = "Usuario envia Id da marca pelo patch da requisição")
            @PathVariable Long id){
        if(marcaService.listarMarcaId(id) != null){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(marcaService.listarMarcaId(id));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Marca com id: " + id + " não encontrado nos nossos registros");
        }
    }

    @PatchMapping("/alterar/{id}")
    @Operation(summary = "Altera as informações da marca pelo Id", description = "Rota altera uma marca pelo seu Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Marca alterada com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Erro, Id da marca não encontrada nos registros")
    })
    public ResponseEntity<?> alterarMarcaPorId(
            @Parameter(description = "Usuario envia Id da marca pelo patch da requisição")
            @PathVariable Long id,
            @Parameter(description = "Usuario envia os dados da marca a ser atualizada no corpo da requisição")
            @RequestBody MarcaDTO marcaDTO){
        MarcaDTO marca = marcaService.alterarMarcaPorId(id, marcaDTO);
        if(marca != null){
            Map<String, Object> resposta = new LinkedHashMap<>();
            resposta.put("Mensagem", "Marca: " + marca.getNome() + " " + id + " alterada com sucesso!");
            resposta.put("Dados", marca);

            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(resposta);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                    .body("Id " + id + " não encontrado nos nossos registros");
        }
    }

    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deleta  a marca pelo seu Id", description = "Rota deleta uma marca pelo seu Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Marca deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Erro, Id da marca não encontrada nos registros")
    })
    public ResponseEntity<String> deletarId(
            @Parameter(description = "Usuario envia Id da marca pelo patch da requisição")
            @PathVariable Long id){
        if(marcaService.listarMarcaId(id) != null){
            MarcaDTO marcaDTO = marcaService.listarMarcaId(id);
            marcaService.deletarId(id);

            return ResponseEntity.status(HttpStatus.OK)
                    .body("Marca " + marcaDTO.getNome() + " deletada com sucesso!");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Id: " + id + " não encontrado nos nossos registros");
        }
    }
}
