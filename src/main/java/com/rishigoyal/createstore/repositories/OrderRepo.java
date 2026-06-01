package com.rishigoyal.createstore.repositories;

import com.rishigoyal.createstore.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Long> {
}
