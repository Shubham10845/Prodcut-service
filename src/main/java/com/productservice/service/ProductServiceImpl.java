package com.productservice.service;

import com.productservice.dto.GenericProductDTO;
import com.productservice.model.Category;
import com.productservice.model.Price;
import com.productservice.model.Product;
import com.productservice.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    ProductRepository productRepository;

    @Override
    public GenericProductDTO createProduct(GenericProductDTO genericProductDTO) {
        Product product = from(genericProductDTO);
        Product savedProduct = productRepository.save(product);
        return from(savedProduct);
    }

    @Override
    public List<GenericProductDTO> createProductsInBulk(List<GenericProductDTO> productDTOS) {
        List<Product> products = productDTOS.stream()
                .map(this::from)
                .collect(Collectors.toList());
        return productRepository.saveAll(products)
                .stream()
                .map(this::from)
                .collect(Collectors.toList());
    }

    @Override
    public GenericProductDTO getProductById(long productId) {
        Optional<Product> product = Optional.of(productRepository.findById(productId).orElseThrow());
        return from(product.get());
    }

    @Override
    public Page<GenericProductDTO> getAllProduct(int pageNumber, int pageSize) {
        return productRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by("title"))).map(this::from);
    }

    @Override
    public GenericProductDTO updateProduct(GenericProductDTO genericProductDTO, long productId) {
        Product product = productRepository.findById(productId).orElseThrow();
        product.setTitle(genericProductDTO.getTitle());
        product.getCategory().setName(genericProductDTO.getCategory());
        product.getPrice().setValue(genericProductDTO.getPrice());
        product.setDescription(genericProductDTO.getDescription());
        product.setImage(genericProductDTO.getImage());
        productRepository.save(product);
        return genericProductDTO;
    }

    @Override
    public GenericProductDTO deleteProduct(long productId) {
        return productRepository.findById(productId)
                .map((product) -> {
                    productRepository.delete(product);
                    return from(product);
                })
                .orElseThrow();
    }

    public Product from(GenericProductDTO genericProductDTO) {
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
