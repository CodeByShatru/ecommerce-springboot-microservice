package com.codebyshatru.userservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

    /**
     *
     */
    private static final long serialVersionUID = -7545443555690967031L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(nullable = false, unique = true, name = "email")
    private String email;

    @Column(nullable = false, unique = true, name = "username")
    private String username;

    @JsonIgnore
    @Column(nullable = false, unique = true, name = "password")
    private String password;

    @Column(nullable = false,name = "title")
    private  String title;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(nullable = true,unique = true, name = "company_name")
    private String companyName;

    @Column(nullable = true,unique = true, name = "vat")
    private String vat;

    @Column(nullable = true,unique = true, name = "website")
    private String website;

    @Column(nullable = true,unique = true, name = "telephone")
    private String telephone;

    @Column(nullable = true,unique = true, name = "telefax")
    private String telefax;

    @Column(nullable = false, unique = true, name = "mobile")
    private BigInteger mobile;

    @Column(nullable = true,unique = true, name = "primary_language")
    private String primaryLanguage;
    @Column(nullable = false, name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private Set<Address> addresses = new HashSet<>();
    @Nonnull
    @Column(name = "created_dt")
    private LocalDate createdDt;

    @Column(name = "updated_dt")
    private LocalDate updatedDt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
