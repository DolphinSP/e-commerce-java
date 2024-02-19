package io.github.dolphin.software.users.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;


/**
 * Represents a user in the system.
 * <p>
 * The User class is an entity class that is mapped to the "users" table in the database.
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
 * This class also includes the lombok @Data annotation, which automatically generates getter and setter methods for the class properties.
 * </p>
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class User {

    @Id
    @GeneratedValue
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "user_id", updatable = false)
    private UUID id;

    @NotBlank(message = "{NotBlank.user.fullName}")
    @NotNull(message = "{NotNull.user.fullName}")
    @Column(name = "full_name", length = 60)
    private String fullName;

    @NotBlank(message = "{NotBlank.user.phone}")
    @NotNull(message = "{NotNull.user.phone}")
    @Column(length = 15)
    private String phone;

    @NotBlank(message = "{NotBlank.user.email}")
    @NotNull(message = "{NotNull.user.email}")
    @Email(message = "{Email.user.email}")
    @Column(unique = true, length = 60)
    private String email;

    @NotNull(message = "{NotNull.user.createDate}")
    @CreatedDate
    @Temporal(TemporalType.DATE)
    @Column(name = "create_date")
    private LocalDate createDate;

    @NotNull(message = "{NotNull.user.updateDate}")
    @LastModifiedDate
    @Temporal(TemporalType.DATE)
    @Column(name = "update_date")
    private LocalDate updateDate;

    @NotBlank(message = "{NotBlank.user.password}")
    @NotNull(message = "{NotNull.user.password}")
    private String password;


    /**
     * This method is annotated with @PrePersist, indicating that it is a callback method to be invoked before an entity is persisted.
     * When the entity is saved or updated to the database, this method will be automatically called.
     * <p>
     * The purpose of this method is to set the createDate property of the User object to the current date before it is persisted to the database.
     *
     * @see User
     */
    @PrePersist
    public void prePersist() {
        createDate = LocalDate.now();
        updateDate = LocalDate.now();
    }

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User user)) {
            return false;
        }
        return Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
