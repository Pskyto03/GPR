
package com.example.productos.controller;

import com.example.productos.model.Producto;
import com.example.productos.repository.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Collections;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class ProductoIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private ProductoRepository productoRepository;

    private Producto producto;

    @BeforeEach
    void setUp() {
        productoRepository.deleteAll().block();
        producto = Producto.builder()
                .nombre("Teclado")
                .precio(150.0)
                .build();
    }

    @Test
    @WithMockUser(username = "usuario", password = "clave123", roles = "USER")
    void crearProducto() {
        webTestClient.post().uri("/api/productos")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(producto)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.nombre").isEqualTo("Teclado")
                .jsonPath("$.precio").isEqualTo(150.0);
    }

    @Test
    @WithMockUser(username = "usuario", password = "clave123", roles = "USER")
    void obtenerProductoPorId() {
        Producto productoGuardado = productoRepository.save(producto).block();

        webTestClient.get().uri("/api/productos/{id}", Collections.singletonMap("id", productoGuardado.getId()))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(productoGuardado.getId())
                .jsonPath("$.nombre").isEqualTo("Teclado");
    }

    @Test
    @WithMockUser(username = "usuario", password = "clave123", roles = "USER")
    void eliminarProducto() {
        Producto productoGuardado = productoRepository.save(producto).block();

        webTestClient.delete().uri("/api/productos/{id}", Collections.singletonMap("id", productoGuardado.getId()))
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    void accesoNoAutorizado() {
        webTestClient.get().uri("/api/productos")
                .exchange()
                .expectStatus().isUnauthorized();
    }
}