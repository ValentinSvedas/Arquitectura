package com.example.ti4.services;

import com.example.ti4.controller.ClienteController;
import com.example.ti4.entities.ProductoCliente;
import com.example.ti4.exceptions.ClienteMasDe3ProductosException;
import com.example.ti4.repositories.ProductoClienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoClienteService {
    private static Logger LOG = LoggerFactory.getLogger(ProductoClienteService.class);

    @Autowired
    private ProductoClienteRepository productoClienteRepository;

    public List<ProductoCliente> findAll() {
        return productoClienteRepository.findAll();
    }

    public ProductoCliente save(ProductoCliente productoCliente) {
        List<ProductoCliente> allByDate = productoClienteRepository.findAllByDate(LocalDate.now());
        int reduce = allByDate.stream()
                .map(pc -> pc.getCantidad())
                .reduce((x, y) -> x + y)
                .orElse(0);
        if (reduce + productoCliente.getCantidad() >= 3) {
            LOG.error("El cliente no puede cargar mas de un producto por dia");
            throw new ClienteMasDe3ProductosException();
        }
        return productoClienteRepository.save(productoCliente);
    }
}
