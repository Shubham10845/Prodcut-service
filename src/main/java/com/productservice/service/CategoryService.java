package com.productservice.service;

import com.productservice.dto.GenericProductDTO;
import org.springframework.data.domain.Page;

public interface CategoryService {
    Page<GenericProductDTO> getProductsByCategoryId(long categoryId, int pageNumber, int pageSize);
}
