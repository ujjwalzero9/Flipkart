package com.example.flipkart.service;

import com.example.flipkart.model.*;
import com.example.flipkart.repository.CatalogRepository;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Strategy‐based filtering and sorting of products.
 */
public class SearchService {
    private final CatalogRepository catalog = CatalogRepository.getInstance();

    /** 
     * Finds products matching filters and then orders them.
     */
    public List<Product> search(
        Set<Category> categories,
        Set<String> brands,
        Double priceMin,
        Double priceMax,
        Comparator<Product> sorter
    ) {
        Stream<Product> stream = catalog.findAll().stream()
            .filter(byCategory(categories))
            .filter(byBrand(brands))
            .filter(byPriceRange(priceMin, priceMax));

        if (sorter != null) {
            return stream.sorted(sorter).collect(Collectors.toList());
        } else {
            return stream.collect(Collectors.toList());
        }
    }

    private Predicate<Product> byCategory(Set<Category> cats) {
        return p -> cats == null || cats.isEmpty() || cats.contains(p.getCategory());
    }

    private Predicate<Product> byBrand(Set<String> brs) {
        return p -> brs == null || brs.isEmpty() || brs.contains(p.getBrand());
    }

    private Predicate<Product> byPriceRange(Double min, Double max) {
        return p -> (min == null || p.getPrice() >= min)
                 && (max == null || p.getPrice() <= max);
    }
}
