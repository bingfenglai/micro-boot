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

package pers.lbf.common.authorization.authorization;

import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * 鉴权管理器
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/10/14 23:33
 */
@Component
public class MicroReactiveAuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    /**
     * Determines if access is granted for a specific authentication and object.
     *
     * @param authentication the Authentication to check
     * @param object         the object to check
     * @return an decision or empty Mono if no decision could be made.
     */
    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> authentication, AuthorizationContext object) {
        return null;
    }

    /**
     * Determines if access should be granted for a specific authentication and object
     *
     * @param authentication the Authentication to check
     * @param object         the object to check
     * @return an empty Mono if authorization is granted or a Mono error if access is
     * denied
     */
    @Override
    public Mono<Void> verify(Mono<Authentication> authentication, AuthorizationContext object) {
        return null;
    }
}
