package io.vehita.domain.entity;

import io.vehita.domain.dto.UserRequestData;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String name;
    private String nickname;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToMany
    private List<Dataset> datasets;

    public User(UserRequestData userRequestData) {
        this.username = userRequestData.getUsername();
        this.password = userRequestData.getPassword();
        this.name = userRequestData.getName();
        this.nickname = userRequestData.getNickname();

        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}
