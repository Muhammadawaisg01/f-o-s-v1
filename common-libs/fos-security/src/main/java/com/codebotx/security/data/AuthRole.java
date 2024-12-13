package com.codebotx.security.data;

import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class AuthRole {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private AuthERole name;

    public AuthRole() {
    }

    public AuthRole(AuthERole name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public AuthERole getName() {
        return name;
    }

    public void setName(AuthERole name) {
        this.name = name;
    }
}
