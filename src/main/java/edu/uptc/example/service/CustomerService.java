package edu.uptc.example.service;

import edu.uptc.example.entityes.Customer;
import edu.uptc.example.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerService() {
    }

    // Obtener todos los clientes
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    // Obtener un cliente por su ID
    public Customer getCustomer(Long id) {
        Optional<Customer> opt = customerRepository.findById(id);
        return opt.orElse(null);
    }

    // Guardar un nuevo cliente
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    // Actualizar un cliente existente
    public Customer update(Long id, Customer customerDetails) {
        Optional<Customer> opt = customerRepository.findById(id);
        if (opt.isPresent()) {
            Customer existingCustomer = opt.get();
            existingCustomer.setName(customerDetails.getName());
            existingCustomer.setEmail(customerDetails.getEmail());
            return customerRepository.save(existingCustomer);
        }
        return null;
    }

    // Eliminar un cliente
    public boolean delete(Long id) {
        Optional<Customer> opt = customerRepository.findById(id);
        if (opt.isPresent()) {
            customerRepository.delete(opt.get());
            return true;
        }
        return false;
    }
}
