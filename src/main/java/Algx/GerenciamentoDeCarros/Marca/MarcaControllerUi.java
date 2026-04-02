package Algx.GerenciamentoDeCarros.Marca;

import Algx.GerenciamentoDeCarros.RegraNegocioException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/marca/ui")
public class MarcaControllerUi {

    private final MarcaService marcaService;

    public MarcaControllerUi(MarcaService marcaService) {
        this.marcaService = marcaService;
    }

    @GetMapping("/criar")
    public String formsAdicionarMarca(Model model){
        model.addAttribute("marca", new MarcaDTO());
        return "adicionarMarca";
    }

    @PostMapping("/salvar")
    public String salvarMarca(@Valid @ModelAttribute("marca") MarcaDTO marcaDTO, BindingResult result, RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            return "adicionarMarca";
        }try{
            marcaService.criarMarca(marcaDTO);
            redirectAttributes.addFlashAttribute("Mensagem", "Marca cadastrada com sucesso!");
            return "redirect:/marca/ui/listar/all";
        }catch (RegraNegocioException e){
            result.rejectValue(e.getCampo(), "error.marca", e.getMessage());
            return "adicionarMarca";
        }

    }

    @GetMapping("/listar/all")
    public Object listarMarca(Model model){
        List<MarcaDTO> marcaDTO = marcaService.listarMarcas();
        if(!marcaDTO.isEmpty()){
            model.addAttribute("marca", marcaDTO);
            return "listarMarca";
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Não há marcas registradas");
        }
    }


    @GetMapping("/alterar/{id}")
    public String formsAlterarMarca(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes){
        MarcaDTO marcaDTO = marcaService.listarMarcaId(id);
        if(marcaDTO != null){
            model.addAttribute("marca", marcaDTO);
            return "alterarMarca";
        }else{
            redirectAttributes.addFlashAttribute("Mensagem", "Marca não encontrada nos nossos registros");
            return "redirect:/marca/ui/listar/all";
        }
    }

    @PostMapping("/atualizar/{id}")
    public String atualizarMarca(@PathVariable Long id,@Valid @ModelAttribute("marca") MarcaDTO marcaDTO, BindingResult result, RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            return "alterarMarca";
        }try{
            marcaService.alterarMarcaPorId(id, marcaDTO);
            redirectAttributes.addFlashAttribute("Mensagem", "Marca alterada com sucesso!");
            return "redirect:/marca/ui/listar/all";
        }catch (RegraNegocioException e){
            result.rejectValue(e.getCampo(), "error.marca", e.getMessage());
            return "alterarMarca";
        }

    }

    @GetMapping("/deletar/{id}")
    public String deletarMarca(@PathVariable Long id){
        marcaService.deletarId(id);
        return "redirect:/marca/ui/listar/all";
    }
}
