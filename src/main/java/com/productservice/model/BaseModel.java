package com.productservice.model;

import jakarta.persistence.*;

import java.io.Serializable;

@MappedSuperclass
public class BaseModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable = false,updatable = false)
    private Long id;
}
