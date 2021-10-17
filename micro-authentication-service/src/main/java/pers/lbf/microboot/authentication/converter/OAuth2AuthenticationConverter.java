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

package pers.lbf.microboot.authentication.converter;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import pers.lbf.microboot.authentication.oauth.repository.MicroReactiveClientRegistrationRepository;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.function.Function;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/10/16 21:14
 */
@Slf4j
@Component
@AllArgsConstructor
public class OAuth2AuthenticationConverter implements ServerAuthenticationConverter, Function<ServerWebExchange, Mono<Authentication>> {

    private final MicroReactiveClientRegistrationRepository clientRegistrationRepository;


    /**
     * Converts a {@link ServerWebExchange} to an {@link Authentication}
     *
     * @param exchange The {@link ServerWebExchange}
     * @return A {@link Mono} representing an {@link Authentication}
     */
    @Override
    public Mono<Authentication> convert(ServerWebExchange exchange) {
        log.info("进入开放认证转换器");
        ServerHttpRequest request = exchange.getRequest();

        Map<String, String> map = request.getQueryParams().toSingleValueMap();
        log.info(map.toString());
        return this.apply(exchange);
    }

    /**
     * Applies this function to the given argument.
     *
     * @param webExchange the function argument
     * @return the function result
     */
    @Override
    public Mono<Authentication> apply(ServerWebExchange webExchange) {
        return webExchange.getFormData().map(this::createAuthentication);
    }

    private Authentication createAuthentication(MultiValueMap<String, String> data) {
        OAuth2AuthorizationRequest authorizationRequest = OAuth2AuthorizationRequest.authorizationCode().build();
        // OAuth2AuthorizationExchange authorizationExchange = new OAuth2AuthorizationExchange(authorizationRequest, OAuth2AuthorizationResponse.);

        //OAuth2LoginAuthenticationToken authentication = new OAuth2LoginAuthenticationToken(,authorizationExchange);
        //return authentication;

        return null;
    }
}
