package Algx.GerenciamentoDeCarros.Marca;

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
    public ResponseEntity<?> criarMarca(@RequestBody MarcaDTO marcaDTO){
        MarcaDTO marca = marcaService.criarMarca(marcaDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Marca " + marca.getNome() + " criada com sucesso!");
    }

    @GetMapping("/listar/all")
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
    public ResponseEntity<?> listarMarcaId(@PathVariable Long id){
        if(marcaService.listarMarcaId(id) != null){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(marcaService.listarMarcaId(id));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Marca com id: " + id + " não encontrado nos nossos registros");
        }
    }

    @PatchMapping("/alterar/{id}")
    public ResponseEntity<?> alterarMarcaPorId(@PathVariable Long id, @RequestBody MarcaDTO marcaDTO){
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
    public ResponseEntity<String> deletarId(@PathVariable Long id){
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
