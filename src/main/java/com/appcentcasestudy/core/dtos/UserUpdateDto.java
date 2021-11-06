package com.appcentcasestudy.core.dtos;

import lombok.Data;

@Data
public class UserUpdateDto {
    private static final long serialVersionUID = 1L;
    private String email;
    private String password;

}
