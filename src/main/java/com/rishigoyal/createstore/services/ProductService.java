package com.rishigoyal.createstore.services;

import com.rishigoyal.createstore.entities.Product;
import com.rishigoyal.createstore.repositories.ProductRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepo productRepo;


    public Product createProduct(Product product) {
        return productRepo.save(product);
    }

    public Product updateProduct( Long id, Product product) {
        Product existingProduct = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("product not found " + id));

        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setStockQuantity(product.getStockQuantity());
        return productRepo.save(existingProduct);
    }


    public List<Product> getProducts(){
        return productRepo.findAll();
    }


    public Product getProductById( Long id){
        return productRepo.findById(id)
                          .orElseThrow(() -> new RuntimeException("product not found " + id));
    }


    public void deleteProduct( Long id){
      productRepo.deleteById(id);
    }

}
