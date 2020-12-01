package br.edu.usj.ads.lpii.pets.pets;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Pet {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    Long clienteId;

    @Column
    String nome;

    @Column
    String descricao;

    @Column
    Integer idade;

    @Column
    String tipo;

    @Column
    String raca;
}
