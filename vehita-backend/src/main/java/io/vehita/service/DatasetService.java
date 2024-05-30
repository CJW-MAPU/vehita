package io.vehita.service;

import io.vehita.domain.dto.DatasetRequestData;
import io.vehita.domain.dto.DatasetResponseData;
import io.vehita.domain.entity.Dataset;
import io.vehita.domain.entity.User;
import io.vehita.exception.UserNotFoundException;
import io.vehita.repository.DatasetRepository;
import io.vehita.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DatasetService {

    private final DatasetRepository datasetRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    /*
    TODO : 원본 데이터셋을 데이터베이스에 저장하며 블록체인 스마트 컨트랙트 발행 및 컨트랙트에 데이터셋의 해쉬값 포함
     */
    public DatasetResponseData upload(DatasetRequestData request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UserNotFoundException(request.getUsername()));

        Dataset dataset = new Dataset(request, user);

        datasetRepository.save(dataset);

        DatasetResponseData response = modelMapper.map(request, DatasetResponseData.class);
        response.setUsername(request.getUsername());

        return response;
    }

    public List<DatasetResponseData> getDatasets() {
        List<Dataset> datasets = datasetRepository.findAll();
        List<DatasetResponseData> response = new ArrayList<>();

        for (Dataset dataset : datasets) {
            response.add(modelMapper.map(dataset, DatasetResponseData.class));
        }

        return response;
    }

    public DatasetResponseData getDataset(Long id) {
        Dataset dataset = datasetRepository.findById(id)
                .orElseThrow(NullPointerException::new);

        DatasetResponseData response = modelMapper.map(dataset, DatasetResponseData.class);
        User user = userRepository.findById(dataset.getUser().getId())
                .orElseThrow(NullPointerException::new);

        response.setUsername(user.getUsername());
        return response;
    }

    /*
    TODO : JWT 구현을 통한 HttpServletRequest 매개변수 추가 및 토큰 주인의 블록체인 지갑 주소를
    TODO : 기반으로 가격에 따른 지불 메커니즘 구현
     */
    public DatasetResponseData buy(Long id) {
        Dataset dataset = datasetRepository.findById(id)
                .orElseThrow(NullPointerException::new);

        return modelMapper.map(dataset, DatasetResponseData.class);
    }

    /*
    TODO : 데이터셋의 해쉬값을 기반으로 한 데이터베이스의 원본 데이터셋 파인딩 및 다운로드 메커니즘 구현
     */
    public DatasetResponseData download(Long id) {
        Dataset dataset = datasetRepository.findById(id)
                .orElseThrow(NullPointerException::new);

        return modelMapper.map(dataset, DatasetResponseData.class);
    }
}
