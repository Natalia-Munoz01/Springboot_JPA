package edu.uptc.example.controller;

import edu.uptc.example.entityes.Customer;
import edu.uptc.example.service.CustomerService;
import edu.uptc.handling.ResponseHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers") 
public class CustomerController {

    @Autowired
    private CustomerService customerService; 

    // Obtener todos los clientes
    @GetMapping
    public ResponseEntity<Object> getCustomers() {
        try {
            List<Customer> result = customerService.getCustomers();
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    // Obtener un cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getCustomer(@PathVariable("id") long id) {
        try {
            Customer result = customerService.getCustomer(id);
            if (result == null) {
                return ResponseHandler.generateResponse("Customer not found", HttpStatus.NOT_FOUND, null);
            }
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    // Crear un nuevo cliente
    @PostMapping
    public ResponseEntity<Object> save(@RequestBody Customer customer) {
        try {
            Customer result = customerService.save(customer);
            return ResponseHandler.generateResponse("Customer created successfully", HttpStatus.CREATED, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    // Actualizar un cliente existente
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") long id, @RequestBody Customer customer) {
        try {
            Customer result = customerService.update(id, customer);
            if (result == null) {
                return ResponseHandler.generateResponse("Customer not found", HttpStatus.NOT_FOUND, null);
            }
            return ResponseHandler.generateResponse("Customer updated successfully", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    // Eliminar un cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") long id) {
        try {
            boolean deleted = customerService.delete(id);
            if (!deleted) {
                return ResponseHandler.generateResponse("Customer not found", HttpStatus.NOT_FOUND, null);
            }
            return ResponseHandler.generateResponse("Customer deleted successfully", HttpStatus.OK, null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
