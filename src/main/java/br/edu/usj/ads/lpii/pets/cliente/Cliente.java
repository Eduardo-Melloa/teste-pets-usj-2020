package br.edu.usj.ads.lpii.pets.cliente;

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
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    Long cpf;

    @Column
    String nome;
    @Column
    String telefone;
    
	@Override
	public String toString() {
		return "Cliente [cpf=" + cpf + ", id=" + id + ", nome=" + nome + ", telefone=" + telefone + "]";
	}

    
}
