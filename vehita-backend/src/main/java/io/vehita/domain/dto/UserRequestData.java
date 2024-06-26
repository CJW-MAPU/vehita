package io.vehita.domain.dto;

import lombok.*;

@Getter @Setter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestData {
    private String username;
    private String password;
    private String name;
    private String nickname;
}
