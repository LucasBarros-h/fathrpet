package com.fathrpet.mappers;

import com.fathrpet.model.dto.CreateUserDTO;
import com.fathrpet.model.dto.UserDTO;
import com.fathrpet.model.entity.User;

public class UserMapper {

    public static UserDTO toDTO(User user){
        if(user == null) return null;
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        return dto;
    }

    public static User toEntity(CreateUserDTO dto){
        if(dto == null) return null;
        User user = new User();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        return user;
    }
}
