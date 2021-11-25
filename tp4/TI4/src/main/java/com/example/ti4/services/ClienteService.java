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

    /**
     * Devuelve el cliente por id
     * @param id id del cliente
     * @return Cliente
     */
    public Optional<Cliente> getClienteById(int id){
        return this.clienteRepository.findById((id));
    }


    public List<Cliente> findAll() {
        return this.clienteRepository.findAll();
    }

    /**
     * Añade un cliente
     * @param c
     * @return un boolean verificando si se pudo agregar
     */
    @Transactional
    public Boolean addCliente(Cliente c) {
        this.clienteRepository.save(c);
        return true;
    }

    /**
     * Busca cliente por id
     * @param clienteId
     * @return Cliente
     */
    public Optional<Cliente> findById(int clienteId) {
        return this.clienteRepository.findById(clienteId);
    }
}
