package com.productservice.service;

import com.productservice.dto.GenericProductDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    GenericProductDTO createProduct(GenericProductDTO product);
    List<GenericProductDTO> createProductsInBulk(List<GenericProductDTO> productDTOS);
    GenericProductDTO getProductById(long productId);
    Page<GenericProductDTO> getAllProduct(int pageNumber, int pageSize);
    GenericProductDTO updateProduct(GenericProductDTO genericProductDTO, long productId);
    GenericProductDTO deleteProduct(long id);
}
