package com.project.shopApp.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class Product extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 300)
    private String name;

    private Float price;

    @Column(name = "thumbnail", length = 300)
    private String thumbnail;

    @Column(name = "description")
    private  String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
