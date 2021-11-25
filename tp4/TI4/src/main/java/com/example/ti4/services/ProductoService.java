package com.example.ti4.services;

import com.example.ti4.entities.Producto;
import com.example.ti4.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productos;

    /**
     * busca un producto especifico
     * @param id
     * @return producto
     */
    public Optional<Producto> getProductoById(int id){
        return this.productos.findById((id));
    }

    /**
     * busca todos los producto
     * @return lista de productos
     */
    public List<Producto> getProductos() {
        return this.productos.findAll();
    }

    /**
     * Añade un producto
     * @param p
     * @return Boolean
     */
    @Transactional
    public Boolean addProducto(Producto p) {
        this.productos.save(p);
        return true;
    }

    /**
     * Busca producto por id
     * @param productoId
     * @return Producto
     */
    public Optional<Producto> findById(int productoId) {
        return this.productos.findById(productoId);
    }


}
