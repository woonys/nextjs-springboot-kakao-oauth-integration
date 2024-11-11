package org.example.nextjsspringbootkakaooauthintegration.user.service;

import org.example.nextjsspringbootkakaooauthintegration.user.domain.User;
import org.example.nextjsspringbootkakaooauthintegration.user.dto.UserPrincipal;
import org.example.nextjsspringbootkakaooauthintegration.user.dto.UserResponseDto;
import org.example.nextjsspringbootkakaooauthintegration.user.exception.UserNotFoundException;
import org.example.nextjsspringbootkakaooauthintegration.user.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserResponseDto getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = ((UserPrincipal) authentication.getPrincipal()).getId();

        User user = userRepository.findById(userId)
                                  .orElseThrow(() -> new UserNotFoundException("User not found: userId=" + userId));

        return new UserResponseDto(user.getNickname(), user.getProfileImage());
    }
}
