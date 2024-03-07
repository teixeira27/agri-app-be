package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import org.acme.domain.Product;
import org.acme.dto.ProductDTO;
import org.acme.repository.ProductRepository;
import java.util.List;

@ApplicationScoped
public class ProductService {

    @Inject
    ProductRepository productRepository;

    public String createProduct (ProductDTO productDTO){
        productRepository.save(Product.builder()
                .name(productDTO.getName())
                .safetyPeriod(productDTO.getSafetyPeriod())
                .description(productDTO.getDescription())
                .type(productDTO.getType())
                .build());
        return "Product saved successfully.";
    }

    public ProductDTO findById (Integer id){
        final Product product = productRepository.findById(id)
                .orElseThrow( ()-> new EntityNotFoundException("Product not found."));
        return ProductDTO.builder()
                .productID(product.getProductID())
                .name(product.getName())
                .safetyPeriod(product.getSafetyPeriod())
                .description(product.getDescription())
                .type(product.getType())
                .build();
    }

    public List<ProductDTO> listAllFields(){
        return productRepository.findAll()
                .stream()
                .map(product -> ProductDTO
                        .builder()
                        .productID(product.getProductID())
                        .name(product.getName())
                        .safetyPeriod(product.getSafetyPeriod())
                        .description(product.getDescription())
                        .type(product.getType())
                        .build())
                .toList();
    }

    public String deleteById (Integer id){
        productRepository.deleteById(id);
        return ("Product deleted successfully.");
    }

    public String save (){
        productRepository.save(Product.builder().name("quimico").safetyPeriod("sss").description("bbb").type("zzz").build());
        productRepository.save(Product.builder().name("spray").safetyPeriod("sss").description("bbb").type("zzz").build());
        productRepository.save(Product.builder().name("agua").safetyPeriod("sss").description("bbb").type("zzz").build());
        productRepository.save(Product.builder().name("papel").safetyPeriod("sss").description("bbb").type("zzz").build());
        return "Products saved successfully";
    }
}
