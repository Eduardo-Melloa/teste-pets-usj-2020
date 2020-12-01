package br.edu.usj.ads.lpii.pets.cliente;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ClienteRepository extends CrudRepository<Cliente , Long> {
    List<Cliente> findAll();
    List<Cliente> findByCpf(Long cpf);
    List<Cliente> findByNomeContainingIgnoreCaseOrderByNome(String nome);
}
