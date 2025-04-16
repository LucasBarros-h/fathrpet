package com.fathrpet.model.dto;

import com.fathrpet.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String userName;
    private String email;

    public static UserDTO fromEntity(User user){
        return new UserDTO(
                user.getId(),
                user.getUserName(),
                user.getEmail()
        );
    }
}
