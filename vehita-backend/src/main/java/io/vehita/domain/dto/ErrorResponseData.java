package io.vehita.domain.dto;

import lombok.*;

@Getter @Setter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseData {
    private String message;
}
