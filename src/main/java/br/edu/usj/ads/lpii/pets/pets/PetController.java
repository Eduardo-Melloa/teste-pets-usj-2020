package br.edu.usj.ads.lpii.pets.pets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import br.edu.usj.ads.lpii.pets.cliente.Cliente;
import br.edu.usj.ads.lpii.pets.cliente.ClienteRepository;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/pet")
public class PetController {
    
    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    PetRepository petRepository;

    @GetMapping(value="/cadastrarPet/{clienteId}")
    public ModelAndView getCadastrarPetPorId(@PathVariable Long clienteId) {

        Pet pet = new Pet();
        Cliente cliente = clienteRepository.findById(clienteId).get();

        ModelAndView mAv = new ModelAndView("cadastroPet");
        mAv.addObject("cliente", cliente);
        mAv.addObject("pet", pet);
        return mAv;
    }

    @PostMapping(value="/cadastrarPet")
    public String postCadastrarPet(Pet pet) {
        
        petRepository.save(pet);
        
        return "redirect:/detalhes/"+pet.getClienteId();
    }
    
    @GetMapping(value="/detalhes/{petId}")
    public ModelAndView getDetalhesPet(@PathVariable Long petId) {

        Pet pet = petRepository.findById(petId).get();
        Cliente cliente = clienteRepository.findById(pet.clienteId).get();

        ModelAndView mAv = new ModelAndView("petDetalhes");
        mAv.addObject("pet", pet);
        mAv.addObject("cliente", cliente);

        return mAv;
    }

    @GetMapping(value="/editar/{id}") 
    public ModelAndView getEditar(@PathVariable Long id) {

        Pet pet = petRepository.findById(id).get();
        Cliente cliente = clienteRepository.findById(pet.clienteId).get();

        ModelAndView mAv = new ModelAndView("cadastroPet");
        mAv.addObject("pet", pet);
        mAv.addObject("cliente", cliente);

        return mAv; 
    }

    @GetMapping(value="/delete/{petId}")
    public String getDeletar(@PathVariable Long petId) {
        Long id = petRepository.findById(petId).get().getClienteId();
        petRepository.deleteById(petId);

        return "redirect:/detalhes/"+id;
    }
    
    
}
