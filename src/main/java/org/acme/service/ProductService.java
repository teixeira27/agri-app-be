package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.repository.ProductRepository;

@ApplicationScoped
public class ProductService {

    @Inject
    ProductRepository productRepository;


    public String deleteById(Integer id) {
        productRepository.deleteById(id);
        return ("Product deleted successfully.");
    }

}
