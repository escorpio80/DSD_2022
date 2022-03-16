package com.upc.curso.repositorio;

import com.upc.curso.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductoRepositorio extends JpaRepository<Producto, Long> {
    @Query("SELECT p FROM Producto p WHERE p.descripcion like '%Lec%'")
    public List<Producto> obtenerReporte();
    @Query("SELECT p FROM Producto p WHERE p.precio<=:xprecio and p.descripcion=:xdescripcion")
    public Producto buscarPrecioDescripcion(@Param("xprecio") double xprecio, @Param("xdescripcion") String xdescripcion);
    public Producto findByPrecioAndDescripcion(double precio, String descripcion);
}
