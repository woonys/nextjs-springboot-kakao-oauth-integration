package org.example.nextjsspringbootkakaooauthintegration.user.domain;

import java.time.LocalDateTime;

import org.example.nextjsspringbootkakaooauthintegration.common.BaseTimeEntity;
import org.springframework.util.StringUtils;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String nickname;

    @Enumerated(EnumType.STRING)
    private Provider provider;

    private String providerId;

    private Role role = Role.USER;

    private String profileImage;

    private LocalDateTime lastLoginAt;

    @Builder
    public User(String email, String nickname, Provider provider, String providerId, String profileImage) {
        this.email = email;
        this.nickname = nickname;
        this.provider = provider;
        this.providerId = providerId;
        this.profileImage = profileImage;
    }

    public User updateProfile(String nickname, String profileImage) {
        if (StringUtils.hasText(nickname)) {
            this.nickname = nickname;
        }
        if (StringUtils.hasText(profileImage)) {
            this.profileImage = profileImage;
        }
        return this;
    }

    public void updateLastLoginAt() {
        this.lastLoginAt = LocalDateTime.now();
    }

}
