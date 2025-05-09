package com.project.shopApp.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDetailDto {
    @JsonProperty("order_id")
    @Min(value = 1, message = "OrderId have to > 1")
    private Long orderId;

    @JsonProperty("product_id")
    @Min(value = 1, message = "ProductId have to > 1")
    private Long productId;

    private Long price;

    @JsonProperty("number_of_products")
    @Min(value = 1, message = "Product have to > 1")
    private Long numberOfProducts;

    @JsonProperty("total_money")
    @Min(value = 0, message = "total_money have to > 0")
    private Float totalMoney;

    private String color;
}
