package com.example.ti4.services;

import com.example.ti4.entities.Cliente;
import com.example.ti4.entities.Producto;
import com.example.ti4.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    
    public Optional<Cliente> getClienteById(int id){
        return this.clienteRepository.findById((id));
    }

    public List<Cliente> findAll() {
        return this.clienteRepository.findAll();
    }

    @Transactional
    public Boolean addCliente(Cliente c) {
        this.clienteRepository.save(c);
        return true;
    }

    public Optional<Cliente> findById(int clienteId) {
        return this.clienteRepository.findById(clienteId);
    }
}
