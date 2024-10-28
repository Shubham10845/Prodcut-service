package com.productservice.service;

import com.productservice.dto.GenericProductDTO;

public interface ProductService {
    GenericProductDTO createProduct(GenericProductDTO product);
    GenericProductDTO getProductById(long productId);
}
