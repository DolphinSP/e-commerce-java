package io.github.dolphin.software.users.model.dto.mapper;

import io.github.dolphin.software.users.model.User;
import io.github.dolphin.software.users.model.dto.UserDTO;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * The UserDTOMapper class is responsible for mapping a User object to a UserDTO object.
 * It provides methods for setting the properties of a User object and building a UserDTO object.
 */
public final class UserDTOMapper {

    private User user;

    private UserDTOMapper() {

    }

    /**
     * Returns a new instance of UserDTOMapper.
     * <p>
     * The UserDTOMapper class is responsible for mapping a User object to a UserDTO object.
     * It provides methods for setting the properties of a User object and building a UserDTO object.
     * </p>
     *
     * @return a new instance of UserDTOMapper
     */
    @Contract(value = " -> new", pure = true)
    public static @NotNull UserDTOMapper createMapper() {
        return new UserDTOMapper();
    }

    /**
     * Sets the User object for UserDTOMapper.
     * This method is used to set the User object that needs to be mapped to a UserDTO object.
     *
     * @param user the User object to be set
     * @return the updated UserDTOMapper instance
     */
    public UserDTOMapper withUser(User user) {
        this.user = user;
        return this;
    }

    /**
     * Builds a UserDTO object based on the properties of the User object set using the withUser method.
     *
     * @return a UserDTO object with the properties copied from the User object
     */
    public @NotNull UserDTO build() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFullName(user.getFullName());
        userDTO.setPhone(user.getPhone());
        userDTO.setEmail(user.getEmail());
        userDTO.setCreateDate(user.getCreateDate());
        userDTO.setUpdateDate(user.getUpdateDate());
        return userDTO;
    }
}
