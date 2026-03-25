package Algx.GerenciamentoDeCarros.Clientes;

import Algx.GerenciamentoDeCarros.Carros.CarroModel;
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
            clienteMapper.atualizarCliente(clienteDTO, cliente);
            cliente = clienteRepository.save(cliente);

            return  clienteMapper.map(cliente);
        }
        return null;
    }

    public void deletarPorId(Long id){
        if(clienteRepository.existsById(id)){
            clienteRepository.deleteById(id);
        }
    }
}
