package com.swadesishop.backend.service;

import com.swadesishop.backend.dto.FakeStoreProductDto;
import com.swadesishop.backend.exceptions.ProductNotFoundException;
import com.swadesishop.backend.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
@Service //this tells spring that this is special class and add dependency for it.
public class FakeStoreProductService implements ProductService{
    //here we will implement fake-store logic.

    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }


    @Override
    public Product getSingleProduct(Long id) throws ProductNotFoundException {
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products/"+id, FakeStoreProductDto.class);
        if(fakeStoreProductDto == null){
            throw new ProductNotFoundException("Product not found check id or enter valid id");
        }
        return fakeStoreProductDto.getProduct();
    }

    @Override
    public List<Product> getAllProducts() {

        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForObject("https://fakestoreapi.com/products",FakeStoreProductDto[].class);
        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDto i : fakeStoreProductDtos){
            products.add(i.getProduct());
        }
        return products;
    }

    @Override
    public Product creatProduct(Long id, String title, String description, Double price, String imageUrl, String Category) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(id);
        fakeStoreProductDto.setTitle(title);
        fakeStoreProductDto.setDescription(description);
        fakeStoreProductDto.setCategory(Category);
        fakeStoreProductDto.setPrice(price);
        fakeStoreProductDto.setImage(imageUrl);
        FakeStoreProductDto res = restTemplate.postForObject("https://fakestoreapi.com/products",fakeStoreProductDto, FakeStoreProductDto.class);
        return res.getProduct();
    }

    @Override
    public void updateProduct(Long id, String title, String description, Double price, String imageUrl, String Category) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(title);
        fakeStoreProductDto.setImage(imageUrl);
        fakeStoreProductDto.setCategory(Category);
        fakeStoreProductDto.setPrice(price);
        fakeStoreProductDto.setDescription(description);

        restTemplate.put("https://fakestoreapi.com/products/"+id,fakeStoreProductDto);
        System.out.print("Update is working");
    }

    @Override
    public void deleteProduct( Long id) {
        restTemplate.delete("https://fakestoreapi.com/products/"+id);
        System.out.print("Delete is working");
    }
}
