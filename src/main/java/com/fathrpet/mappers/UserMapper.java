package com.fathrpet.mappers;

import com.fathrpet.model.dto.UserDTO;
import com.fathrpet.model.entity.User;

public class UserMapper {

    public static UserDTO toDTO(User user){
        if(user == null) return null;
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUserName(user.getUserName());
        dto.setEmail(user.getEmail());
        return dto;
    }

    public static User toEntity(UserDTO dto){
        if(dto == null) return null;
        User user = new User();
        user.setId(dto.getId());
        user.setUserName(dto.getUserName());
        user.setEmail(dto.getEmail());
        return user;
    }
}
