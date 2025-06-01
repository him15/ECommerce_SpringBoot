package com.ecommerce.project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
@ToString
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;

    @NotBlank
    @Size(min = 3, max = 50, message = "Products must contain atleast 3 character")
    private String productName;

    @NotBlank
    @Size(min = 6,  message = "Description must contain atleast 6 character")
    private String description;
    private String image;
    private Integer quantity;
    private Double price; // 100
    private Double discount; // 25%
    private Double specialPrice; // 75  -> A.P - (D/100) * 100


    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "seller_id") // it refers to user_id in the user table // create link from product to user
    private User user;
}
