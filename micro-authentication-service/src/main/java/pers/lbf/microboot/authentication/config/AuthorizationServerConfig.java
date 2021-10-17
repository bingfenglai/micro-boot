package pers.lbf.microboot.authentication.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizationCodeAuthenticationTokenConverter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import pers.lbf.microboot.authentication.converter.OAuth2AuthenticationConverter;
import pers.lbf.microboot.authentication.handler.MicroAuthenticationFailureHandler;
import pers.lbf.microboot.authentication.handler.MicroAuthenticationSuccessHandler;
import pers.lbf.microboot.authentication.oauth.repository.MicroReactiveClientRegistrationRepository;
import pers.lbf.microboot.authentication.oauth.repository.MicroServerAuthorizationRequestRepository;
import pers.lbf.microboot.authentication.oauth.repository.MicroServerOAuth2AuthorizedClientRepository;
import pers.lbf.microboot.authentication.oauth.service.MicroReactiveOAuth2AuthorizedClientServiceImpl;

/**
 * @author 赖柄沣
 * @since 2021/10/12 13:14
 */
@Configuration
@AllArgsConstructor
@EnableWebFluxSecurity
@Primary
public class AuthorizationServerConfig {

    private final MicroAuthenticationSuccessHandler authenticationSuccessHandler;

    private final MicroAuthenticationFailureHandler authenticationFailureHandler;

    private final PasswordEncoderConfig passwordEncoderConfig;

    private final MicroReactiveOAuth2AuthorizedClientServiceImpl reactiveOAuth2AuthorizedClientService;

    private final MicroReactiveClientRegistrationRepository clientRegistrationRepository;

    private final OAuth2AuthenticationConverter oAuth2AuthenticationConverter;

    private final MicroServerOAuth2AuthorizedClientRepository serverOAuth2AuthorizedClientRepository;

    private final MicroServerAuthorizationRequestRepository serverAuthorizationRequestRepository;

    @Bean
    @Primary
    public SecurityWebFilterChain createSecurityWebFilterChain(ServerHttpSecurity http) {
        return http
                .formLogin()
                .authenticationSuccessHandler(authenticationSuccessHandler)
                .authenticationFailureHandler(authenticationFailureHandler)
                .and()
                .authorizeExchange()
                .pathMatchers("/oauth/*", "/login/**").permitAll()
                .anyExchange().authenticated()
                .and()
                .oauth2Login()
                .authenticationConverter(createAuthenticationConverter())
                .authenticationFailureHandler(authenticationFailureHandler)
                .authenticationSuccessHandler(authenticationSuccessHandler)
                .authorizedClientService(reactiveOAuth2AuthorizedClientService)
                .clientRegistrationRepository(clientRegistrationRepository)
                .and()
                .httpBasic().disable()
                .csrf().disable()
                .build();

    }

    private ServerAuthenticationConverter createAuthenticationConverter() {

        ServerOAuth2AuthorizationCodeAuthenticationTokenConverter authenticationTokenConverter = new ServerOAuth2AuthorizationCodeAuthenticationTokenConverter(clientRegistrationRepository);

        authenticationTokenConverter.setAuthorizationRequestRepository(serverAuthorizationRequestRepository);


        return authenticationTokenConverter;
    }

    /**
     * 密码加密器配置
     *
     * @return
     */
    @Bean
    public PasswordEncoder createPasswordEncoder() {
        return passwordEncoderConfig.getDefaultPasswordEncoder();
    }


}
