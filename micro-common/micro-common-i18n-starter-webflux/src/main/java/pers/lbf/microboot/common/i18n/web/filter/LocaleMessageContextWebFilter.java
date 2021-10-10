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

package pers.lbf.microboot.common.i18n.web.filter;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import reactor.util.Logger;
import reactor.util.Loggers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

/**
 * locale 拦截器
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/10/10 22:30
 */
public class LocaleMessageContextWebFilter implements WebFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        List<Locale> localeList = exchange.getRequest().getHeaders().getAcceptLanguageAsLocales();
        Logger logger = Loggers.getLogger(LocaleMessageContextWebFilter.class);
        if (!localeList.isEmpty()) {
            logger.debug("{} 存储当前会话的本地化信息", LocalDateTime.now());
            LocaleContextHolder.setLocale(localeList.get(0));
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -999;
    }
}
