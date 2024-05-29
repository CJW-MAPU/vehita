package io.vehita.domain.dto;

import lombok.*;

@Getter @Setter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class SilentRefreshResponseData {
    private Long id;
    private String name;
    private String nickname;
}
