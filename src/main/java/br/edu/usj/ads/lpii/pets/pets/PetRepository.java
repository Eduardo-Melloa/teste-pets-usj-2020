package br.edu.usj.ads.lpii.pets.pets;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends CrudRepository<Pet, Long>{
    
    List<Pet> findAll();
    List<Pet> findByClienteId(Long clienteId);
    
}
