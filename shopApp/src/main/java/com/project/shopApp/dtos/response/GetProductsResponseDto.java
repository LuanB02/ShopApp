package com.project.shopApp.dtos.response;

import com.project.shopApp.dtos.ProductRequestDto;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetProductsResponseDto {
    private List<ProductRequestDto> products;
    private int totalPages;
}
