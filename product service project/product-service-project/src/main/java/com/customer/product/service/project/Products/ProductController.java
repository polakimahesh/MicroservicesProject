package com.customer.product.service.project.Products;

import com.customer.product.service.project.dto.GetAvailabilityDto;
import com.customer.product.service.project.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    //get all the products
    @GetMapping("")
    public ResponseEntity<Object> getAllProducts(){
        var product=productService.getAllProducts();
        if(Boolean.TRUE.equals(product.get("isSuccess"))){
            return ResponseEntity.ok(product.get("message"));
        }else
            return ResponseEntity.badRequest().body(product.get("message"));
    }
    //create the product
    @PostMapping("/register-product")
    public ResponseEntity<Object> createProduct(@RequestBody @Valid ProductDto productDto){
        var product=productService.createProduct(productDto);
        if(Boolean.TRUE.equals(product.get("isSuccess"))){
            return ResponseEntity.ok(product.get("message"));
        }else
            return ResponseEntity.badRequest().body(product.get("message"));
    }
    //get the product with specified id
    @GetMapping("/{id}")
    @ExceptionHandler(HttpClientErrorException.class)
    public  ResponseEntity<Object> getSingleProduct(@PathVariable int id){
        var product=productService.getSingleProduct(id);
        if(Boolean.TRUE.equals(product.get("isSuccess"))){
            return ResponseEntity.ok(product.get("message"));
        }else
            return ResponseEntity.badRequest().body(product.get("message"));
    }
    //update the product with id by using post operation
    @PostMapping("/update-product")
    public ResponseEntity<Object> updateProduct(@RequestBody ProductDto productDto){
        var product =productService.updateProduct(productDto);
        if(Boolean.TRUE.equals(product.get("isSuccess"))){
            return ResponseEntity.ok(product.get("message"));
        }else
            return ResponseEntity.badRequest().body(product.get("message"));
    }
    //delete the product with id by using post operation
    @PostMapping("/delete-product")
    public ResponseEntity<Object> deleteProduct(@RequestBody ProductDeleteId productDeleteId){
        var product =productService.deleteProduct(productDeleteId);
        if(Boolean.TRUE.equals(product.get("isSuccess"))){
            return ResponseEntity.ok(product.get("message"));
        }else
            return ResponseEntity.badRequest().body(product.get("message"));
    }
    @PostMapping("/get-product-check")
    public ResponseEntity<Boolean> productCheck(@RequestBody GetAvailabilityDto getAvailabilityDto){
        return new ResponseEntity<>(productService.productsAvailabilityCheck(getAvailabilityDto), HttpStatus.OK);
    }
    @PostMapping("/update-product-quantity")
    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<Object> updateProductQuantity(@RequestBody GetAvailabilityDto getAvailabilityDto){
        var product =productService.updateProductQuantity(getAvailabilityDto);
        if(Boolean.TRUE.equals(product.get("isSuccess"))){
            return ResponseEntity.ok(product.get("message"));
        }else
            return ResponseEntity.badRequest().body(product.get("message"));
    }
}
