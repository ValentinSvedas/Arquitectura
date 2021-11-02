package com.example.ti4.entities;

import lombok.Data;

import javax.persistence.*;
@Entity
@Data
public class Producto {
    @Id
    private int id;

    @Column
    private String nombre;

    @Column
    private double precio;

    @ManyToOne
    private Cliente cliente;
}
