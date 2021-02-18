package com.cinema.model.dto;

import com.cinema.lib.EmailValidation;
import com.cinema.lib.PasswordValidation;

@PasswordValidation.List({
        @PasswordValidation(
                field = "password",
                fieldMatch = "repeatPassword",
                message = "Passwords are different or empty")})
public class UserRequestDto {
    @EmailValidation
    private String email;
    private String password;
    private String repeatPassword;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
