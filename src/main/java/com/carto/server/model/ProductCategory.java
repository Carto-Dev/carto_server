package com.carto.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String text;

    @Column(unique = true)
    private String key;

    private String img;

    @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
    private Set<Product> products;

}