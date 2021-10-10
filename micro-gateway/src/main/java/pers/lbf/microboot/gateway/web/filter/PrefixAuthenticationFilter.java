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

package pers.lbf.microboot.gateway.web.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import pers.lbf.microboot.common.core.exception.MicroServiceException;
import pers.lbf.microboot.common.i18n.constants.status.AuthStatusEnum;
import pers.lbf.microboot.common.utils.MicroStringUtils;
import pers.lbf.microboot.gateway.config.AccessWhiteProperties;
import pers.lbf.microboot.gateway.constants.OperationTypeConstant;
import pers.lbf.microboot.gateway.util.HttpUtils;
import reactor.core.publisher.Mono;

/**
 * 前置鉴权过滤器
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/7/8 21:11
 */
@Component
@Slf4j
public class PrefixAuthenticationFilter implements GlobalFilter, Ordered {

    /**
     * 在请求当中标记处理请求的开始时间的KEY值
     */
    private static final String START_TIME = "startTime";


    /**
     * 访问放行白名单
     */
    @Autowired
    private AccessWhiteProperties accessWhiteProperties;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1. 获取访问URI
        String path = exchange.getRequest().getURI().getPath();

        // 2. 判断访问路径是否在放行白名单当中
        if (!MicroStringUtils.matches(path, accessWhiteProperties.getWhites())) {
            log.warn("{} 访问 {} 鉴权不通过", HttpUtils.getIpAddress(exchange.getRequest()), path);
            throw new MicroServiceException(AuthStatusEnum.NO_TOKEN);
        }

        //2.2 token存在则放行

        //记录开始时间戳
        exchange.getAttributes().put(START_TIME, System.currentTimeMillis());


        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            //计算请求执行时间和记录访问日志
            Long startTime = exchange.getAttribute(START_TIME);
            if (startTime != null) {
                //计算 执行时间
                long executeTime = (System.currentTimeMillis() - startTime);

                log.info("访问IP: {}  " +
                                "请求路径: {}  " +
                                "执行耗时: {}ms  ",
                        HttpUtils.getIpAddress(exchange.getRequest()),
                        exchange.getRequest().getURI().getRawPath(),
                        executeTime);
            }


        }));
    }

    /**
     * 获取操作类型
     *
     * @param requestMethodName
     * @return 操作类型
     */
    public Integer getOperationType(String requestMethodName) {
        if ("GET".equalsIgnoreCase(requestMethodName)) {
            return OperationTypeConstant.SELECT.getValue();
        }

        if ("DELETE".equalsIgnoreCase(requestMethodName)) {
            return OperationTypeConstant.DELETE.getValue();
        }

        if ("POST".equalsIgnoreCase(requestMethodName)) {
            return OperationTypeConstant.INSTER.getValue();
        }

        if ("PUT".equalsIgnoreCase(requestMethodName)) {
            return OperationTypeConstant.UPDATE.getValue();
        }

        return OperationTypeConstant.OTHER.getValue();

    }


    @Override
    public int getOrder() {
        return -997;
    }
}
