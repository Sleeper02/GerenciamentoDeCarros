package Algx.GerenciamentoDeCarros.Clientes;

import Algx.GerenciamentoDeCarros.Carros.CarroDTO;
import Algx.GerenciamentoDeCarros.Carros.CarroService;
import Algx.GerenciamentoDeCarros.RegraNegocioException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cliente/ui")
public class ClienteControllerUi {

    private final ClienteService clienteService;
    private final CarroService carroService;

    public ClienteControllerUi(ClienteService clienteService, CarroService carroService) {
        this.clienteService = clienteService;
        this.carroService = carroService;
    }

    @GetMapping("/criar")
    public String formsAdicionarCliente(Model model){
        model.addAttribute("cliente", new ClienteDTO());
        model.addAttribute("carro", carroService.listarCarrosDisponiveis());
        return "adicionarCliente";
    }

    @PostMapping("/salvar")
    public String salvarCliente(@Valid @ModelAttribute("cliente") ClienteDTO clienteDTO, BindingResult result, Model model, RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            model.addAttribute("carro", carroService.listarCarros());
            return "adicionarCliente";
        }try{
            clienteService.criarCliente(clienteDTO);
            redirectAttributes.addFlashAttribute("Mensagem", "Cliente cadastrado com sucesso!");
            return "redirect:/cliente/ui/listar/all";
        }catch (RegraNegocioException e){
            result.rejectValue(e.getCampo(), "error.cliente", e.getMessage());
            model.addAttribute("carro", carroService.listarCarros());
            return "adicionarCliente";
        }
    }

    @GetMapping("/listar/all")
    public Object listarClientes(Model model){
        List<ClienteDTO> clienteDTO = clienteService.listarClientes();
        if(!clienteDTO.isEmpty()){
            model.addAttribute("cliente", clienteDTO);
            return "listarCliente";
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Não há clientes registrados");
        }
    }

    @GetMapping("/listar/{id}")
    public String listarClientePorId(@PathVariable Long id, Model model){
        ClienteDTO clienteDTO = clienteService.listarClientePorId(id);
        if(clienteDTO!=null){
            model.addAttribute("cliente", clienteDTO);
            return "detalhesCliente";
        }else{
            model.addAttribute("mensagem", "Cliente não encontrado nos nossos registros");
            return "redirect:/cliente/ui/listar/all";
        }
    }

    @GetMapping("/alterar/{id}")
    public String formsAlterarCliente(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes){
        ClienteDTO clienteDTO = clienteService.listarClientePorId(id);
        if(clienteDTO != null){
            model.addAttribute("cliente", clienteDTO);
            model.addAttribute("carro", carroService.listarCarrosTela(clienteDTO));
            return "alterarCliente";
        }else{
            redirectAttributes.addFlashAttribute("Mensagem", "Cliente não encontrado nos nossos registros");
            return "redirect:/cliente/ui/listar/all";
        }
    }

    @PostMapping("/atualizar/{id}")
    public String atualizarCliente(@PathVariable Long id,@Valid @ModelAttribute("cliente") ClienteDTO clienteDTO, BindingResult result, RedirectAttributes redirectAttributes, Model model){
       if(result.hasErrors()){
           model.addAttribute("carro", carroService.listarCarrosTela(clienteDTO));
           return "alterarCliente";
       }try{
            clienteService.alterarClientePorId(id, clienteDTO);
            redirectAttributes.addFlashAttribute("Mensagem", "Cliente alterado com sucesso!");
            return "redirect:/cliente/ui/listar/all";
        }catch (RegraNegocioException e){
            result.rejectValue(e.getCampo(), "error.cliente", e.getMessage());
            model.addAttribute("carro", carroService.listarCarrosTela(clienteDTO));
            return "alterarCliente";
        }

    }

    @GetMapping("/deletar/{id}")
    public String deletarCliente(@PathVariable Long id){
        clienteService.deletarPorId(id);
        return "redirect:/cliente/ui/listar/all";
    }
}
