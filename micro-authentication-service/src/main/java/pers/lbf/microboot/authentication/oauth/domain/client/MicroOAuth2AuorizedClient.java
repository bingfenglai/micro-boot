/*
 * Copyright 2020 赖柄沣 bingfengdev@aliyun.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package pers.lbf.microboot.authentication.oauth.domain.client;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/10/16 13:52
 */
public class MicroOAuth2AuorizedClient extends OAuth2AuthorizedClient {
    public MicroOAuth2AuorizedClient(ClientRegistration clientRegistration, String principalName, OAuth2AccessToken accessToken) {
        super(clientRegistration, principalName, accessToken);
    }

    public MicroOAuth2AuorizedClient(ClientRegistration clientRegistration, String principalName, OAuth2AccessToken accessToken, OAuth2RefreshToken refreshToken) {
        super(clientRegistration, principalName, accessToken, refreshToken);
    }
}
