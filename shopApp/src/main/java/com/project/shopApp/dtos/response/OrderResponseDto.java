package com.project.shopApp.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class OrderResponseDto {
    @JsonProperty("user_id")
    @Min(value = 1, message = "UserId must be > 0")
    private Long userId;

    @JsonProperty("fullname")
    private String fullName;

    private String email;

    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    private String address;

    private String note;

    @Min(value = 0, message = "total money must be > 0")
    private Float totalMoney;

    private boolean active;

    private String status;

    private String shippingMethod;

    private String shippingAddress;

    private String paymentMethod;

}
