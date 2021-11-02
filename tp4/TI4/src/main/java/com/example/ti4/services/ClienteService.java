package com.example.ti4.services;

import com.example.ti4.entities.Cliente;
import com.example.ti4.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clientes;
    
    public Optional<Cliente> getClienteById(int id){
        return this.clientes.findById((id));
    }

    public List<Cliente> getClientes() {
        return this.clientes.findAll();
    }
    @Transactional
    public Boolean addCliente(Cliente c) {
        this.clientes.save(c);
        return true;
    }
}
