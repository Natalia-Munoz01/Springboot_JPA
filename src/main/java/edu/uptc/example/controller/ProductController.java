package edu.uptc.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.uptc.example.entityes.Product;
import edu.uptc.example.service.ProductService;
import edu.uptc.handling.ResponseHandler;

@RestController
@RequestMapping("/products") 
public class ProductController {

    @Autowired
    private ProductService productService; 

    // Obtener todos los productos
    @GetMapping
    public ResponseEntity<Object> getProducts() {
        try {
            List<Product> result = productService.getAll();
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    // Obtener un producto por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable Long id) {
        try {
            Product result = productService.getById(id);
            if (result == null) {
                return ResponseHandler.generateResponse("Product not found", HttpStatus.NOT_FOUND, null);
            }
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    // Guardar un nuevo producto
    @PostMapping
    public ResponseEntity<Object> save(@RequestBody Product product) {
        try {
            Product savedProduct = productService.save(product);
            return ResponseHandler.generateResponse("Product created successfully", HttpStatus.CREATED, savedProduct);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    // Actualizar un producto existente
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Product product) {
        try {
            Product updatedProduct = productService.update(id, product);
            if (updatedProduct == null) {
                return ResponseHandler.generateResponse("Product not found", HttpStatus.NOT_FOUND, null);
            }
            return ResponseHandler.generateResponse("Product updated successfully", HttpStatus.OK, updatedProduct);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    // Eliminar un producto por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        try {
            boolean deleted = productService.delete(id);
            if (!deleted) {
                return ResponseHandler.generateResponse("Product not found", HttpStatus.NOT_FOUND, null);
            }
            return ResponseHandler.generateResponse("Product deleted successfully", HttpStatus.OK, null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}

    
