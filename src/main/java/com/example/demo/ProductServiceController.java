package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ProductServiceController {
    private static Map<String,Product> productRepo = new HashMap<>();
    static {
        Product honey = new Product();
        honey.setId("1");
        honey.setName("Honey");
        productRepo.put(honey.getId(), honey);

        Product almond = new Product();
        almond.setId("2");
        almond.setName("Almond");
        productRepo.put(almond.getId(), almond);
    }

    @RequestMapping(value = "/products")
    public ResponseEntity<Object> getProduct(){
        return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
    }

    @PostMapping("/products")
    public ResponseEntity<Object> createProduct(@RequestBody Product product){
        productRepo.put(product.getId(),product);
        return new ResponseEntity<>("Product is created successfully",HttpStatus.CREATED);
    }

    @RequestMapping(value = "products/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateProduct(
            @PathVariable String id,
            @RequestBody Product product){
        productRepo.remove(id);
        product.setId(id);
        productRepo.put(id,product);
        return new ResponseEntity<>("Product updated successfully",HttpStatus.OK);

    }
}
