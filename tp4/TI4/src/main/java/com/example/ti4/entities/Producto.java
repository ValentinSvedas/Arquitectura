package com.example.ti4.entities;

import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
@Entity
@Data
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String nombre;

    @Column
    private double precio;

    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private Cliente cliente;
}
