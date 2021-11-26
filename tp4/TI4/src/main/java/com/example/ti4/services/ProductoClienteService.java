package com.example.ti4.services;

import com.example.ti4.controller.ClienteController;
import com.example.ti4.controller.dto.ClienteComprasReporte;
import com.example.ti4.controller.dto.ProductoCantVendido;
import com.example.ti4.controller.dto.ProductoClienteReporte;
import com.example.ti4.entities.Cliente;
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

    @Autowired
    private ClienteService clienteService;

    public ProductoClienteService(ProductoClienteRepository productoClienteRepository, ClienteService clienteService) {
        this.productoClienteRepository = productoClienteRepository;
        this.clienteService = clienteService;
    }

    public List<Cliente> findAllClientes() {
        return clienteService.findAll();
    }

    /**
     * Guarda en la base de datos el producto vendido con la cantidad, comprueba que no se vendan 3 productos por persona en el día
     * @param productoCliente
     * @return producto vendido
     */
    public ProductoCliente save(ProductoCliente productoCliente) {
        List<ProductoCliente> allByDate = productoClienteRepository.findAllByDateAndCliente(LocalDate.now(), productoCliente.getCliente());
        int reduce = allByDate.stream()
                .map(pc -> pc.getCantidad())
                .reduce((x, y) -> x + y)
                .orElse(0);
        if (reduce + productoCliente.getCantidad() > 3) {
            LOG.error("El cliente no puede cargar mas de 3 productos por dia");
            throw new ClienteMasDe3ProductosException();
        }
        return productoClienteRepository.save(productoCliente);
    }

    /**
     * Genera un reporte de las ventas que se hicieron en el día
     * @return  Lista de los productos por clientes vendidos
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
     * Agarra el producto que más se vendio hasta el momento
     * @return la cantidad del producto y el nombre del producto
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
            } else {
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

    /**
     * Muestra todos los clientes y el monto total de sus compras
     * @return Devuelve una lista con DTOs que almacenan a cada cliente y su monto de compras
     */
    public List<ClienteComprasReporte> getProductoClienteReport() {
        List<ProductoCliente> all = this.productoClienteRepository.findAll();
        List<Cliente> clientesTotales = this.findAllClientes();
        List<ClienteComprasReporte> clientesMonto = new ArrayList<>();
        for (Cliente c: clientesTotales){
            ClienteComprasReporte ccr = new ClienteComprasReporte();
            ccr.setCliente(c);
            for(ProductoCliente p: all) {
                if (Objects.equals(p.getCliente(),ccr.getCliente())){
                    ccr.setTotal(ccr.getTotal()+p.getProducto().getPrecio()*p.getCantidad());
                }
            }
            clientesMonto.add(ccr);
        }

        return clientesMonto;
    }


}
