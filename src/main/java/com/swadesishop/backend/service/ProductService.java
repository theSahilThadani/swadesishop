package com.swadesishop.backend.service;

import com.swadesishop.backend.exceptions.ProductNotFoundException;
import com.swadesishop.backend.models.Product;

import java.util.List;

public interface ProductService {
    //this is interface we are using to fetch data and implement all methods in service logic.
    Product getSingleProduct(Long id) throws ProductNotFoundException;
    List<Product> getAllProducts();
    Product creatProduct(Long id, String title, String description, Double price, String imageUrl, String Category);
    void updateProduct(Long id, String title, String description, Double price, String imageUrl, String Category);
    void deleteProduct(Long id);
}
