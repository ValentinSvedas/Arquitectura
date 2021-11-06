package com.example.ti4.controller;

import com.example.ti4.controller.dto.ClienteComprasReporte;
import com.example.ti4.controller.dto.ProductoCantVendido;
import com.example.ti4.controller.dto.ProductoClienteDto;
import com.example.ti4.controller.dto.ProductoClienteReporte;
import com.example.ti4.entities.Cliente;
import com.example.ti4.entities.Producto;
import com.example.ti4.entities.ProductoCliente;
import com.example.ti4.exceptions.ClienteNotFoundException;
import com.example.ti4.exceptions.ProductoNotFoundException;
import com.example.ti4.services.ClienteService;
import com.example.ti4.services.ProductoClienteService;
import com.example.ti4.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/producto/comprar")
public class ProductoClienteController {
    @Autowired
    private ProductoClienteService productoClienteService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ProductoCliente save(@RequestBody ProductoClienteDto productoClienteDto) {
        Optional<Producto> producto = productoService.findById(productoClienteDto.getProductoId());
        if(!producto.isPresent()){
            throw new ProductoNotFoundException();
        }

        Optional<Cliente> cliente = clienteService.findById(productoClienteDto.getClienteId());
        if(!cliente.isPresent()) {
            throw new ClienteNotFoundException();
        }

        ProductoCliente productoCliente = new ProductoCliente();
        productoCliente.setCliente(cliente.get());
        productoCliente.setProducto(producto.get());
        productoCliente.setCantidad(productoClienteDto.getCantidad());
        productoCliente.setDate(LocalDate.now());
        return this.productoClienteService.save(productoCliente);
    }

    @GetMapping("/productoMasVendido")
    public ProductoCantVendido getProductoMasVendido(){
     return this.productoClienteService.findProductoMasVendido();
    }

    @GetMapping("/ventasPorDia")
    public List<ProductoClienteReporte> getVentasPorDia(){
        return this.productoClienteService.reporteVentasPorDias();
    }

    @GetMapping("/clienteMonto")
    public List<ClienteComprasReporte> getProductoClienteReport() {
        List<ProductoCliente> all = productoClienteService.findAll();
        List<Cliente> clientesTotales = clienteService.findAll();
        List<ClienteComprasReporte> clientesMonto = new ArrayList<>();
        for (Cliente c: clientesTotales){
            ClienteComprasReporte ccr = new ClienteComprasReporte();
            ccr.setCliente(c);
            for(ProductoCliente p: all) {
                if (Objects.equals(p.getCliente(),ccr.getCliente())){
                    ccr.setTotal(ccr.getTotal()+p.getProducto().getPrecio());
                }
            }
            clientesMonto.add(ccr);
        }

        return clientesMonto;
    }
}
