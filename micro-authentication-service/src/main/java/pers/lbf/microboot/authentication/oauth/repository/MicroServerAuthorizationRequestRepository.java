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

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.oauth2.client.web.server.ServerAuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/10/17 1:38
 */
@Component
@Slf4j
public class MicroServerAuthorizationRequestRepository implements ServerAuthorizationRequestRepository<OAuth2AuthorizationRequest> {

    @Override
    public Mono<OAuth2AuthorizationRequest> loadAuthorizationRequest(ServerWebExchange serverWebExchange) {
        return null;
    }

    @Override
    public Mono<Void> saveAuthorizationRequest(OAuth2AuthorizationRequest oAuth2AuthorizationRequest, ServerWebExchange serverWebExchange) {
        return null;
    }

    @Override
    public Mono<OAuth2AuthorizationRequest> removeAuthorizationRequest(ServerWebExchange serverWebExchange) {
        log.warn("进入到{}", this.getClass().getName());

        ServerHttpRequest request = serverWebExchange.getRequest();

        Map<String, String> queryParams = request.getQueryParams().toSingleValueMap();
        String state = queryParams.get("state");
        String registrationId = queryParams.get("registration_id");
        String code = queryParams.get("code");

        log.info("用户授权码 {},{},{}", registrationId, state, code);
        OAuth2AuthorizationRequest auth2AuthorizationRequest = OAuth2AuthorizationRequest
                .authorizationCode()
                .state(state)
                .authorizationUri("/login")
                .redirectUri("/oauth/token")
                .clientId("111")
                .attributes(stringObjectMap -> {
                    stringObjectMap.putAll(queryParams);
                })
                .build();

        return Mono.just(auth2AuthorizationRequest);
    }
}
