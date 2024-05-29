package io.vehita.domain.entity;

import io.vehita.domain.dto.UserHttpRequestData;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

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
    private String userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToMany
    private List<Dataset> datasets;

    public User(UserHttpRequestData userHttpRequestData) {
        this.username = userHttpRequestData.getUsername();
        this.password = userHttpRequestData.getPassword();
        this.name = userHttpRequestData.getName();
        this.nickname = userHttpRequestData.getNickname();

        this.userId = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}
