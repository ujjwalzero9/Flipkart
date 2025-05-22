package com.example.flipkart.repository;

import com.example.flipkart.model.Product;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * In‐memory catalog; Singleton ensures one shared store.
 */
public class CatalogRepository {
    private static final CatalogRepository INSTANCE = new CatalogRepository();
    private final Map<String, Product> products = new ConcurrentHashMap<>();

    private CatalogRepository() {}

    /** Get the single instance. */
    public static CatalogRepository getInstance() {
        return INSTANCE;
    }

    public void save(Product p) {
        products.put(p.getId(), p);
    }

    public Optional<Product> findById(String id) {
        return Optional.ofNullable(products.get(id));
    }

    public Collection<Product> findAll() {
        return products.values();
    }
}
