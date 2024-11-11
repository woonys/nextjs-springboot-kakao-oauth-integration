package org.example.nextjsspringbootkakaooauthintegration.security.oauth.domain;

import java.util.Map;

import org.example.nextjsspringbootkakaooauthintegration.security.oauth.exception.OAuth2AuthenticationProcessingException;
import org.example.nextjsspringbootkakaooauthintegration.user.domain.Provider;
import org.example.nextjsspringbootkakaooauthintegration.user.domain.User;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String nickname;
    private String email;
    private String imageUrl;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey,
                           String nickname, String email, String imageUrl) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.nickname = nickname;
        this.email = email;
        this.imageUrl = imageUrl;
    }

    public static OAuthAttributes of(String registrationId, Map<String, Object> attributes) throws OAuth2AuthenticationProcessingException {
        if ("kakao".equals(registrationId)) {
            return ofKakao(attributes);
        }
        throw new OAuth2AuthenticationProcessingException("Login with " + registrationId + " is not supported");
    }

    @SuppressWarnings("unchecked")
    private static OAuthAttributes ofKakao(Map<String, Object> attributes) {
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");

        return OAuthAttributes.builder()
                              .nickname((String) profile.get("nickname"))
                              .email((String) kakaoAccount.get("email"))
                              .imageUrl((String) profile.get("profile_image_url"))  // 이미지 URL 추출
                              .attributes(attributes)
                              .nameAttributeKey("id")
                              .build();
    }

    public User toEntity() {
        return User.builder()
                   .nickname(nickname)
                   .email(email)
                   .provider(Provider.KAKAO)
                   .providerId(attributes.get(nameAttributeKey).toString())
                   .build();
    }
}
