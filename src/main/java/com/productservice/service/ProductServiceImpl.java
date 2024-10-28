package com.productservice.service;

import com.productservice.dto.GenericProductDTO;
import com.productservice.model.Category;
import com.productservice.model.Price;
import com.productservice.model.Product;
import com.productservice.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService{
    ProductRepository productRepository;
    @Override
    public GenericProductDTO createProduct(GenericProductDTO genericProductDTO) {
        Product product = from(genericProductDTO);
        Product savedProduct = productRepository.save(product);
        return from(savedProduct);
    }

    @Override
    public GenericProductDTO getProductById(long productId) {
        Optional<Product> product = Optional.of(productRepository.findById(productId).orElseThrow());
        return from(product.get());
    }

    public Product from (GenericProductDTO genericProductDTO){
        Product product = new Product();
        Category category = new Category();
        Price price = new Price();
        category.setName(genericProductDTO.getCategory());
        price.setValue(genericProductDTO.getPrice());
        product.setTitle(genericProductDTO.getTitle());
        product.setDescription(genericProductDTO.getDescription());
        product.setImage(genericProductDTO.getImage());
        product.setPrice(price);
        product.setCategory(category);
        return product;
    }
    public GenericProductDTO from(Product product){
        GenericProductDTO genericProductDTO = new GenericProductDTO();
        genericProductDTO.setTitle(product.getTitle());
        genericProductDTO.setDescription(product.getDescription());
        genericProductDTO.setCategory(product.getCategory().getName());
        genericProductDTO.setPrice(product.getPrice().getValue());
        genericProductDTO.setImage(product.getImage());
        return genericProductDTO;
    }
}
