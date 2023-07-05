package com.codebyshatru.userservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;
import java.time.LocalDate;

@Entity
@Table(name = "user_address")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long addressId;

    @Column(nullable = false, name = "is_primary")
    private Boolean primary;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, name = "address_line1", length = 200)
    private String addressLine1;

    @Column(nullable = false, name = "address_line2", length = 200)
    private String addressLine2;

    @Column(nullable = false, name = "zipcode", length = 6)
    private Integer zipcode;

    @Column(nullable = false, name = "country")
    private String country;

    @Column(nullable = false, name = "state")
    private String state;

    @Column(nullable = false, name = "city")
    private String city;

    @Column(name = "secondary_mobile", length = 10)
    private BigInteger secondaryMobile;

    @Column(nullable = false, name = "created_dt")
    private LocalDate createdDt;

    @Column(name = "updated_dt")
    private LocalDate updatedDt;
}
