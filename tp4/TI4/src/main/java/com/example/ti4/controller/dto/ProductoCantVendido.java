package com.example.ti4.controller.dto;

import com.example.ti4.entities.Producto;
import lombok.Data;

import java.util.Comparator;

@Data
public class ProductoCantVendido implements Comparable {
    private String name;
    private Integer cantidad;


    @Override
    public int compareTo(Object o) {
        ProductoCantVendido pcv = (ProductoCantVendido) o;
        return pcv.getCantidad().compareTo(this.getCantidad());
    }
    @Override
    public boolean equals(Object o){
        ProductoCantVendido pcv = (ProductoCantVendido) o;
        return pcv.getName().equals(this.getName());
    }
}
