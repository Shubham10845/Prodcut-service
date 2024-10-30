package com.productservice.service;

import com.productservice.dto.GenericProductDTO;

public interface ProductService {
    GenericProductDTO createProduct(GenericProductDTO product);
    GenericProductDTO getProductById(long productId);
    GenericProductDTO updateProduct(GenericProductDTO genericProductDTO, long productId);
    GenericProductDTO deleteProduct(long id);
}
