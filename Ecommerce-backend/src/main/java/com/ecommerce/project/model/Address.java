package com.ecommerce.project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "addresses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @NotBlank
    @Size(min = 3, message = "street name atlease 3 chars")
    private String street;

    @NotBlank
    @Size(min = 3, message = "Building atleast 3 char")
    private String buildingName;

    @NotBlank
    @Size(min = 3, message = "city name atleast 3 char")
    private String city;

    @NotBlank
    @Size(min = 2, message = "state name atleast 2 char")
    private String state;

    @NotBlank
    @Size(min = 2, message = "country name atleast 2 char")
    private String country;

    @NotBlank
    @Size(min = 6, message = "Pincode atleast 6 char")
    private String pincode;

    @ToString.Exclude
    @ManyToMany(mappedBy = "addresses")
    private List<User> users = new ArrayList<>();

    public Address(String street, String buildingName, String city, String state, String country, String pincode) {
        this.street = street;
        this.buildingName = buildingName;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pincode = pincode;
    }
}
