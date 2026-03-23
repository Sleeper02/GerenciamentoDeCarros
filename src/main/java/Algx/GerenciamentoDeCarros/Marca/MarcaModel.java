package Algx.GerenciamentoDeCarros.Marca;

import Algx.GerenciamentoDeCarros.Carros.CarroModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tb_marcas")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MarcaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", unique = true, nullable = false)
    private String nome;

    // Uma marca possui vários carros
    @OneToMany(mappedBy = "marca")
    @JsonIgnore
    private List<CarroModel> carros;
}
