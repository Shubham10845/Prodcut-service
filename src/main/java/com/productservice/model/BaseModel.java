package com.productservice.model;

import jakarta.persistence.*;

import java.io.Serializable;

@MappedSuperclass
public class BaseModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id",columnDefinition = "binary(16)",nullable = false,updatable = false)
    private Long id;
}
