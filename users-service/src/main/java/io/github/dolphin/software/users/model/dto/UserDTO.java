package io.github.dolphin.software.users.model.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

/**
 * The UserDTO class represents a Data Transfer Object (DTO) for a user in the system.
 * It contains various properties to store user information.
 *
 * <p>
 * The UserDTO class is used to transfer user data between different layers of the application,
 * such as the service layer and the presentation layer. It helps to separate the entity object
 * from the data that is actually transferred, providing a simplified view of the user data.
 * </p>
 *
 * <p>
 * The UserDTO class includes properties such as id, fullName, phone, email, createDate, and updateDate.
 * These properties represent the unique identifier, full name, phone number, email address, creation date,
 * and last update date of the user, respectively.
 * </p>
 *
 * <p>
 * The UserDTO class is a simple POJO (Plain Old Java Object) class with private fields and public getter/setter methods.
 * It is annotated with the Lombok @Data annotation to generate the getter/setter methods, equals(), hashCode(), and toString() methods automatically.
 * </p>
 *
 * <p>
 * Example usage:
 * <pre>{@code
 * UserDTO user = new UserDTO();
 * user.setId(UUID.randomUUID());
 * user.setFullName("John Doe");
 * user.setPhone("1234567890");
 * user.setEmail("johndoe@example.com");
 * user.setCreateDate(LocalDate.now());
 * user.setUpdateDate(LocalDate.now());
 * }</pre>
 * </p>
 *
 * @see Data
 */
@Data
public class UserDTO {

    private UUID id;

    private String fullName;

    private String phone;

    private String email;

    private LocalDate createDate;

    private LocalDate updateDate;

}
