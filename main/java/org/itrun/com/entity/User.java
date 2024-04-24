package org.itrun.com.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @NotBlank(message = "External or Internal user type")
    @Length(max = 255, message = "Don't be more than 255")
    @Column(name = "type")
    private String type;

    @NotBlank(message = "Name is required")
    @Length(max = 255, message = "Don't be more than 255")
    @Column(name = "name")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Length(max = 255, message = "Don't be more than 255")
    @Column(name = "last_name")
    private String lastName;

    @NotBlank(message = "Phone number is required")
    @Length(max = 255, message = "Don't be more than 255")
    @Pattern(regexp = "^\\d{3}-\\d{3}-\\d{3}$",
            message = "irregular format of this field")
    @Column(name = "mobile")
    private String mobile;

    @NotBlank(message = "Email is required")
    @Length(max = 255, message = "Don't be more than 255")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$",
            message = "irregular format of this field")
    @Column(name = "email")
    private String email;

    @NotBlank(message = "Pesel is required")
    @Length(max = 255, message = "Don't be more than 12")
    @Pattern(regexp = "^\\d{11}$",
            message = "irregular format of this field")
    @Column(name = "pesel")
    private String pesel;

    public User() {
    }

    public User(String type, String firstName, String lastName, String mobile, String email, String pesel) {
        this.type = type;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobile = mobile;
        this.email = email;
        this.pesel = pesel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
    public String toString() {
        return "User{" +
                "type='" + type + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", pesel='" + pesel + '\'' +
                '}';
    }
}