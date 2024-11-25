package com.productservice.service;

import com.productservice.dto.GenericProductDTO;
import com.productservice.model.Category;
import com.productservice.model.Price;
import com.productservice.model.Product;
import com.productservice.repository.CategoryRepository;
import com.productservice.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService{
    CategoryRepository categoryRepository;
    @Override
    public Page<GenericProductDTO> getProductsByCategoryId(long categoryId, int pageNumber, int pageSize) {
        return categoryRepository.findProductsByCategoryId(categoryId,PageRequest.of(pageNumber,pageSize)).map(this::from);
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
