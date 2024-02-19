package io.github.dolphin.software.users.services.impl;

import io.github.dolphin.software.users.model.User;
import io.github.dolphin.software.users.model.dto.UserDTO;
import io.github.dolphin.software.users.model.dto.mapper.UserDTOMapper;
import io.github.dolphin.software.users.model.exceptions.UserNotFoundException;
import io.github.dolphin.software.users.repository.UserRepository;
import io.github.dolphin.software.users.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * The UserServiceImpl class is an implementation of the UserService interface.
 * It provides methods for managing users in the system.
 * The class is annotated with @Service to indicate that it is a service component.
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MessageSource messageSource;

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> findAll() {
        Stream<User> users =
                StreamSupport.stream(userRepository.findAll().spliterator(), false);
        return users.map(UserDTOMapper.createMapper()::withUser)
                .map(UserDTOMapper::build)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserDTO> findById(UUID id) {
        return userRepository.findById(id)
                .map(UserDTOMapper.createMapper()::withUser)
                .map(UserDTOMapper::build);
    }

    @Override
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateUserById(UUID id, User user) {
        userRepository.findById(id)
                .ifPresentOrElse(
                        u -> {
                            user.setEmail(u.getEmail());
                            user.setFullName(u.getFullName());
                            user.setPhone(u.getPhone());
                            user.setUpdateDate(LocalDate.now());
                            userRepository.save(user);
                        },
                        () -> {
                            throw new UserNotFoundException(messageSource.getMessage("user.message.notfound", new String[] {id.toString()}, Locale.US))
                        }
                );
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void delete(User user) {
        userRepository.delete(user);
    }
}
