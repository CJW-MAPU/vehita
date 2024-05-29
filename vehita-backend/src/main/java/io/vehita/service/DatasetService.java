package io.vehita.service;

import io.jsonwebtoken.Claims;
import io.vehita.domain.dto.DatasetHttpRequestData;
import io.vehita.domain.dto.DatasetResponseData;
import io.vehita.domain.entity.Dataset;
import io.vehita.domain.entity.User;
import io.vehita.exception.EmptyCookieException;
import io.vehita.exception.UserNotFoundException;
import io.vehita.repository.DatasetRepository;
import io.vehita.repository.UserRepository;
import io.vehita.util.CookieUtil;
import io.vehita.util.JwtUtil;
import io.vehita.util.TokenType;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DatasetService {

    private final DatasetRepository datasetRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final CookieUtil cookieUtil;
    private final JwtUtil jwtUtil;

    public DatasetResponseData upload(DatasetHttpRequestData data, HttpServletRequest request) {
        if (request.getCookies() == null) {
            throw new EmptyCookieException();
        }

        String accessToken = cookieUtil.parseTokenFromCookies(
                request.getCookies(),
                TokenType.ACCESS_TOKEN
        );

        Claims claims = jwtUtil.parseToken(accessToken);
        String username = claims.get("username", String.class);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        Dataset dataset = modelMapper.map(data, Dataset.class);
        dataset.enroll(user);

        datasetRepository.save(dataset);

        DatasetResponseData responseData = modelMapper.map(data, DatasetResponseData.class);
        responseData.setSeller(username);

        return responseData;
    }
}
