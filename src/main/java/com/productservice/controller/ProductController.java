package com.productservice.controller;

import com.productservice.dto.GenericProductDTO;
import com.productservice.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {
    ProductService productService;
    @PostMapping
    public ResponseEntity<GenericProductDTO> createProduct(@RequestBody GenericProductDTO genericProductDTO){
        GenericProductDTO genericProductDTO1 = productService.createProduct(genericProductDTO);
        return new ResponseEntity<>(genericProductDTO1, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<GenericProductDTO> getProductById(@PathVariable("id") long productId){
        GenericProductDTO genericProductDTO = productService.getProductById(productId);
        return new ResponseEntity<>(genericProductDTO, HttpStatus.FOUND);
    }
    @PutMapping("/{id}")
    public ResponseEntity<GenericProductDTO> updateProduct(@RequestBody GenericProductDTO genericProductDTO, @PathVariable("id") long productId){
        GenericProductDTO productDTO = productService.updateProduct(genericProductDTO,productId);
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<GenericProductDTO> deleteProduct(@PathVariable("id") long productId){
        GenericProductDTO genericProductDTO = productService.deleteProduct(productId);
        return new ResponseEntity<>(genericProductDTO,HttpStatus.OK);
    }
}
