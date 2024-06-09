package io.vehita.controller;

import io.vehita.domain.dto.DatasetRequestData;
import io.vehita.domain.dto.DatasetResponseData;
import io.vehita.service.DatasetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/dataset")
public class DatasetController {

    private final DatasetService datasetService;

    @PostMapping("/upload")
    public ResponseEntity<DatasetResponseData> create(@RequestBody DatasetRequestData request) {
        return ResponseEntity.ok(datasetService.upload(request));
    }

    @GetMapping("/read")
    public ResponseEntity<List<DatasetResponseData>> read() {
        return ResponseEntity.ok(datasetService.getDatasets());
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<DatasetResponseData> read(@PathVariable Long id) {
        return ResponseEntity.ok(datasetService.getDataset(id));
    }

    @GetMapping("/buy/{id}")
    public ResponseEntity<DatasetResponseData> buy(@PathVariable Long id) {
        return ResponseEntity.ok(datasetService.buy(id));
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<DatasetResponseData> download(@PathVariable Long id) {
        return ResponseEntity.ok(datasetService.download(id));
    }
}
