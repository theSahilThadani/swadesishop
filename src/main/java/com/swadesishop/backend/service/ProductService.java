package com.swadesishop.backend.service;

import com.swadesishop.backend.models.Product;

import java.util.List;

public interface ProductService {
    //this is interface we are using to fetch data and implement all methods in service logic.
    Product getSingleProduct(Long id);
    List<Product> getAllProducts();
    Product creatProduct(Product product);

}
