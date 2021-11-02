package com.example.ti4.services;

import com.example.ti4.entities.Producto;
import com.example.ti4.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productos;

    public Optional<Producto> getProductoById(int id){
        return this.productos.findById((id));
    }

    public List<Producto> getProductos() {
        return this.productos.findAll();
    }

    @Transactional
    public Boolean addProducto(Producto p) {
        this.productos.save(p);
        return true;
    }
}
