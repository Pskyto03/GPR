package com.example.productos.repository;
import com.example.productos.model.Producto;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
public interface ProductoRepository extends ReactiveCrudRepository<Producto, String> 
{
}