package io.github.dolphin.software.users.controller;

import io.github.dolphin.software.users.model.User;
import io.github.dolphin.software.users.model.dto.UserDTO;
import io.github.dolphin.software.users.model.exceptions.UserNotFoundException;
import io.github.dolphin.software.users.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

/**
 * The UserController class is responsible for handling HTTP requests related
 * to user management. It provides endpoints for retrieving users.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;
    private final MessageSource messageSource;

    /**
     * Retrieves a list of users.
     * <p>
     * This method is responsible for handling HTTP GET requests to retrieve all users in the system.
     * It will return a ResponseEntity object with a HttpStatus OK (200) status code and a Map object
     * containing the key "users" and a List of UserDTO objects as the value.
     * The UserDTO objects represent the users in the system.
     * <p>
     * @return A ResponseEntity object with a HttpStatus OK (200) status code and a Map object containing
     * the key "users" and a List of UserDTO objects as the value.
     * @see UserDTO
     */
    @GetMapping
    public ResponseEntity<Map<String, List<UserDTO>>> getUsers() {

        return ResponseEntity.status(HttpStatus.OK).body(Map.of("users", userService.findAll()));
    }

    /**
     * Retrieves a user by their unique identifier.
     *
     * @param id The unique identifier of the user.
     * @return A ResponseEntity object with a HttpStatus OK (200) status code and the UserDTO object representing the user.
     * @throws UserNotFoundException if the user is not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable UUID id) {
        UserDTO user = userService.findById(id).orElseThrow(() -> new UserNotFoundException(
                messageSource.getMessage("user.message.notfound", new String[] {id.toString()}, Locale.US)
        ));
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    /**
     * Creates a new user in the system.
     *
     * <p>
     * This method is responsible for handling HTTP POST requests to create a new user in the system.
     * The user object provided in the request body will be saved to the database using the userService.save() method.
     * The method returns a ResponseEntity object with a HttpStatus CREATED (201) status code and the saved User object in the response body.
     * </p>
     *
     * @param user The User object representing the new user to be created.
     * @return A ResponseEntity object with a HttpStatus CREATED (201) status code and the saved User object in the response body.
     * @see User
     */
    @PostMapping
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user, @NotNull BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        if (result.hasErrors()) {
            result.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }
}
