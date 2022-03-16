package com.upc.curso;

import com.upc.curso.entidades.Producto;
import com.upc.curso.negocio.ProductoNegocio;
import com.upc.curso.repositorio.ProductoRepositorio;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MockTest{
    @Autowired
    private ProductoNegocio productoNegocio;

    @MockBean
    private ProductoRepositorio productoRepositorio;


    @Test
    public void registrarTest(){
        Producto producto = new Producto(1L, "Leche", 4,100);
        when(productoRepositorio.save(producto)).thenReturn(producto);
        Assert.assertEquals(producto,productoNegocio.registrar(producto));
    }

    @Test
    public void obtenerProductos(){
        when(productoRepositorio.obtenerReporte()).thenReturn(Stream.of(
                        new Producto(1L, "Leche", 4,100)).
                collect(Collectors.toList()));
        Assert.assertEquals(1,productoNegocio.obtenerReporte().size());
    }
    @Test
    public void obtenerIGV(){
        Producto producto = new Producto(1L, "Leche", 4,100);
        when(productoRepositorio.findById(1L)).thenReturn(Optional.of(producto));
        try {
            Assert.assertEquals(0.72,productoNegocio.calcularIGV(1L),0.01);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
