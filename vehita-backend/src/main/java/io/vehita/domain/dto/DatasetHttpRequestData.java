package io.vehita.domain.dto;

import lombok.*;

@Getter @Setter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class DatasetHttpRequestData {
    private String datasetName;
    private String vehicleType;
    private String price;
    private String description;
}
