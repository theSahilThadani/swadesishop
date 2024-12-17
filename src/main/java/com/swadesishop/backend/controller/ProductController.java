package com.swadesishop.backend.controller;

import com.swadesishop.backend.dto.ErrorDto;
import com.swadesishop.backend.exceptions.ProductNotFoundException;
import com.swadesishop.backend.models.Product;
import com.swadesishop.backend.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //by this spring will know this is controller for api
public class ProductController {
    //create api around crud

    //now implementing ProductService dependency to this controller.
    private final ProductService productService;
    public ProductController(ProductService productService){

        this.productService = productService;
    }


    //making this route as post request here two methods
    //@RequestMapping(value = "/Products", method = RequestMethod.POST) this will map post request to /products routes
    @PostMapping("/products")
    public ResponseEntity<Product> CreateProduct(@RequestBody Product product){
        ResponseEntity<Product> res = new ResponseEntity<>(productService.creatProduct(product.getId(), product.getTitle(), product.getDescription(), product.getPrice(), product.getImageUrl(), product.getCategory().getTitle()),HttpStatus.CREATED);
        return res;
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        ResponseEntity<List<Product>> res = new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
        return res;
    }

    //to get the product
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
        //if id not in correct format then it will throw exception
        if(id < 0){
            throw new ProductNotFoundException("Product not found check id or enter valid id");
        }

        ResponseEntity<Product> product = new ResponseEntity<>(productService.getSingleProduct(id), HttpStatus.OK);
        return product;
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody Product product){
        productService.updateProduct(id,product.getTitle(),product.getDescription(),product.getPrice(),product.getImageUrl(),product.getCategory().getTitle());
        ResponseEntity<String> res = new ResponseEntity<>("Product updated successfully", HttpStatus.ACCEPTED);
        return res;
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return new ResponseEntity<>("Product Deleted successfully", HttpStatus.OK);
    }

    //here we are adding method for error message
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDto> handleProductNotFoundException(Exception e){
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(e.getMessage());
        ResponseEntity<ErrorDto> er = new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
        return er;
    }
}
