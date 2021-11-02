package com.example.ti4;

import com.example.ti4.entities.ProductoCliente;
import com.example.ti4.services.ProductoClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Ti4Application {

    public static void main(String[] args) {
        SpringApplication.run(Ti4Application.class, args);
    }
}
