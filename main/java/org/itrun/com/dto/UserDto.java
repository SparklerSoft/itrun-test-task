package org.itrun.com.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

public class UserDto {

    @Schema(description = "The type of the user", example = "External, Internal")
    private String type;

    @Schema(description = "The name of the user", example = "Janek")
    private String firstName;

    @Schema(description = "The last name of the user", example = "Nowak")
    private String lastName;

    @Schema(description = "The mobile phone number of the user", example = "111-222-333")
    private String mobile;

    @Schema(description = "The email of the user", example = "mail@mail.com")
    private String email;

    @Schema(description = "The pesel number of the user", example = "12345678901")
    private String pesel;

    private Object id;

    public UserDto(String type, String firstName, String lastName, String mobile, String email, String pesel) {
        this.type = type;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobile = mobile;
        this.email = email;
        this.pesel = pesel;
    }

    public UserDto() {

    }

    public UserDto(long id, String firstName) {
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

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(id, userDto.id) &&
                Objects.equals(firstName, userDto.firstName) &&
                Objects.equals(lastName, userDto.lastName) &&
                Objects.equals(mobile, userDto.mobile) &&
                Objects.equals(email, userDto.email) &&
                Objects.equals(pesel, userDto.pesel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }
}
