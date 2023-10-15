package com.javahunter.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Address {

    private String streetAddress;
    private String postalCode;
    private String landmark;
    private String city;
    private String state;
    private String country;
}
