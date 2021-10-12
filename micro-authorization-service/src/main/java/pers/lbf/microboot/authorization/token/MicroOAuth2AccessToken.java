package pers.lbf.microboot.authorization.token;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

/**
 * 扩展令牌信息
 *
 * @author 赖柄沣
 * @since 2021/10/12 13:35
 */
public class MicroOAuth2AccessToken extends DefaultOAuth2AccessToken {
    public MicroOAuth2AccessToken(String value) {
        super(value);
    }

    public MicroOAuth2AccessToken(OAuth2AccessToken accessToken) {
        super(accessToken);
    }
}
