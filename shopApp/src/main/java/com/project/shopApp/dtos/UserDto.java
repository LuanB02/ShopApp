package com.project.shopApp.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class UserDto {
    @JsonProperty("fullname")
    private String fullName;

    @JsonProperty("phone_number")
    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    private String address;

    @NotBlank(message = "Password is required")
    private String password;

    @JsonProperty("retype_password")
    @NotBlank(message = "Retype password")
    private String retypePassword;

    @JsonProperty("date_of_birth")
    private Date dateOfBirth;
    
    @JsonProperty("facebook_account_id")
    private int facebookAccountId;
    
    @JsonProperty("google_account_id")
    private int googleAccountId;

    @NotNull(message = "RoleID is required")
    @JsonProperty("role_id")
    private Long roleId;
}
