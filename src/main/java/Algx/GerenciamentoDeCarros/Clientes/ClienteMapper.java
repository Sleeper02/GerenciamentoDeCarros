package Algx.GerenciamentoDeCarros.Clientes;

import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    public ClienteModel map(ClienteDTO clienteDTO){
        ClienteModel clienteModel = new ClienteModel();
        clienteModel.setId(clienteDTO.getId());
        clienteModel.setNome(clienteDTO.getNome());
        clienteModel.setCPF(clienteDTO.getCPF());
        clienteModel.setEmail(clienteDTO.getEmail());
        clienteModel.setTelefone(clienteDTO.getTelefone());
        clienteModel.setEndereco(clienteDTO.getEndereco());
        clienteModel.setCarro(clienteDTO.getCarro());

        return clienteModel;
    }

    public ClienteDTO map(ClienteModel clienteModel){
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(clienteModel.getId());
        clienteDTO.setNome(clienteModel.getNome());
        clienteDTO.setCPF(clienteModel.getCPF());
        clienteDTO.setEmail(clienteModel.getEmail());
        clienteDTO.setTelefone(clienteModel.getTelefone());
        clienteDTO.setEndereco(clienteModel.getEndereco());
        clienteDTO.setCarro(clienteModel.getCarro());

        return clienteDTO;
    }

    public void atualizarCliente (ClienteDTO clienteDTO, ClienteModel clienteModelExit){
        if(clienteDTO.getNome() != null){
            clienteModelExit.setNome(clienteDTO.getNome());
        }
        if(clienteDTO.getCPF() != null){
            clienteModelExit.setCPF(clienteDTO.getCPF());
        }
        if(clienteDTO.getEmail()!= null){
            clienteModelExit.setEmail(clienteDTO.getEmail());
        }
        if(clienteDTO.getTelefone()!= null){
            clienteModelExit.setTelefone(clienteDTO.getTelefone());
        }
        if(clienteDTO.getEndereco()!= null){
            clienteModelExit.setEndereco(clienteDTO.getEndereco());
        }
        if(clienteDTO.getCarro()!= null){
            clienteModelExit.setCarro(clienteDTO.getCarro());
        }
    }
}
