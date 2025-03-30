// src/test/java/com/example/productos/service/ProductoServiceTest.java
package com.example.productos.service;

import com.example.productos.model.Producto;
import com.example.productos.repository.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    private Producto producto;

    @BeforeEach
    void setUp() {
        producto = Producto.builder()
                .id("1")
                .nombre("Laptop")
                .precio(3200.0)
                .build();
    }

    @Test
    void listarProductos() {
        when(productoRepository.findAll()).thenReturn(Flux.just(producto));

        Flux<Producto> result = productoService.listarproductos();

        StepVerifier.create(result)
                .expectNext(producto)
                .verifyComplete();
        verify(productoRepository).findAll();
    }

    @Test
    void obtenerProductoPorId() {
        when(productoRepository.findById("1")).thenReturn(Mono.just(producto));

        Mono<Producto> result = productoService.buscarPorId("1");

        StepVerifier.create(result)
                .expectNext(producto)
                .verifyComplete();
        verify(productoRepository).findById("1");
    }

    @Test
    void crearProducto() {
        when(productoRepository.save(producto)).thenReturn(Mono.just(producto));

        Mono<Producto> result = productoService.guardar(producto);

        StepVerifier.create(result)
                .expectNext(producto)
                .verifyComplete();
        verify(productoRepository).save(producto);
    }

    @Test
    void actualizarProducto() {
        Producto productoActualizado = Producto.builder()
                .id("1")
                .nombre("Laptop Pro")
                .precio(3500.0)
                .build();

        when(productoRepository.findById("1")).thenReturn(Mono.just(producto));
        when(productoRepository.save(any(Producto.class))).thenReturn(Mono.just(productoActualizado));

        Mono<Producto> result = productoService.actualizar("1", productoActualizado);

        StepVerifier.create(result)
                .expectNextMatches(p -> p.getNombre().equals("Laptop Pro"))
                .verifyComplete();
        verify(productoRepository).findById("1");
        verify(productoRepository).save(any(Producto.class));
    }

    @Test
    void eliminarProducto() {
        when(productoRepository.deleteById("1")).thenReturn(Mono.empty());

        Mono<Void> result = productoService.eliminar("1");

        StepVerifier.create(result)
                .verifyComplete();
        verify(productoRepository).deleteById("1");
    }
}