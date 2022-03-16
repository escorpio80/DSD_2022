package com.upc.curso.negocio;

import com.upc.curso.entidades.Producto;
import com.upc.curso.repositorio.ProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //no agnostica
public class ProductoNegocio {
    @Autowired
    private ProductoRepositorio productoRepositorio;

    public Producto registrar(Producto producto){
       return productoRepositorio.save(producto);
    }
    public Producto buscar(Long codigo) throws Exception {
        return productoRepositorio.findById(codigo).orElseThrow(() -> new Exception("No se encontró entidad"));
    }
    public List<Producto> listado(){
        return (List<Producto>)productoRepositorio.findAll();
    }
    public double calcularIGV(Long codigo) throws Exception {
        Producto producto;
        producto = buscar(codigo);
        if (producto!=null){
            return 0.18*producto.getPrecio();
        }
        return 0;
    }
    public List<Producto> obtenerReporte(){
        return productoRepositorio.obtenerReporte();
    }
    public Producto buscarPrecioDescripcion(double precio, String descripcion){
       return productoRepositorio.buscarPrecioDescripcion(precio,descripcion);
       // return productoRepositorio.findByPrecioAndDescripcion(precio, descripcion);
    }

    public Producto actualizarProducto(Producto producto) throws Exception {
        Producto productoT = productoRepositorio.findById(producto.getCodigo()).orElseThrow(() -> new Exception("No se encontró entidad"));
        productoT=producto;
        return productoRepositorio.save(productoT);
    }

}
