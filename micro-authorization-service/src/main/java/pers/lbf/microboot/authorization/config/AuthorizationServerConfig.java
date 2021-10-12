package pers.lbf.microboot.authorization.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenGranter;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeTokenGranter;
import org.springframework.security.oauth2.provider.implicit.ImplicitTokenGranter;
import org.springframework.security.oauth2.provider.password.ResourceOwnerPasswordTokenGranter;
import org.springframework.security.oauth2.provider.refresh.RefreshTokenGranter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import java.util.ArrayList;
import java.util.List;

import static pers.lbf.microboot.authorization.constants.AuthorizationConstants.TOKEN_PERFIX;

/**
 * @author 赖柄沣
 * @since 2021/10/12 13:14
 */
@Configuration
@EnableAuthorizationServer
@AllArgsConstructor
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private final RedisConnectionFactory redisConnectionFactory;

    /**
     * 认证管理器
     */
    private final AuthenticationManager authenticationManager;

    /**
     * 配置令牌存储策略
     *
     * @return TokenStore
     */
    @Bean
    public TokenStore createRedisTokenStore() {
        RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
        tokenStore.setPrefix(TOKEN_PERFIX);
        return tokenStore;
    }


    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        super.configure(security);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        super.configure(clients);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenGranter(tokenGranter(endpoints));
        super.configure(endpoints);
    }


    /**
     * 授权模式配置，默认的OAUTH2的几种方式加 手机验证码
     *
     * @param endpoints
     * @return
     */
    private TokenGranter tokenGranter(AuthorizationServerEndpointsConfigurer endpoints) {
        List<TokenGranter> tokenGranterList = new ArrayList<>();

        //刷新token
        tokenGranterList.add(new RefreshTokenGranter(endpoints.getTokenServices(), endpoints.getClientDetailsService(), endpoints.getOAuth2RequestFactory()));
        //授权码模式
        tokenGranterList.add(new AuthorizationCodeTokenGranter(endpoints.getTokenServices(), endpoints.getAuthorizationCodeServices(), endpoints.getClientDetailsService(), endpoints.getOAuth2RequestFactory()));
        //客户端凭证模式
        tokenGranterList.add(new ClientCredentialsTokenGranter(endpoints.getTokenServices(), endpoints.getClientDetailsService(), endpoints.getOAuth2RequestFactory()));
        //密码模式
        tokenGranterList.add(new ResourceOwnerPasswordTokenGranter(authenticationManager, endpoints.getTokenServices(), endpoints.getClientDetailsService(), endpoints.getOAuth2RequestFactory()));
        //隐藏式
        tokenGranterList.add(new ImplicitTokenGranter(endpoints.getTokenServices(), endpoints.getClientDetailsService(), endpoints.getOAuth2RequestFactory()));
        return new CompositeTokenGranter(tokenGranterList);
    }
}
