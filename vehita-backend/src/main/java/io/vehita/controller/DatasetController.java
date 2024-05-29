package io.vehita.controller;

import io.vehita.domain.dto.DatasetHttpRequestData;
import io.vehita.domain.dto.DatasetResponseData;
import io.vehita.service.DatasetService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/dataset")
public class DatasetController {

    private final DatasetService datasetService;

    @PostMapping("/upload")
    public ResponseEntity<DatasetResponseData> create(@RequestBody DatasetHttpRequestData datasetHttpRequestData,
                                                      HttpServletRequest request) {
        System.out.println("test");
        return ResponseEntity.ok(datasetService.upload(datasetHttpRequestData, request));
    }
}
