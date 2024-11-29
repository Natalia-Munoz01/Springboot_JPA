package edu.uptc.example.service;

import edu.uptc.example.entityes.Product;
import edu.uptc.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Obtener todos los productos
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    // Obtener un producto por ID
    public Product getById(Long id) {
        return productRepository.findById(id).orElse(null); 
    }

    // Guardar un nuevo producto
    public Product save(Product product) {
        return productRepository.save(product);
    }

    // Actualizar un producto existente
    public Product update(Long id, Product productDetails) {
        return productRepository.findById(id).map(existingProduct -> {
            existingProduct.setName(productDetails.getName());  
            existingProduct.setPrice(productDetails.getPrice()); 
            existingProduct.setStock(productDetails.getStock());  
            return productRepository.save(existingProduct);
        }).orElse(null);
    }

    // Eliminar un producto por ID
    public boolean delete(Long id) {
        return productRepository.findById(id).map(product -> {
            productRepository.delete(product); 
            return true;  
        }).orElse(false);  
    }
}
