package com.project.shopApp.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequestDto {
    private Long id;
    @NotNull(message = "Name is required")
    @Size(min = 3, max = 200, message = "Name must be between 3 and 200 char")
    private String name;

    @Min(value = 0, message = "Price must be more than 0")
    @Max(value = 10000000, message = "Price must be less than 10,000,000")
    private Float price;

    private String thumbnail;
    private String description;

    @JsonProperty("category_id")
    private long categoryId;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    private MultipartFile file;

    private List<MultipartFile> files;
}
