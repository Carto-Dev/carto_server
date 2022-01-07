package com.carto.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    CartoUser user;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private double cost;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<ProductImage> imgLinks;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "product_categories_relation",
            joinColumns = @JoinColumn(
                    name = "product_id",
                    referencedColumnName = "id",
                    nullable = false,
                    updatable = false
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "category_id",
                    referencedColumnName = "id",
                    nullable = false,
                    updatable = false
            )
    )
    private Set<ProductCategory> categories;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;

}
