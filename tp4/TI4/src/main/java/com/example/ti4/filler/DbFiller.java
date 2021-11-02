package com.example.ti4.filler;

import com.example.ti4.entities.Cliente;
import com.example.ti4.entities.Producto;
import com.example.ti4.repositories.ClienteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

    @Configuration
    public class DbFiller {

        @Bean
        public CommandLineRunner initDb(ClienteRepository clientes) {
            return args-> {
                IntStream.range(0, 10).forEach(i->{
                    List<Producto> p = new ArrayList<>();
                    Cliente c = new Cliente(i,"juan"+i,0,p);
                    clientes.save(c);
                });
            };
        }
    }

