package io.github.dolphin.software.users.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The UserNotFoundException class serves as a specific type of RuntimeException in the application
 * to signify that a user entity is not located within the system's data source. This may typically
 * occur if a query for a specific user, whether by ID, username or other unique identifier, results
 * in no matches within the database or any other data source associated with user data.
 * <p>
 * Usage:
 * This exception should be thrown in service layer classes where the retrieval of user data occurs.
 * Upon retrieval attempt, if no user entity is found that matches the provided criteria, a new
 * instance of this exception should be thrown.
 *
 * <pre>
 * Usage example:
 * if(userRepository.findById(id) == null){
 *     throw new UserNotFoundException("User with id " + id + " not found");
 * }
 * </pre>
 * </p>
 * Structure:
 * The UserNotFoundException extends the base RuntimeException and thus, inherits all of its
 * methods. The main method to note is the getMessage() method that can be used to retrieve
 * the detailed error message that was passed in when the exception was thrown.
 *
 * <pre>
 * Exception handling example:
 * try{
 *     userService.getUserById(id);
 * } catch (UserNotFoundException ex){
 *     System.out.println(ex.getMessage());
 * }
 * </pre>
 *
 * <p>
 * In essence, the UserNotFoundException exists to provide a more descriptive and specific error when
 * the system is unable to locate a requested user in the datasource. Its use within the application
 * should be reserved for these cases.
 * </p>
 *
 * @see RuntimeException
 */
@ResponseBody
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

    /**
     * The UserNotFoundException class is a custom exception class that extends the RuntimeException class.
     * It is used to indicate that a user is not found in the system.
     *
     * <p>
     * The UserNotFoundException class is typically thrown when a user is not found in the database or any other data source.
     * It provides a message to describe the error and inherits the getMessage() method from the RuntimeException class to retrieve the error message.
     * </p>
     *
     * <p>
     * Example usage:
     * <pre>{@code
     * try {
     *     throw new UserNotFoundException("User not found");
     * } catch (UserNotFoundException e) {
     *     System.out.println(e.getMessage()); // Output: "User not found"
     * }
     * }</pre>
     * </p>
     *
     * @see RuntimeException
     */
    public UserNotFoundException(String messageNotfound) {
        super(messageNotfound);
    }
}
