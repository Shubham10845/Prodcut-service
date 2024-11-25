package com.productservice.controller;

import com.productservice.dto.GenericProductDTO;
import com.productservice.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/category")
public class CategoryController {
    CategoryService categoryService;
    @GetMapping("/{id}/{page}/{size}")
    public ResponseEntity<Page<GenericProductDTO>> getProductsByCategoryId(@PathVariable("id") long categoryId,
                                                                           @PathVariable("page") int pageNumber,
                                                                           @PathVariable("size") int pageSize){
        Page<GenericProductDTO> genericProductDTOS = categoryService.getProductsByCategoryId(categoryId, pageNumber, pageSize);
        return new ResponseEntity<>(genericProductDTOS, HttpStatus.OK);
    }
}
