package org.example.nextjsspringbootkakaooauthintegration.user.dto;

import org.example.nextjsspringbootkakaooauthintegration.user.domain.Role;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserPrincipal {
    private final Long id;
    private final String email;
    private final Role role;

    @Builder
    public UserPrincipal(Long id, String email, Role role) {
        this.id = id;
        this.email = email;
        this.role = role;
    }
}
