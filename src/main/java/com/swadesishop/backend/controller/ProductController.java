package com.swadesishop.backend.controller;

import com.swadesishop.backend.models.Product;
import com.swadesishop.backend.service.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController //by this spring will know this is controller for apigit a
public class ProductController {
    //create api around crud

    //now implementing ProductService dependency to this controller.
    private ProductService productService;
    public ProductController(ProductService productService){
        this.productService = productService;
    }


    //making this route as post request here two methods
    //@RequestMapping(value = "/Products", method = RequestMethod.POST) this will map post request to /products routes
    @PostMapping("/products")
    public Product CreateProduct(@RequestBody Product product){
        Product p = productService.creatProduct(product.getId(), product.getTitle(), product.getDescription(), product.getPrice(), product.getImageUrl(), product.getCategory().getTitle());
        return p;
    }
    //to get the product
    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable("id") Long id){
        Product p = productService.getSingleProduct(id);
        return p;
    }

    public void updateProduct(Long id){

    }

    public void deleteProduct(Long id){

    }
}
