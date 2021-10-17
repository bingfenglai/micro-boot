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

package pers.lbf.microboot.authentication.handler;

import com.alibaba.nacos.common.utils.JacksonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * 认证成功处理器
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/10/14 20:30
 */
@Slf4j
@Component
public class MicroAuthenticationSuccessHandler implements ServerAuthenticationSuccessHandler {
    /**
     * Invoked when the application authenticates successfully
     *
     * @param webFilterExchange the exchange
     * @param authentication    the {@link Authentication}
     * @return a completion notification (success or error)
     */
    @Override
    public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
        log.info("走了认证成功处理器");
        log.info(authentication.getPrincipal().toString());
        log.info(authentication.toString());
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;

        byte[] bytes = JacksonUtils.toJsonBytes(token);

        ServerHttpResponse response = webFilterExchange.getExchange().getResponse();

        DataBufferFactory bufferFactory = response.bufferFactory();
        return response.writeWith(
                Mono.just(bufferFactory.wrap(bytes))
        );

    }
}
