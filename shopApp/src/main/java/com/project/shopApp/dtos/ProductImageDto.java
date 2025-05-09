package com.project.shopApp.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ProductImageDto {
    @JsonProperty("product_id")
    @Min(value = 1, message = "Product id have to > 0")
    private Long productId;

    @JsonProperty("image_url")
    @Size(min = 5, max = 200, message = "Image's name")
    private String imageUrl;
}
