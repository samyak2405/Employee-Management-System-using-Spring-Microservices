package com.javahunter.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "streetAddress",column = @Column(name = "t_street_address")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "t_postal_code")),
            @AttributeOverride(name = "landmark",column = @Column(name = "t_landmark")),
            @AttributeOverride(name = "city", column = @Column(name = "t_city")),
            @AttributeOverride(name = "state",column = @Column(name = "t_state")),
            @AttributeOverride(name = "country", column = @Column(name = "t_country"))
    })
    private Address temporaryAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "streetAddress",column = @Column(name = "p_street_address")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "p_postal_code")),
            @AttributeOverride(name = "landmark",column = @Column(name = "p_landmark")),
            @AttributeOverride(name = "city", column = @Column(name = "p_city")),
            @AttributeOverride(name = "state",column = @Column(name = "p_state")),
            @AttributeOverride(name = "country", column = @Column(name = "p_country"))
    })
    @Column(nullable = false)
    private Address permanentAddress;

    @Column(nullable = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    @Column(nullable = false,unique = true)
    private String mobileNumber;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;

}
