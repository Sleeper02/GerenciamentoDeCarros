package Algx.GerenciamentoDeCarros.Carros;

import Algx.GerenciamentoDeCarros.Marca.MarcaModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_carros")

@NoArgsConstructor
@AllArgsConstructor
@Data

public class CarroModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "modelo")
    private String modelo;

    @Column(name = "placa", unique = true)
    private String placa;

    @ManyToOne
    @JoinColumn(name = "marca_id", nullable = false)
    private MarcaModel marca;

    @Column(name = "imgUrl")
    private String imgUrl;
}
