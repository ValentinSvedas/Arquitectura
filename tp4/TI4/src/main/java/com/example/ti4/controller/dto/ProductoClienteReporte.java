package com.example.ti4.controller.dto;

import com.example.ti4.entities.Producto;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProductoClienteReporte implements Comparable {
    private LocalDate date;
    private List<Producto> productos = new ArrayList<>();

    @Override
    public int compareTo(Object o) {
        ProductoClienteReporte pcr = (ProductoClienteReporte) o;
        return pcr.getDate().compareTo(this.getDate());
    }
}
