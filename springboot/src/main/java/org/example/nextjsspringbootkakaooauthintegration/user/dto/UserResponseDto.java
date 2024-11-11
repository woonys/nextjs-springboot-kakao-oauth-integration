package org.example.nextjsspringbootkakaooauthintegration.user.dto;

import org.springframework.stereotype.Service;

import lombok.Data;
import lombok.Getter;

@Data
public class UserResponseDto {
    private String nickname;
    private String profileImage;

    public UserResponseDto(String nickname, String profileImage) {
        this.nickname = nickname;
        this.profileImage = profileImage;
    }
}
