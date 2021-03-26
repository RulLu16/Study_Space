package com.example.demo.service;

import com.example.demo.entity.GoogleUser;
import com.example.demo.repository.GoogleUserRepository;
import com.example.demo.vo.OAuthAttributes;
import com.example.demo.vo.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final GoogleUserRepository googleUserRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        // 로그인 진행중 서비스 구분 코드. 구글, 네이버인지 구분 위해
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        // 로그인 진행 시 키가 되는 필드값. primary key.
        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        // user의 attribute를 담음
        OAuthAttributes attributes = OAuthAttributes.
                of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        GoogleUser googleUser = saveOrUpdate(attributes);
        httpSession.setAttribute("googleUser", new SessionUser(googleUser));

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(googleUser.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

    private GoogleUser saveOrUpdate(OAuthAttributes attributes) {
        GoogleUser googleUser = googleUserRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(),attributes.getPicture()))
                .orElse(attributes.toEntity());

        return googleUserRepository.save(googleUser);
    }
}
