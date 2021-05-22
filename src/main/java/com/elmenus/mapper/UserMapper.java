package com.elmenus.mapper;


import com.elmenus.user.dto.UserDTO;
import com.elmenus.user.model.User;

public class UserMapper {

    public User mapDtoToEntity(UserDTO userDTO) {
        User userEntity = new User();

        userEntity.setEmail(userDTO.getEmail());
        userEntity.setAddress(userDTO.getAddress());
        userEntity.setPhone(userDTO.getPhone());

        return userEntity;
    }


    public UserDTO mapEntityToDTO(User entity) {

        if ( entity == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setAddress(entity.getAddress());
        userDTO.setEmail(entity.getEmail());
        userDTO.setPhone(entity.getPhone());


        return userDTO;
    }
}
