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

package pers.lbf.microboot.authentication.oauth.repository;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizedClientRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/10/16 13:44
 */
@Component
public class MicroServerOAuth2AuthorizedClientRepository implements ServerOAuth2AuthorizedClientRepository {
    @Override
    public <T extends OAuth2AuthorizedClient> Mono<T> loadAuthorizedClient(String s, Authentication authentication, ServerWebExchange serverWebExchange) {
        return null;
    }

    @Override
    public Mono<Void> saveAuthorizedClient(OAuth2AuthorizedClient oAuth2AuthorizedClient, Authentication authentication, ServerWebExchange serverWebExchange) {
        return null;
    }

    @Override
    public Mono<Void> removeAuthorizedClient(String s, Authentication authentication, ServerWebExchange serverWebExchange) {
        return Mono.empty();
    }
}
