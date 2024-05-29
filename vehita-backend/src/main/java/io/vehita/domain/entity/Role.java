package io.vehita.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;

    @Getter
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    public Role(String username, RoleType roleType) {
        this.username = username;
        this.roleType = roleType;
    }

    public Role(RoleType roleType) {
        this(null, roleType);
    }
}
