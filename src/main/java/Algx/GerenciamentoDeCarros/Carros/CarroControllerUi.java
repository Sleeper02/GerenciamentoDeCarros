package Algx.GerenciamentoDeCarros.Carros;

import Algx.GerenciamentoDeCarros.Marca.MarcaDTO;
import Algx.GerenciamentoDeCarros.Marca.MarcaService;
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
@RequestMapping("/carro/ui")
public class CarroControllerUi {

    private final CarroService carroService;
    private final MarcaService marcaService;

    public CarroControllerUi(CarroService carroService, MarcaService marcaService) {
        this.carroService = carroService;
        this.marcaService = marcaService;
    }

    @GetMapping("/criar")
    public String formsAdicionarCarro(Model model){
        model.addAttribute("carro", new CarroDTO());
        List<MarcaDTO> marcaDTO = marcaService.listarMarcas();
        model.addAttribute("marca", marcaDTO);
        return "adicionarCarro";
    }

    @PostMapping("/salvar")
    public String salvarCarro(@Valid @ModelAttribute("carro") CarroDTO carroDTO, BindingResult result, Model model, RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            // Se der erro, devolve a lista de marcas pro <select> não sumir
            model.addAttribute("marca", marcaService.listarMarcas());
            return "adicionarCarro";
        }try {
            carroService.criarCarro(carroDTO);
            redirectAttributes.addFlashAttribute("Mensagem", "Carro cadastrado com sucesso!");
            return "redirect:/carro/ui/listar/all";
        }catch (RegraNegocioException e){
            result.rejectValue(e.getCampo(), "error.carro", e.getMessage());
            model.addAttribute("marca", marcaService.listarMarcas());
            return "adicionarCarro";
        }

    }

    @GetMapping("/listar/all")
    public Object listarCarros(Model model){
        List<CarroDTO> carroDTO = carroService.listarCarros();
        if(!carroDTO.isEmpty()){
            model.addAttribute("carro", carroDTO);
            return "listarCarro";
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Não há carros registrados");
        }
    }

    @GetMapping("/listar/{id}")
    public String listarCarroPorId(@PathVariable Long id, Model model){
        CarroDTO carroDTO = carroService.listarCarroId(id);
        if(carroDTO!=null){
            model.addAttribute("carro", carroDTO);
            return "detalhesCarro";
        }else{
            model.addAttribute("mensagem", "Carro não encontrado nos nossos registros");
            return "redirect:/carro/ui/listar/all";
        }
    }

    @GetMapping("/alterar/{id}")
    public String formsAlterarCarro(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes){
        CarroDTO carroDTO = carroService.listarCarroId(id);
        if(carroDTO != null){
            model.addAttribute("carro", carroDTO);
            model.addAttribute("marca", marcaService.listarMarcas());
            return "alterarCarro";
        }else{
            redirectAttributes.addFlashAttribute("Mensagem", "Carro não encontrado nos nossos registros");
            return "redirect:/carro/ui/listar/all";
        }
    }

    @PostMapping("/atualizar/{id}")
    public String atualizarCarro(@PathVariable Long id,@Valid @ModelAttribute("carro") CarroDTO carroDTO, BindingResult result, RedirectAttributes redirectAttributes, Model model){
        if(result.hasErrors()){
            model.addAttribute("marca", marcaService.listarMarcas());
            return "alterarCarro";
        }try{
            carroService.alterarCarroPorId(id, carroDTO);
            redirectAttributes.addFlashAttribute("Mensagem", "Carro alterado com sucesso!");
            return "redirect:/carro/ui/listar/all";
        }catch (RegraNegocioException e){
            result.rejectValue(e.getCampo(), "error.carro", e.getMessage());
            model.addAttribute("marca", marcaService.listarMarcas());
            return "alterarCarro";
        }
    }

    @GetMapping("/deletar/{id}")
    public String deletarCarro(@PathVariable Long id){
        carroService.deletarCarroId(id);
        return "redirect:/carro/ui/listar/all";
    }
}
