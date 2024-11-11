package org.example.nextjsspringbootkakaooauthintegration.security.oauth.service;

import org.example.nextjsspringbootkakaooauthintegration.security.jwt.JwtTokenProvider;
import org.example.nextjsspringbootkakaooauthintegration.security.oauth.domain.CustomUserDetails;
import org.example.nextjsspringbootkakaooauthintegration.security.oauth.domain.OAuthAttributes;
import org.example.nextjsspringbootkakaooauthintegration.security.oauth.exception.OAuth2AuthenticationProcessingException;
import org.example.nextjsspringbootkakaooauthintegration.user.domain.Provider;
import org.example.nextjsspringbootkakaooauthintegration.user.domain.User;
import org.example.nextjsspringbootkakaooauthintegration.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String redirectUri;

    @Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
    private String clientSecret;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        try {
            OAuthAttributes attributes = OAuthAttributes.of(registrationId, oAuth2User.getAttributes());
            User user = saveOrUpdate(attributes);
            return new CustomUserDetails(user, attributes.getAttributes());
        } catch (OAuth2AuthenticationProcessingException e) {
            log.error("OAuth2 인증 처리 중 예외 발생", e);
            throw new OAuth2AuthenticationException(
                new OAuth2Error("authentication_processing_error"),
                e.getMessage()
            );
        }
    }

    private User saveOrUpdate(OAuthAttributes attributes) {
        User user = userRepository.findByEmailAndProvider(attributes.getEmail(), Provider.KAKAO)
                                    .map(entity -> entity.updateProfile(attributes.getNickname(), attributes.getImageUrl()))
                                    .orElse(attributes.toEntity());

        return userRepository.save(user);
    }
}
