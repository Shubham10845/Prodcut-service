package com.productservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity(name = "orders")
@Getter
@Setter
public class Order extends BaseModel{
    @ManyToMany
    @JoinTable(name="product_order",
            joinColumns =@JoinColumn(name="order_id"),
            inverseJoinColumns = @JoinColumn(name="product_id"))
    private List<Product> product;
}
