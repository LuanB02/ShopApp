package com.project.shopApp.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "categories")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;
}
