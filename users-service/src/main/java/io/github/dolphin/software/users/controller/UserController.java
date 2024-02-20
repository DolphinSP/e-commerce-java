package io.github.dolphin.software.users.controller;

import io.github.dolphin.software.users.model.User;
import io.github.dolphin.software.users.model.dto.UserDTO;
import io.github.dolphin.software.users.model.exceptions.UserNotFoundException;
import io.github.dolphin.software.users.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Invalid input",
                content = @Content),
        @ApiResponse(responseCode = "404", description = "Not found",
                content = @Content)
})
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
     *
     * @return A ResponseEntity object with a HttpStatus OK (200) status code and a Map object containing
     * the key "users" and a List of UserDTO objects as the value.
     * @see UserDTO
     */
    @Operation(summary = "Get a list of users", description = "Retrieves a list of users.",
            tags = {"users"}, operationId = "getUsers", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retrieved users successfully",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Users not found",
                    content = @Content)
    })
    @GetMapping
    public ResponseEntity<Map<String, List<UserDTO>>> getUsers() {

        return ResponseEntity.status(HttpStatus.OK)
                .body(Map.of("users", userService.findAll()));
    }

    /**
     * Retrieves a user by their unique identifier.
     *
     * @param id The unique identifier of the user.
     * @return A ResponseEntity object with a HttpStatus OK (200) status code and the UserDTO object representing the user.
     * @throws UserNotFoundException if the user is not found.
     */

    @Operation(summary = "Get a user by their unique identifier", description = "Retrieves a user by their unique identifier.",
            tags = {"users"}, operationId = "getUserById", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retrieved user successfully",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class))}),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid UUID supplied",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(
            @Parameter(description = "ID of the user to be obtained. Cannot be empty.",
                    required = true) @PathVariable UUID id) {
        UserDTO user =
                userService.findById(id).orElseThrow(() -> new UserNotFoundException(
                        messageSource.getMessage("user.message.notfound",
                                new String[] {id.toString()}, Locale.US)
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
    @Operation(summary = "Create a new user in the system", description = "Creates a new user in the system.",
            tags = {"users"}, operationId = "createUser", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<Object> createUser(
            @Parameter(description = "User object that needs to be added to the system. Cannot be null.", required = true)
            @Valid @RequestBody User user,
            @NotNull BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        if (result.hasErrors()) {
            result.getFieldErrors().forEach(
                    error -> errors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    /**
     * Updates a user in the system by their unique identifier.
     *
     * @param id     The unique identifier of the user to update.
     * @param user   The User object representing the updated user information.
     * @param result The BindingResult object containing any validation errors for the user object.
     * @return A ResponseEntity object with a HttpStatus NO_CONTENT(204) status code if the update is successful,
     * or a ResponseEntity object with a HttpStatus BAD_REQUEST (400) status code and a Map object containing
     * field errors if there are any validation errors.
     * @see User
     */
    @Operation(summary = "Update a user in the system by their unique identifier",
            description = "Updates a user in the system by their unique identifier.",
            tags = {"users"}, operationId = "updateUser", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User updated successfully",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(
            @Parameter(description = "ID of the user to be updated. Cannot be empty.", required = true)
            @PathVariable UUID id,
            @Valid @RequestBody User user,
            @NotNull BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        if (result.hasErrors()) {
            result.getFieldErrors().forEach(
                    error -> errors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        userService.updateUserById(id, user);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * Deletes a user from the system by their unique identifier.
     *
     * @param id The unique identifier of the user to delete.
     * @return A ResponseEntity object with a HttpStatus NO_CONTENT (204) status code if the deletion is successful.
     */
    @Operation(summary = "Delete a user from the system by their unique identifier",
            description = "Deletes a user from the system by their unique identifier.",
            tags = {"users"}, operationId = "deleteUser", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deleted successfully",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(
            @Parameter(description = "ID of the user to be deleted. Cannot be empty.", required = true)
                    @PathVariable UUID id) {
        userService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
