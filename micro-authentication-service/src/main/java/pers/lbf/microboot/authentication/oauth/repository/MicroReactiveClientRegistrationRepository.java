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
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/10/16 13:22
 */

@Repository
@Slf4j
public class MicroReactiveClientRegistrationRepository implements ReactiveClientRegistrationRepository {
    @Override
    public Mono<ClientRegistration> findByRegistrationId(String s) {
        log.info("加载客户端信息 {}", s);
        ClientRegistration clientRegistration = ClientRegistration.withRegistrationId(s)
                .clientId("111")
                .clientSecret("111")
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationUri("/login")
                .redirectUri("http://localhost:9002/login")
                .tokenUri("http://localhost:9002/oauth/token")
                .build();
        return Mono.just(clientRegistration);
    }
}
