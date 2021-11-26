package com.example.ti4.repositories;

import com.example.ti4.entities.Cliente;
import com.example.ti4.entities.ProductoCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ProductoClienteRepository extends JpaRepository<ProductoCliente, Integer> {

//    @Query("select p from ProductoCliente p where p.date <= :date")
//    List<Article> findAllBySameDate(@Param("creationDateTime") Date creationDateTime);

    List<ProductoCliente> findAllByDateAndCliente(LocalDate date, Cliente cliente);

}
