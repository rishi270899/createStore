package com.rishigoyal.createstore.services;

import com.rishigoyal.createstore.dto.OrderItemRequest;
import com.rishigoyal.createstore.dto.OrderRequest;
import com.rishigoyal.createstore.entities.Order;
import com.rishigoyal.createstore.entities.OrderItems;
import com.rishigoyal.createstore.entities.Product;
import com.rishigoyal.createstore.repositories.OrderRepo;
import com.rishigoyal.createstore.repositories.ProductRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
@RequiredArgsConstructor
public class OrderService {
    private OrderRepo orderRepo;
    private ProductService productService;
    private ProductRepo productRepo;

    @Transactional
    public Order createOrder(OrderRequest orderRequest) {

        List<OrderItems> orderItems = new ArrayList<>();
        BigDecimal totalPrice = BigDecimal.ZERO;

        Order order = new Order();
        order.setCustomerName(orderRequest.getCustomerName());
        order.setCustomerEmail(orderRequest.getCustomerEmail());
        order.setStatus("CONFIRMED");

        for(OrderItemRequest itemRequest : orderRequest.getItems()){
                    Product product = productRepo.findById(
                            itemRequest.getProductId()
                    ).orElseThrow(() -> new RuntimeException("Product not found with id " + itemRequest.getProductId()));

            // check the product stock
            if(product.getStockQuantity() < itemRequest.getQuantity()){
                throw new RuntimeException("Not enough stock for particular stock");
            }

            // Calulate total price
            BigDecimal priceOfItem = product.getPrice()
                    .multiply(BigDecimal.valueOf(itemRequest.getQuantity()));
            totalPrice = totalPrice.add(priceOfItem);


            // Update the product table with latest stock quantity
            product.setStockQuantity(
                    product.getStockQuantity() - itemRequest.getQuantity()
            );

            productRepo.save(product);

            // Builder patter to make obj
            OrderItems orderItem = OrderItems.builder()
                    .order(order)
                    .product(product)
                    .quantity(itemRequest.getQuantity())
                    .priceAtPurchase(product.getPrice())
                    .build();


            orderItems.add(orderItem);

        }

        order.setTotalPrice(totalPrice);
        order.setOrderItems(orderItems);




        return null;
    }


}
