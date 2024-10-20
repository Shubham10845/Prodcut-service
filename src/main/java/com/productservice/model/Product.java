package com.productservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product extends BaseModel{
    private String title;
    private String description;
    private String  image;

    //P:C 1P:1C 1C:M-P
    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name="category")
    private Category category;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}) //Add and remove price when product is added or removed
    private Price price;
}
