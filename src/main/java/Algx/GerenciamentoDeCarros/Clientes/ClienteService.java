package Algx.GerenciamentoDeCarros.Clientes;

import Algx.GerenciamentoDeCarros.Carros.CarroModel;
import Algx.GerenciamentoDeCarros.RegraNegocioException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {
    private ClienteMapper clienteMapper;
    private ClienteRepository clienteRepository;

    public ClienteService(ClienteMapper clienteMapper, ClienteRepository clienteRepository) {
        this.clienteMapper = clienteMapper;
        this.clienteRepository = clienteRepository;
    }

    public ClienteDTO criarCliente(ClienteDTO clienteDTO){
        if (clienteRepository.existsByCPF(clienteDTO.getCPF())) {
            throw new RegraNegocioException("CPF", "Erro: Este CPF já está cadastrado!");
        }
        if (clienteRepository.existsByEmail(clienteDTO.getEmail())) {
            throw new RegraNegocioException("email", "Erro: Este E-mail já está em uso!");
        }
        if(clienteDTO.getCarro() == null || clienteDTO.getCarro().getId() == null){
            throw new RegraNegocioException("carro", "Erro: Para criar um cliente, deve-se selecionar um carro!");
        }
        ClienteModel clienteModel = clienteMapper.map(clienteDTO);
        clienteModel = clienteRepository.save(clienteModel);

        return  clienteMapper.map(clienteModel);
    }

    public List<ClienteDTO> listarClientes(){
        List<ClienteModel> clienteModels = clienteRepository.findAll();

        return clienteModels.stream()
                .map(clienteMapper::map)
                .collect(Collectors.toList());
    }

    public ClienteDTO listarClientePorId(Long id){
        Optional<ClienteModel> clienteModel = clienteRepository.findById(id);
        return clienteModel.map(clienteMapper::map).orElse(null);
    }

    public ClienteDTO alterarClientePorId(Long id, ClienteDTO clienteDTO){
        Optional<ClienteModel> clienteModel = clienteRepository.findById(id);
        if(clienteModel.isPresent()){
            ClienteModel cliente = clienteModel.get();

            String cpf = cliente.getCPF();

            if ((!cliente.getEmail().equals(clienteDTO.getEmail()) && clienteRepository.existsByEmail(clienteDTO.getEmail()))){
                throw new RegraNegocioException("email", "Erro: Este E-mail já está em uso!");
            }
            if(clienteDTO.getCarro() == null || clienteDTO.getCarro().getId() == null){
                throw new RegraNegocioException("carro", "Erro: Deve-se selecionar um carro!");
            }
            clienteMapper.atualizarCliente(clienteDTO, cliente);
            cliente.setCPF(cpf);
            cliente = clienteRepository.save(cliente);

            return  clienteMapper.map(cliente);
        }
        return null;
    }

    public void deletarPorId(Long id) {
        Optional<ClienteModel> clienteOpt = clienteRepository.findById(id);

        if (clienteOpt.isPresent()) {
            ClienteModel cliente = clienteOpt.get();

            if (cliente.getCarro() != null) {
                cliente.getCarro().setCliente(null);
                cliente.setCarro(null);
            }

            clienteRepository.delete(cliente);
        }
    }
}
