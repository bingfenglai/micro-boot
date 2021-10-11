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

package pers.lbf.microboot.common.i18n.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.server.WebFilter;
import pers.lbf.microboot.common.context.util.ApplicationContextHelper;
import pers.lbf.microboot.common.i18n.config.properties.I18nConfigProperties;
import pers.lbf.microboot.common.i18n.web.filter.LocaleMessageContextWebFilter;
import reactor.util.Logger;
import reactor.util.Loggers;

import static pers.lbf.microboot.common.i18n.constants.I18nConstants.AUTO_CONFIGURATION_PREFIX;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/10/9 9:31
 */
@Configuration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
@ConditionalOnProperty(prefix = AUTO_CONFIGURATION_PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
public class I18nAutoConfiguration {

    public static final String CUSTOM_MESSAGE_SOURCE = "customMessageSource";

    private static final Logger logger = Loggers.getLogger(IExtendI18nMessageSource.class);

    /**
     * 配置本地化消息资源文件,覆盖默认配置
     *
     * @param properties 国际化消息源属性
     * @return message bean
     */
    @Bean(CUSTOM_MESSAGE_SOURCE)
    public ResourceBundleMessageSource createMessageSource(@Qualifier("i18nConfigProperties") I18nConfigProperties properties) {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename(properties.getBaseFile());
        messageSource.setDefaultEncoding(properties.getDefaultEncoding());
        messageSource.setUseCodeAsDefaultMessage(properties.isUseCodeAsDefaultMessage());
        messageSource.setCacheMillis(properties.getCacheMillis());
        messageSource.setFallbackToSystemLocale(properties.isFallbackToSystemLocale());


        this.extendI18nMessageSource(messageSource);

        if (properties.isSpringMessageSourceIntegrationEnabled()) {
            this.extendSpringMessage(messageSource);
        }

        return messageSource;
    }

    private void extendI18nMessageSource(ResourceBundleMessageSource messageSource) {
        IExtendI18nMessageSource extendI18nMessageSource = ApplicationContextHelper.getBean(IExtendI18nMessageSource.class);

        if (null == extendI18nMessageSource) {
            logger.warn("IExtendI18nMessageSource instance is null");
            return;
        }
        logger.info("扩展了用户自定义的message source");
        extendI18nMessageSource.extendI18nMessageSource(messageSource);
    }

    private void extendSpringMessage(ResourceBundleMessageSource messageSource) {
        MessageSource springMessageSource;
        try {
            springMessageSource = ApplicationContextHelper.getBean("messageSource", MessageSource.class);

            if (null == messageSource.getParentMessageSource()) {
                messageSource.setParentMessageSource(springMessageSource);
            } else {
                MessageSource pms1 = messageSource.getParentMessageSource();
                if (pms1 instanceof ResourceBundleMessageSource) {
                    ResourceBundleMessageSource rbms = (ResourceBundleMessageSource) pms1;
                    rbms.setParentMessageSource(springMessageSource);
                } else {
                    throw new IllegalArgumentException("MessageSource parent must be an instance of ResourceBundleMessageSource");
                }
            }

            logger.info("扩展了用户自定义的spring message source");
        } catch (Exception ignore) {
        }

    }

    @Bean
    public WebFilter creteLocaleMessageWebFilter() {
        return new LocaleMessageContextWebFilter();
    }

}
