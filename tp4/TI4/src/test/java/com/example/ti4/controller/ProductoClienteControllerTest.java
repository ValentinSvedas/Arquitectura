package com.example.ti4.controller;

import com.example.ti4.controller.dto.ClienteComprasReporte;
import com.example.ti4.controller.dto.ProductoCantVendido;
import com.example.ti4.controller.dto.ProductoClienteReporte;
import com.example.ti4.entities.Cliente;
import com.example.ti4.entities.Producto;
import com.example.ti4.entities.ProductoCliente;
import com.example.ti4.exceptions.ClienteMasDe3ProductosException;
import com.example.ti4.repositories.ClienteRepository;
import com.example.ti4.repositories.ProductoClienteRepository;
import com.example.ti4.services.ClienteService;
import com.example.ti4.services.ProductoClienteService;
import com.example.ti4.services.ProductoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class ProductoClienteServiceTest {


    @Autowired
    private ProductoClienteRepository productoClienteRepositoryMock = Mockito.mock(ProductoClienteRepository.class);

    @Autowired
    private ClienteService clienteServiceMock = Mockito.mock(ClienteService.class);

    private ProductoClienteService productoClienteService = new ProductoClienteService(productoClienteRepositoryMock, clienteServiceMock);

    @BeforeEach
    void setUp() {
        List<Cliente> clienteList = new ArrayList<>();
        List<ProductoCliente> productoClienteRepository = new ArrayList<>();
        Cliente juan = new Cliente(1,"Juan");
        Cliente jorge = new Cliente(2,"Jorge");
        Cliente adriana = new Cliente(3,"Adriana");
        Cliente marta = new Cliente(4,"Marta");
        Producto chocolate = new Producto(1,"Chocolate",22);
        Producto manzana = new Producto(2,"Manzana",10);
        Producto alfajor = new Producto(3,"Alfajor",15);
        clienteList.add(juan);
        clienteList.add(jorge);
        clienteList.add(adriana);
        clienteList.add(marta);
        ProductoCliente compra1 = new ProductoCliente(1, chocolate, juan, 2, LocalDate.now());
        ProductoCliente compra2 = new ProductoCliente(2, manzana, jorge, 2, LocalDate.now());
        ProductoCliente compra3 = new ProductoCliente(3, alfajor, adriana, 1, LocalDate.now());
        ProductoCliente compra4 = new ProductoCliente(4, alfajor, marta, 2, LocalDate.now());
        productoClienteRepository.add(compra1);
        productoClienteRepository.add(compra2);
        productoClienteRepository.add(compra3);
        productoClienteRepository.add(compra4);

        Mockito.when(productoClienteRepositoryMock.findAll()).thenReturn(productoClienteRepository);
        Mockito.when(clienteServiceMock.findAll()).thenReturn(clienteList);
        Mockito.when(productoClienteService.findAllClientes()).thenReturn(clienteList);
    }

    @Test
    void save(){
        List<Cliente> Clientes = productoClienteService.findAllClientes();
        for (Cliente c: Clientes){
            Assertions.assertThrows(ClienteMasDe3ProductosException.class,()->{
                productoClienteService.save( new ProductoCliente(4, new Producto(1,"Alfajor",22), c, 4, LocalDate.now()));
            });
        }
    }

    @Test
    void getProductoMasVendido() {
        ProductoCantVendido productoMasVendido = productoClienteService.findProductoMasVendido();
        List<ProductoCliente> productosVendidos = this.productoClienteRepositoryMock.findAll();
        List<ProductoCantVendido> productos = new ArrayList<>();
        for (ProductoCliente pv: productosVendidos){
            ProductoCantVendido pcv = new ProductoCantVendido();
            pcv.setName(pv.getProducto().getNombre());
            pcv.setCantidad(pv.getCantidad());
            if (!productos.contains(pcv)){
                productos.add(pcv);
            } else {
                for (ProductoCantVendido p: productos){
                    if (Objects.equals(pv.getProducto().getNombre(),p.getName())){
                        p.setCantidad(pv.getCantidad()+p.getCantidad());
                    }
                }
            }
        }
        Collections.sort(productos);
        Assertions.assertEquals(productoMasVendido,productos.get(0));
    }

    @Test
    void getVentasPorDia() {
        List<ProductoClienteReporte> ventasRealizadasHoy = productoClienteService.reporteVentasPorDias();
        System.out.println(ventasRealizadasHoy);
        Assertions.assertEquals(ventasRealizadasHoy.get(0).getProductos().size(),4);
    }

    @Test
    void getProductoClienteReport() {
        int i =-1;
        List<ProductoCliente> productoClientes = productoClienteRepositoryMock.findAll();
        List<ClienteComprasReporte> productosPorCliente = this.productoClienteService.getProductoClienteReport();
        Assertions.assertEquals(productosPorCliente.size(), 4);
        for (ProductoCliente productosVendidos : productoClientes){
            i++;
            double precio = productosVendidos.getProducto().getPrecio();
            Assertions.assertEquals(precio * productosVendidos.getCantidad(), productosPorCliente.get(i).getTotal());
        }
    }
}