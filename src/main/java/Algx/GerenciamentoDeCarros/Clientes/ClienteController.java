package Algx.GerenciamentoDeCarros.Clientes;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/criar")
    @Operation(summary = "Cria um novo cliente", description = "Rota cria um novo cliente e insere no banco de dados," +
            "OBS: É necéssario ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente criado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Erro na criação do cliente")
    })
    public ResponseEntity<ClienteDTO> criarCliente(
            @Parameter(description = "Usuario envia os dados do cliente a ser criado no corpo da requisição")
            @Valid @RequestBody ClienteDTO clienteDTO){
        ClienteDTO cliente = clienteService.criarCliente(clienteDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(cliente);
    }

    @GetMapping("/listar/all")
    @Operation(summary = "Lista todos os clientes", description = "Rota lista todos os clientes registrados no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Clientes encontrados com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Erro, não há clientes registrados no banco de dados")
    })
    public ResponseEntity<?> listarClientes(){
        List<ClienteDTO> clienteDTOS = clienteService.listarClientes();

        if(!clienteDTOS.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(clienteDTOS);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Não há Clientes registrados nos nossos registros");
    }

    @GetMapping("/listar/{id}")
    @Operation(summary = "Lista cliente pelo seu Id", description = "Rota lista um cliente pelo Id fornecido no patch")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Erro, Id do cliente não encontrado nos registros")
    })
    public ResponseEntity<?> listarClientePorId (
            @Parameter(description = "Usuario envia Id do cliente pelo patch da requisição")
            @PathVariable Long id){
        if(clienteService.listarClientePorId(id) != null){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(clienteService.listarClientePorId(id));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Cliente com id: " + id + " não encontrado nos nossos registros");
        }
    }

    @PatchMapping("/alterar/{id}")
    @Operation(summary = "Altera as informações do cliente pelo Id", description = "Rota altera um cliente pelo seu Id fornecido no patch")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente alterado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Erro, Id do cliente não encontrado nos registros")
    })
    public ResponseEntity<?> alterarClientePorId(
            @Parameter(description = "Usuario envia Id do cliente pelo patch da requisição")
            @PathVariable Long id,
            @Parameter(description = "Usuario envia os dados do cliente a ser atualizado no corpo da requisição")
            @RequestBody ClienteDTO clienteDTO){
        ClienteDTO cliente = clienteService.alterarClientePorId(id, clienteDTO);
        if(cliente != null){
            Map<String, Object> resposta = new LinkedHashMap<>();
            resposta.put("Mensagem", "Cliente " + cliente.getNome() + " com id " + id + " alterado com sucesso");
            resposta.put("Dados", cliente);

            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(resposta);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                    .body("Id " + id + " não encontrado nos nossos registros");
        }
    }

    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deleta o cliente pelo seu Id", description = "Rota deleta um cliente pelo Id fornecido no patch da requisição")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente deletado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Erro, Id do cliente não encontrado nos registros")
    })
    public ResponseEntity<String> deletarPorId(
            @Parameter(description = "Usuario envia Id do cliente pelo patch da requisição")
            @PathVariable Long id){
        if(clienteService.listarClientePorId(id) != null){
            ClienteDTO clienteDTO = clienteService.listarClientePorId(id);
            clienteService.deletarPorId(id);

            return ResponseEntity.status(HttpStatus.OK)
                    .body("Cliente " + clienteDTO.getNome() + " id: " + id + " deletado com sucesso!");
        }else{

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Id: " + id + " não encontrado nos nossos registros");
        }
    }
}
