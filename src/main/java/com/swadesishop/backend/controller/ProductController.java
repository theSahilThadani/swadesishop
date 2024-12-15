package com.swadesishop.backend.controller;

import com.swadesishop.backend.models.Product;
import com.swadesishop.backend.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //by this spring will know this is controller for api
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
        return productService.creatProduct(product.getId(), product.getTitle(), product.getDescription(), product.getPrice(), product.getImageUrl(), product.getCategory().getTitle());
    }

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    //to get the product
    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable("id") Long id){
        return productService.getSingleProduct(id);
    }

    @PutMapping("/products/{id}")
    public void updateProduct(@PathVariable Long id, @RequestBody Product product){
        productService.updateProduct(id,product.getTitle(),product.getDescription(),product.getPrice(),product.getImageUrl(),product.getCategory().getTitle());

    }
    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }
}
