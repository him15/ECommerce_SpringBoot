package com.ecommerce.project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users",
         uniqueConstraints = {
         @UniqueConstraint(columnNames = "username"),
         @UniqueConstraint(columnNames = "email")
         })
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long userId;

    @NotBlank
    @Size(min = 3, max = 20)
    @Column(name="username")
    private String userName;

    @NotBlank
    @Size(min = 3, max = 50)
    @Email
    @Column(name="email")
    private String email;

    @NotBlank
    @Size(min = 3, max = 50)
    @Column(name="password")
    private String password;

    public User(String username, String email, String password) {
        this.userName = username;
        this.email = email;
        this.password = password;
    }


    @Setter
    @Getter
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER )
    @JoinTable( name = "user_role" , joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @Getter
    @Setter
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name="user_address",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id"))
    private List<Address> addresses = new ArrayList<>();


    @ToString.Exclude // means it wont include generated toString method in this
    @OneToMany(mappedBy = "user",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            orphanRemoval = true
    )
    private Set<Product> products;



}
