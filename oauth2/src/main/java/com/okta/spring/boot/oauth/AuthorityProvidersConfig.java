package com.okta.spring.boot.oauth;

import com.okta.spring.boot.oauth.config.OktaOAuth2Properties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @since 1.4.0
 */
@Configuration
class AuthorityProvidersConfig {

    @Bean
    AuthoritiesProvider tokenScopesAuthoritiesProvider() {
        return (user, userRequest) -> TokenUtil.tokenScopesToAuthorities(userRequest.getAccessToken());
    }

    @Bean
    @ConditionalOnProperty(name = "okta.oauth2.groupsClaim")
    AuthoritiesProvider groupClaims(OktaOAuth2Properties oktaOAuth2Properties) {
        return (user, userRequest) -> TokenUtil.tokenClaimsToAuthorities(user.getAttributes(), oktaOAuth2Properties.getGroupsClaim());
    }
}