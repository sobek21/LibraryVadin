package com.example.application.mapper;

import com.example.application.domain.User;
import com.example.application.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    @Autowired
    private BookMapper bookMapper;

    public User mapToUser (final UserDto userDto) {
        return new User(

        );
    }
    public UserDto mapToUserDto(final User user) {
        return new UserDto(


        );
    }
}
