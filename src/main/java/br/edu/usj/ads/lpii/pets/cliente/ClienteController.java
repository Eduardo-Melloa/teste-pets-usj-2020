package br.edu.usj.ads.lpii.pets.cliente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import br.edu.usj.ads.lpii.pets.pets.Pet;
import br.edu.usj.ads.lpii.pets.pets.PetRepository;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;






@Controller
public class ClienteController {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    PetRepository petRepository;

    @GetMapping(value="/")
    public ModelAndView getListaClientes() {
        List<Cliente> listaClientes= clienteRepository.findAll();
        
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("listaClientes", listaClientes);

        return modelAndView;
    }

    @GetMapping(value="/cadastroClientes")
    public ModelAndView getCadastroCliente() {
        Cliente cliente= new Cliente(); 
        
        ModelAndView modelAndView = new ModelAndView("cadastroClientes");
        modelAndView.addObject("cliente", cliente);

        return modelAndView;
    }

    @GetMapping(value="/erro")
    public String getPesquisar() {
        return "erro";
    }
    
    @PostMapping(value="/adicionar")
    public String postCadastroCliente(Cliente cliente) {
        System.out.println(cliente.toString());

        if (cliente.id != null) {
            clienteRepository.save(cliente);
            return "redirect:/detalhes/"+cliente.id;
        } else if (clienteRepository.findByCpf(cliente.getCpf()).isEmpty()) {
            clienteRepository.save(cliente);
            return "redirect:/";
        }
        return "erro";
    }
    
    @GetMapping(value="/detalhes/{id}")
    public ModelAndView getDetalhes(@PathVariable Long id) {

        Cliente cliente = clienteRepository.findById(id).get();
        List<Pet> listaPets = petRepository.findByClienteId(id);

        ModelAndView mAv = new ModelAndView("detalhes");
        mAv.addObject("cliente", cliente);
        mAv.addObject("listaPets", listaPets);
        return mAv; 
    }

    @GetMapping(value="/editar/{id}") 
    public ModelAndView getEditar(@PathVariable Long id) {

        Cliente cliente = clienteRepository.findById(id).get();

        ModelAndView mAv = new ModelAndView("cadastroClientes");
        mAv.addObject("cliente", cliente);
        return mAv; 
    }
    
    @GetMapping(value="/delete/{clienteId}")
    public String getMethodName(@PathVariable Long clienteId) {

        List<Pet> listaPets = petRepository.findByClienteId(clienteId);
        for(Pet pet : listaPets) {
            petRepository.deleteById(pet.getId());
        }

        clienteRepository.deleteById(clienteId);

        return "redirect:/";
    }

    @GetMapping(value="/pesquisaCliente")
    public String getPesquisaCliente() {
        return "pesquisaCliente";
    }
    
    @PostMapping(value="/pesquisaCliente")
    public ModelAndView postPesquisaClientePorNome(@RequestParam String nome) {
        
        List<Cliente> listaClientes = clienteRepository.findByNomeContainingIgnoreCaseOrderByNome(nome);
        ModelAndView mAv= new ModelAndView("pesquisaCliente");
        mAv.addObject("listaClientes", listaClientes);
        mAv.addObject("nome", nome);
                
        return mAv;
    }
    
    

}
