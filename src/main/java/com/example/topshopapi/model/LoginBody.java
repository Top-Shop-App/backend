package com.example.topshopapi.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class LoginBody {

    @NotNull
    @NotBlank
    private String username;

    @NotNull
    @NotBlank
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
