package com.project.shopApp.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product_images")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "image_url", length = 300)
    private String imageUrl;
}
