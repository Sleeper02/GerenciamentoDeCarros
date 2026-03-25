package Algx.GerenciamentoDeCarros.Clientes;

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
    public ResponseEntity<ClienteDTO> criarCliente(@Valid @RequestBody ClienteDTO clienteDTO){
        ClienteDTO cliente = clienteService.criarCliente(clienteDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(cliente);
    }

    @GetMapping("/listar/all")
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
    public ResponseEntity<?> listarClientePorId (@PathVariable Long id){
        if(clienteService.listarClientePorId(id) != null){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(clienteService.listarClientePorId(id));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Cliente com id: " + id + " não encontrado nos nossos registros");
        }
    }

    @PatchMapping("/alterar/{id}")
    public ResponseEntity<?> alterarClientePorId(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO){
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
    public ResponseEntity<String> deletarPorId(@PathVariable Long id){
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
