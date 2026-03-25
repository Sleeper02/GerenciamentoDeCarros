package Algx.GerenciamentoDeCarros.Clientes;

import Algx.GerenciamentoDeCarros.Carros.CarroModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tb_clientes")

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClienteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(unique = true, name = "cpf")
    private String CPF;

    @Column(unique = true, name = "email")
    private String email;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "endereco")
    private String endereco;

    @OneToOne
    @JoinColumn(name = "carro_id")
    @JsonIgnore
    private CarroModel carro;

}
