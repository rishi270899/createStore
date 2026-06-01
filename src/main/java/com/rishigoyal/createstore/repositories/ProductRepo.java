package com.rishigoyal.createstore.repositories;

import com.rishigoyal.createstore.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {

}
