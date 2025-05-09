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
public class SocialAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "provider", nullable = false, length = 20)
    private String provider;

    @Column(name = "provider_id", nullable = false, length = 20)
    private String providerId;

    @Column(name = "email",length = 150)
    private String email;

    @Column(name = "name", length = 150)
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;
}
