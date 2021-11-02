package com.example.ti4.controller.dto;

import com.example.ti4.entities.Cliente;
import lombok.Data;


/**
 * Genere un reporte donde se indiquen los clientes y el monto total de sus compras.
 */
@Data
public class ClienteComprasReporte {
    private Cliente cliente;
    private int total;
}
