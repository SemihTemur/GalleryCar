package com.semih.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// giriş yapmak ve kayıt olmak için kullanılan sınıf
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {

    @NotEmpty(message = "Username cannot be empty")
    @Size(min = 3, max = 15, message = "Username must be between 3 and 15 characters")
    private String username;

    @NotEmpty(message = "Username cannot be empty")
    @Size(min = 4,message = "Password must be minumum size 4")
    private String password;

}
