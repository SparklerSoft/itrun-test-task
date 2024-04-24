package org.itrun.com.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class EditUserDto {

    @Schema(description = "The updated type of the user", example = "External, Internal")
    private String type;

    @Schema(description = "The updated name of the user", example = "Janek_New")
    private String firstName;

    @Schema(description = "The updated last name of the user", example = "Nowak_New")
    private String lastName;

    @Schema(description = "The updated mobile phone number of the user", example = "NEW-222-333")
    private String mobile;

    @Schema(description = "The updated email of the user", example = "new_mail@mail.com")
    private String email;

    public EditUserDto(String type, String firstName, String lastName, String mobile, String email) {
        this.type = type;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobile = mobile;
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
