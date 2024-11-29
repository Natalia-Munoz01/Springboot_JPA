package edu.uptc.example.controller;

import edu.uptc.example.entityes.Sale;
import edu.uptc.example.service.SaleService;
import edu.uptc.handling.ResponseHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales")
public class SaleController {

    @Autowired
    private SaleService saleService;

    // Obtener todas las ventas
    @GetMapping
    public ResponseEntity<Object> getAllSales() {
        try {
            List<Sale> result = saleService.getAllSales();
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    // Obtener una venta por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getSaleById(@PathVariable Long id) {
        try {
            Sale result = saleService.getSaleById(id);
            if (result == null) {
                return ResponseHandler.generateResponse("Sale not found", HttpStatus.NOT_FOUND, null);
            }
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    // Crear una nueva venta
    @PostMapping
    public ResponseEntity<Object> createSale(@RequestBody Sale sale) {
        try {
            // Usamos el método saveSale para guardar la venta
            Sale createdSale = saleService.saveSale(sale);  
            return ResponseHandler.generateResponse("Sale created successfully", HttpStatus.CREATED, createdSale);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    // Actualizar una venta existente
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateSale(@PathVariable Long id, @RequestBody Sale sale) {
        try {
            Sale updatedSale = saleService.updateSale(id, sale);
            if (updatedSale == null) {
                return ResponseHandler.generateResponse("Sale not found", HttpStatus.NOT_FOUND, null);
            }
            return ResponseHandler.generateResponse("Sale updated successfully", HttpStatus.OK, updatedSale);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    // Eliminar una venta por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSale(@PathVariable Long id) {
        try {
            boolean deleted = saleService.deleteSale(id);
            if (!deleted) {
                return ResponseHandler.generateResponse("Sale not found", HttpStatus.NOT_FOUND, null);
            }
            return ResponseHandler.generateResponse("Sale deleted successfully", HttpStatus.OK, null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
