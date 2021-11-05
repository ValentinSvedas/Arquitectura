package com.example.ti4.services;

import com.example.ti4.controller.ClienteController;
import com.example.ti4.controller.dto.ProductoCantVendido;
import com.example.ti4.controller.dto.ProductoClienteReporte;
import com.example.ti4.entities.Producto;
import com.example.ti4.entities.ProductoCliente;
import com.example.ti4.exceptions.ClienteMasDe3ProductosException;
import com.example.ti4.repositories.ProductoClienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class ProductoClienteService {
    private static Logger LOG = LoggerFactory.getLogger(ProductoClienteService.class);

    @Autowired
    private ProductoClienteRepository productoClienteRepository;

    public List<ProductoCliente> findAll() {
        return productoClienteRepository.findAll();
    }

    public ProductoCliente save(ProductoCliente productoCliente) {
        List<ProductoCliente> allByDate = productoClienteRepository.findAllByDate(LocalDate.now());
        int reduce = allByDate.stream()
                .map(pc -> pc.getCantidad())
                .reduce((x, y) -> x + y)
                .orElse(0);
        if (reduce + productoCliente.getCantidad() >= 3) {
            LOG.error("El cliente no puede cargar mas de un producto por dia");
            throw new ClienteMasDe3ProductosException();
        }
        return productoClienteRepository.save(productoCliente);
    }

    /**
     * trae las ventas por dias
     * @return
     */
    public List<ProductoClienteReporte> reporteVentasPorDias(){
        List<ProductoCliente> productosVendidos = this.productoClienteRepository.findAll();
        List<ProductoClienteReporte> listaProductoClienteReporte = new ArrayList<>();
        ProductoClienteReporte pcr = new ProductoClienteReporte();

        for (ProductoCliente pc: productosVendidos){
            if (pcr.getProductos().isEmpty()){
                pcr.getProductos().add(pc.getProducto());
                pcr.setDate(pc.getDate());
                listaProductoClienteReporte.add(pcr);
            }else{
                if (Objects.equals(pcr.getDate(),pc.getDate())){
                    pcr.getProductos().add(pc.getProducto());
                }else{
                    pcr = new ProductoClienteReporte();
                    pcr.getProductos().add(pc.getProducto());
                    pcr.setDate(pc.getDate());
                    listaProductoClienteReporte.add(pcr);
                }
            }
        }
        Collections.reverse(listaProductoClienteReporte);
        return listaProductoClienteReporte;
    }

    /**
     * Agarra el producto más vendido
     * @return
     */
    public ProductoCantVendido findProductoMasVendido(){
        List<ProductoCliente> productosVendidos = this.productoClienteRepository.findAll();
        List<ProductoCantVendido> productos = new ArrayList<>();
        for (ProductoCliente pv: productosVendidos){
            ProductoCantVendido pcv = new ProductoCantVendido();
            pcv.setName(pv.getProducto().getNombre());
            pcv.setCantidad(pv.getCantidad());
            if (!productos.contains(pcv)){
                productos.add(pcv);
            }else{
                for (ProductoCantVendido p: productos){
                    if (Objects.equals(pv.getProducto().getNombre(),p.getName())){
                       p.setCantidad(pv.getCantidad()+p.getCantidad());
                    }
                }
            }
        }
        Collections.sort(productos);
        return productos.get(0);
    }


}
