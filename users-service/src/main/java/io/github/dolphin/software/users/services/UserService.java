package io.github.dolphin.software.users.services;

import io.github.dolphin.software.users.model.User;
import io.github.dolphin.software.users.model.dto.UserDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * The UserService interface provides methods for managing users in the system.
 */
public interface UserService {

    /**
     * Finds all users in the system.
     *
     * @return A list of User objects representing all users in the system.
     */
    List<UserDTO> findAll();

    /**
     * Finds a user by their unique identifier.
     *
     * @param id The unique identifier of the user.
     * @return An Optional object containing the User if found, or an empty Optional if not found.
     */
    Optional<UserDTO> findById(UUID id);

    /**
     * Saves a user in the system.
     *
     * <p>
     * This method is used to persist a User object to the database.
     * If the user already exists in the database, it will update the existing record, otherwise it will create a new record.
     * The user object passed to this method will be modified to include the generated identifier and update date before saving.
     * </p>
     *
     * @param user The User object to save.
     * @return The saved User object with updated identifier and update date.
     * @see User
     */
    User save(User user);


    /**
     * Updates a user in the system by their unique identifier.
     *
     * <p>
     * This method is used to update an existing user in the system.
     * The user with the specified identifier will be updated with the provided user information.
     * If the user does not exist, no action will be taken.
     * </p>
     *
     * @param id The unique identifier of the user to update.
     * @param user The User object representing the updated user information.
     * @see User
     */
    void updateUserById(UUID id, User user);

    /**
     * Deletes a user from the system by their unique identifier.
     *
     * @param id The unique identifier of the user to delete.
     */
    void deleteById(UUID id);

    /**
     * Deletes a user from the system.
     *
     * <p>
     * This method permanently removes a user from the system. The user to be deleted is specified by the user parameter. The user will be deleted based on their unique identifier
     * , which is obtained from the {@link User#getId()} method.
     * </p>
     *
     * <p>
     * To delete a user, make sure the user object is not null and contains a valid identifier. The identifier should match an existing user in the system.
     * </p>
     *
     * @param user The User object representing the user to be deleted.
     */
    void delete(User user);


}
