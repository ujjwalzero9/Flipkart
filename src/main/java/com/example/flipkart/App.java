package com.example.flipkart;

import com.example.flipkart.model.*;
import com.example.flipkart.repository.*;
import com.example.flipkart.service.*;
import com.example.flipkart.util.*;
import java.util.*;

/**
 * Application entry point that wires everything together and demos workflows.
 */
public class App {
    public static void main(String[] args) {
        // 1) Setup repositories & services
        CatalogRepository catalog = CatalogRepository.getInstance();
        OrderLogRepository logRepo = new OrderLogRepository();
        logRepo.register((order, status) ->
            System.out.println("LOG: Order " + order.getOrderId() + " -> " + status));

        SearchService  searchSvc  = new SearchService();
        CartService    cartSvc    = new CartService(raw -> raw * 0.95);   // 5% discount
        OrderService   orderSvc   = new OrderService(logRepo);

        // 2) Seed catalog
        catalog.save(new Product.Builder("P1","Amul Milk", Category.MILK,"Amul",100).stock(20).build());
        catalog.save(new Product.Builder("P2","Nestle Milk",Category.MILK,"Nestle", 60).stock(15).build());
        catalog.save(new Product.Builder("P3","Amul Curd", Category.CURD,"Amul", 50).stock( 5).build());
        catalog.save(new Product.Builder("P4","Nestle Curd",Category.CURD,"Nestle",90).stock(10).build());

        // 3) Search & print
        System.out.println("=== All by price asc ===");
        searchSvc.search(null,null,null,null,
            Comparator.comparingDouble(Product::getPrice))
            .forEach(System.out::println);

        System.out.println("\n=== Brand=Nestle ===");
        searchSvc.search(null,Set.of("Nestle"),null,null,
            Comparator.comparingDouble(Product::getPrice))
            .forEach(System.out::println);

        // 4) Create cart & place order
        User u = new User("U1","Ujjwal Kumar");
        List<CartItem> cart = cartSvc.createCart();
        catalog.findById("P1").ifPresent(p -> cart.add(new CartItem(p,2)));
        catalog.findById("P3").ifPresent(p -> cart.add(new CartItem(p,1)));

        double rawTotal = cartSvc.calculateTotal(cart);
        System.out.println("\nCart total after discount: " + rawTotal);

        Order order = orderSvc.placeOrder(u,cart);
        System.out.println("\nShipped: " + orderSvc.shipOrder(order,"123 Main St"));
        orderSvc.deliverOrder(order);
    }
}
