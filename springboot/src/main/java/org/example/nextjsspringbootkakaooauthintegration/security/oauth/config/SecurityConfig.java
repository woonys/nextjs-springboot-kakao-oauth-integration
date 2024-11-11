package org.example.nextjsspringbootkakaooauthintegration.security.oauth.config;

import org.example.nextjsspringbootkakaooauthintegration.security.jwt.JwtFilter;
import org.example.nextjsspringbootkakaooauthintegration.security.oauth.service.CustomOAuth2UserService;
import org.example.nextjsspringbootkakaooauthintegration.security.oauth.service.OAuth2SuccessHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final CustomOAuth2UserService customOAuth2UserService;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;
    private final JwtFilter jwtFilter;
}
