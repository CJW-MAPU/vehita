package io.vehita.service;

import io.vehita.domain.dto.UserRequestData;
import io.vehita.domain.dto.UserResponseData;
import io.vehita.domain.entity.User;
import io.vehita.exception.DuplicatedUsernameException;
import io.vehita.exception.InvalidPasswordException;
import io.vehita.exception.UserNotFoundException;
import io.vehita.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserResponseData register(UserRequestData request) {
        User user = new User(request);

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new DuplicatedUsernameException(user.getUsername());
        }

        userRepository.save(user);

        return modelMapper.map(user, UserResponseData.class);
    }

    public UserResponseData login(UserRequestData request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UserNotFoundException(request.getUsername()));

        if (!isValidPassword(request.getPassword(), user.getPassword())) {
            throw new InvalidPasswordException();
        }

        return modelMapper.map(user, UserResponseData.class);
    }

    private boolean isValidPassword(String password, String valid) {
        return valid.equals(password);
    }
}
