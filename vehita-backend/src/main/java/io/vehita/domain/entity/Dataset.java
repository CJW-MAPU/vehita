package io.vehita.domain.entity;

import io.vehita.domain.dto.DatasetRequestData;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
@Table(name = "Datasets")
public class Dataset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String datasetName;
    private String vehicleType;
    private String price;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne
    private User user;

    public Dataset(DatasetRequestData datasetRequestData, User user) {
        this.datasetName = datasetRequestData.getDatasetName();
        this.vehicleType = datasetRequestData.getVehicleType();
        this.price = datasetRequestData.getPrice();
        this.description = datasetRequestData.getDescription();

        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();

        this.user = user;
    }
}
