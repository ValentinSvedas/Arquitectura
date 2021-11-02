package com.example.ti4.filler;

import com.example.ti4.entities.Cliente;
import com.example.ti4.entities.Producto;
import com.example.ti4.entities.ProductoCliente;
import com.example.ti4.repositories.ClienteRepository;
import com.example.ti4.repositories.ProductoClienteRepository;
import com.example.ti4.repositories.ProductoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Configuration
public class DbFiller {

    @Bean
    public CommandLineRunner initDb(ClienteRepository clienteRepository, ProductoClienteRepository productoClienteRepository, ProductoRepository productoRepository) {
        Producto producto = new Producto(1, "Chocoloate", 23.2);
        Producto productSaved = productoRepository.save(producto);

        Cliente juan = new Cliente(1, "Juan");
        Cliente juanSaved = clienteRepository.save(juan);

        ProductoCliente productoCliente = new ProductoCliente(1, productSaved, juanSaved, 1, LocalDate.now());
        productoClienteRepository.save(productoCliente);
        return args-> {
            IntStream.range(0, 10).forEach(i->{
                Cliente c = new Cliente(i,"j"+i);
                clienteRepository.save(c);
            });
        };
    }
}

