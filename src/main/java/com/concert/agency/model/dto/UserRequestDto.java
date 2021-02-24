package com.concert.agency.model.dto;

import com.concert.agency.lib.annotations.EmailValidation;
import com.concert.agency.lib.annotations.PasswordValidation;

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
