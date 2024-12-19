package com.example.auth_service.dtos;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CreatedUserDto {
    private String username;
    private String password;
    private String role;
}
