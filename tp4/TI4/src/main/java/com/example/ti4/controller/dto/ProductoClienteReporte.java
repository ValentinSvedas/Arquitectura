package com.example.ti4.controller.dto;

import com.example.ti4.entities.Producto;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ProductoClienteReporte {
    private LocalDate date;
    private List<Producto> productos;
}
