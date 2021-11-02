package com.example.ti4.controller;

import com.example.ti4.entities.Cliente;
import com.example.ti4.services.ClienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/cliente")
public class ClienteController {
    private static Logger LOG = LoggerFactory.getLogger(ClienteController.class);

    @Autowired
    private ClienteService servicioClientes;

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getCliente(@PathVariable("id")int id){
        LOG.info("Buscando Cliente {}",id);
        Optional<Cliente> cliente = this.servicioClientes.getClienteById(id);
        if (!cliente.isPresent()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cliente.get(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Cliente> addCliente(@RequestBody Cliente c){
       Boolean ok = this.servicioClientes.addCliente(c);
       if(!ok){
             return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
       }
       return new ResponseEntity<>(c,HttpStatus.OK);
    }

    @GetMapping("")
    public List<Cliente> getAll(){
        return this.servicioClientes.getClientes();
    }

}
