package com.example.ti4.controller;

import com.example.ti4.entities.Cliente;
import com.example.ti4.entities.Producto;
import com.example.ti4.services.ProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/producto")
public class ProductoController {

    private static Logger LOG = LoggerFactory.getLogger(ClienteController.class);

    @Autowired
    private ProductoService servicioProducto;

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProducto(@PathVariable("id")int id){
        LOG.info("Buscando Producto {}",id);
        Optional<Producto> producto = this.servicioProducto.getProductoById(id);
        if (!producto.isPresent()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(producto.get(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Producto> addProducto(@RequestBody Producto p){
        Boolean ok = this.servicioProducto.addProducto(p);
        if(!ok){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(p,HttpStatus.OK);
    }

    @GetMapping("")
    public List<Producto> getAll(){
        return this.servicioProducto.getProductos();
    }
}
