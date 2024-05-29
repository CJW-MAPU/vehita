package io.vehita.domain.dto;

import lombok.*;

@Getter @Setter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class DatasetResponseData {
    private String datasetName;
    private String vehicleType;
    private String price;
    private String Description;
    private String seller;
}
