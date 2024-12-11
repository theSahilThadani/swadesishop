package com.swadesishop.backend.service;

import com.swadesishop.backend.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;
@Service //this tells spring that this is special class and add dependency for it.
public class FakeStoreProductService implements ProductService{
    //here we will implement fake-store logic.
    @Override
    public Product getSingleProduct(Long id) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product creatProduct(Product product) {
        return null;
    }
}
