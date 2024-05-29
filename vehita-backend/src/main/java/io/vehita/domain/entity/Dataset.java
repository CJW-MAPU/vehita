package io.vehita.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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

    @ManyToOne
    private User user;

    public void enroll(User user) {
        this.user = user;
    }
}
