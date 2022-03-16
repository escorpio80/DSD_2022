package com.upc.curso;

import com.upc.curso.entidades.Producto;
import com.upc.curso.negocio.ProductoNegocio;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CursoApplicationTests {

	@Autowired
	private ProductoNegocio productoNegocio;


	public void contextLoads() {
	}

	//@Test
	public void probarRegistro(){
		Producto producto = new Producto();
		producto.setDescripcion("Leche");
		producto.setPrecio(5.00);
		producto.setStock(50);
		Producto p;
		p = productoNegocio.registrar(producto);
		Assert.assertNotNull(p);
	}
	//@Test
	public void probarIGV(){
		double resultado;
		try {
			resultado = productoNegocio.calcularIGV(1L);
			System.out.println("IGV:" + resultado);
			Assert.assertEquals(0.54, resultado, 0.00);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	@Test
	public void reporte(){
		List<Producto> productos;
		productos = productoNegocio.obtenerReporte();
		for(Producto p:productos){
			System.out.println(p.getCodigo()+ " " + p.getDescripcion() + "  " + p.getPrecio());
		}

	}
	//@Test
	public void buscarProducto(){
		Producto producto;
		try {
			producto = productoNegocio.buscar(7L);
			System.out.println(producto.getDescripcion()+ " " + producto.getStock());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	//@Test
	public void parametros(){
		Producto producto;
		producto = productoNegocio.buscarPrecioDescripcion(5,"Leche");
		System.out.println(producto.getDescripcion()+ " " + producto.getStock());
	}
	//@Test
	public void actualizarTest(){
		Producto producto = new Producto(1L,"Leche",5,50);
		try {
			producto = productoNegocio.actualizarProducto(producto);
			System.out.println(producto.getDescripcion()+ " " + producto.getStock());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
