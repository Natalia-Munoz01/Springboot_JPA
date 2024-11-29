package edu.uptc.example.service;

import edu.uptc.example.entityes.Sale;
import edu.uptc.example.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    // Obtener todas las ventas
    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    // Obtener una venta por ID
    public Sale getSaleById(Long id) {
        Optional<Sale> sale = saleRepository.findById(id);
        return sale.orElse(null); // Si no encuentra la venta, retorna null
    }

    // Guardar una nueva venta
    public Sale saveSale(Sale sale) {
        return saleRepository.save(sale);
    }

    // Actualizar una venta (si fuera necesario)
    public Sale updateSale(Long id, Sale saleDetails) {
        return saleRepository.findById(id).map(existingSale -> {
            existingSale.setCustomer(saleDetails.getCustomer());  // Actualiza el cliente
            existingSale.setProducts(saleDetails.getProducts());  // Actualiza los productos
            existingSale.setDate(saleDetails.getDate());  // Actualiza la fecha de la venta
            return saleRepository.save(existingSale);
        }).orElse(null); // Si no encuentra la venta, retorna null
    }

    // Eliminar una venta por ID
    public boolean deleteSale(Long id) {
        return saleRepository.findById(id).map(sale -> {
            saleRepository.delete(sale);  // Elimina la venta
            return true;  // Retorna true si se eliminó con éxito
        }).orElse(false);  // Retorna false si no encuentra la venta
    }
}
