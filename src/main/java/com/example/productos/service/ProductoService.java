package com.example.productos.service;
import com.example.productos.model.Producto;
import com.example.productos.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
@RequiredArgsConstructor
public class ProductoService {
private final ProductoRepository repository;
public Flux<Producto> listarproductos() {
return repository.findAll();
}
public Mono<Producto> guardar(Producto producto) {
    return repository.save(producto);
    }
    public Mono<Producto> buscarPorId(String id) {
    return repository.findById(id);
    }
    public Mono<Void> eliminar(String id) {
    return repository.deleteById(id);
    }
    public Mono<Producto> actualizar(String id, Producto producto) {
    return repository.findById(id)
    .flatMap(p -> {
    p.setNombre(producto.getNombre());
    p.setPrecio(producto.getPrecio());
    return repository.save(p);
    });
    }
    }