package io.github.dolphin.software.users.repository;

import io.github.dolphin.software.users.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * This interface is a repository for managing User entities.
 * <p>
 * The UserRepository interface extends the PagingAndSortingRepository interface,
 * which provides basic CRUD operations (Create, Read, Update, Delete) and sorting/paging functionality for User objects.
 * It is responsible for persisting User objects to the database and retrieving them.
 * </p>
 *
 * <p>
 * The User class is an entity class that represents a user in the system.
 * It stores information about a user, such as their full name, phone number, email, and password.
 * The class also includes timestamps for when the user was created and last updated.
 * </p>
 *
 * <p>
 * The User class includes various annotations for mapping the class properties to the corresponding database columns.
 * These annotations include @Entity, @Table, @Id, @GeneratedValue, @Column, @NotBlank, @NotNull, @Email, @Temporal, and @PrePersist.
 * </p>
 *
 * <p>
 * This interface inherits the methods from the PagingAndSortingRepository interface,
 * which include methods for saving, deleting, and finding User objects.
 * </p>
 */
@Repository
public interface UserRepository
        extends CrudRepository<User, UUID>, PagingAndSortingRepository<User, UUID> {
}
