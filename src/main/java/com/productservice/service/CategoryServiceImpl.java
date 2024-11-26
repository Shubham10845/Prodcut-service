package com.productservice.service;

import com.productservice.dto.GenericProductDTO;
import com.productservice.model.Product;
import com.productservice.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService{
    CategoryRepository categoryRepository;
    @Override
    public Page<GenericProductDTO> getProductsByCategoryId(long categoryId, int pageNumber, int pageSize) {
        long start = (long) (pageNumber-1) * pageSize;
        List<GenericProductDTO> products= categoryRepository.findById(categoryId)
                .orElseThrow().getProducts()
                .stream()
                .skip(start)
                .limit(pageSize)
                .map(this::from)
                .toList();
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return new PageImpl<>(products, pageable, products.size());
    }
    public GenericProductDTO from(Product product) {
        GenericProductDTO genericProductDTO = new GenericProductDTO();
        genericProductDTO.setTitle(product.getTitle());
        genericProductDTO.setDescription(product.getDescription());
        genericProductDTO.setCategory(product.getCategory().getName());
        genericProductDTO.setPrice(product.getPrice().getValue());
        genericProductDTO.setImage(product.getImage());
        return genericProductDTO;
    }
}
