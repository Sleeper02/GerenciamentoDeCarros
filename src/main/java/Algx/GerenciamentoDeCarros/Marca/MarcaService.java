package Algx.GerenciamentoDeCarros.Marca;

import Algx.GerenciamentoDeCarros.Carros.CarroModel;
import Algx.GerenciamentoDeCarros.RegraNegocioException;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MarcaService {
    private MarcaRepository marcaRepository;
    private MarcaMapper marcaMapper;

    public MarcaService(MarcaRepository marcaRepository, MarcaMapper marcaMapper) {
        this.marcaRepository = marcaRepository;
        this.marcaMapper = marcaMapper;
    }

    public MarcaDTO criarMarca(MarcaDTO marcaDTO){
        if(marcaRepository.existsByNomeIgnoreCase(marcaDTO.getNome())){
            throw new RegraNegocioException("nome", "Erro: Este nome já está cadastrado no sistema!");

        }
        MarcaModel marcaModel = marcaMapper.map(marcaDTO);
        marcaModel = marcaRepository.save(marcaModel);

        return marcaMapper.map(marcaModel);
    }

    public List<MarcaDTO> listarMarcas(){
        List<MarcaModel> marcaModels = marcaRepository.findAll();
        return marcaModels.stream()
                .map(marcaMapper :: map)
                .collect(Collectors.toList());
    }

    public MarcaDTO listarMarcaId(Long id){
        Optional<MarcaModel> marcaModel = marcaRepository.findById(id);
        return marcaModel.map(marcaMapper::map).orElse(null);
    }

    public void deletarId(Long id){
        if(marcaRepository.existsById(id)){
            marcaRepository.deleteById(id);
        }else{
            return;
        }
    }

    public MarcaDTO alterarMarcaPorId(Long id, MarcaDTO marcaDTO){
        Optional<MarcaModel> marcaModel = marcaRepository.findById(id);
        if(marcaModel.isPresent()){
            MarcaModel marca = marcaModel.get();
            if((!marca.getNome().equals(marcaDTO.getNome()) && marcaRepository.existsByNomeIgnoreCase(marcaDTO.getNome()))){
                throw new RegraNegocioException("nome", "Erro: Este nome já está em uso!");
            }
            marcaMapper.atualizarMarca(marcaDTO, marca);
            marca = marcaRepository.save(marca);

            return marcaMapper.map(marca);
        }

        return null;
    }


}
