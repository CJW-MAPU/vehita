package io.vehita.domain.dto;

import lombok.*;

@Getter @Setter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponseData {
    private Long id;
    private String username;
    private String accessToken;
    private String refreshToken;
    private String name;
    private String nickname;

    public void complete(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
