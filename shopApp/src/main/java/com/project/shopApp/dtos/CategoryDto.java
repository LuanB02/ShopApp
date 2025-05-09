package com.project.shopApp.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
    private Long id;

    @NotEmpty(message = "This field name is not empty")
    private String name;
}
