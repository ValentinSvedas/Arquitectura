package com.example.ti4.repositories;

import com.example.ti4.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository  extends JpaRepository<Cliente,Integer> {
}
