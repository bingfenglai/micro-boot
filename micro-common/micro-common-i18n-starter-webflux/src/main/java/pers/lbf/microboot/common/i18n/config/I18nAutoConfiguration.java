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

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.server.WebFilter;
import pers.lbf.microboot.common.context.util.ApplicationContextHelper;
import pers.lbf.microboot.common.i18n.config.properties.LocaleChangeConfigProperties;
import pers.lbf.microboot.common.i18n.web.filter.LocaleMessageContextWebFilter;

import static pers.lbf.microboot.common.i18n.constants.I18nConstants.AUTO_CONFIGURATION_PREFIX;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/10/9 9:31
 */
@Configuration
@EnableConfigurationProperties(LocaleChangeConfigProperties.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
@ConditionalOnProperty(prefix = AUTO_CONFIGURATION_PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
public class I18nAutoConfiguration {

    public static final String CUSTOM_MESSAGE_SOURCE = "customMessageSource";

    /**
     * 配置本地化消息资源文件,覆盖默认配置
     *
     * @param properties 国际化消息源属性
     * @return message bean
     */
    @Bean(CUSTOM_MESSAGE_SOURCE)
    public MessageSource createMessageSource(LocaleChangeConfigProperties properties) {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename(properties.getBaseName());
        messageSource.setDefaultEncoding(properties.getDefaultEncoding());
        messageSource.setUseCodeAsDefaultMessage(properties.isUseCodeAsDefaultMessage());
        messageSource.setCacheMillis(properties.getCacheMillis());
        messageSource.setFallbackToSystemLocale(properties.isFallbackToSystemLocale());
        MessageSource parentMessageSource = this.getMessageParent();

        if (parentMessageSource != null) {
            messageSource.setParentMessageSource(parentMessageSource);
        }
        return messageSource;
    }

    private MessageSource getMessageParent() {
        MessageSource messageSource = null;
        try {
            messageSource = ApplicationContextHelper.getBean("messageSource", MessageSource.class);
        } catch (Exception ignore) {
        }

        return messageSource;
    }

    @Bean
    public WebFilter creteLocaleMessageWebFilter() {
        return new LocaleMessageContextWebFilter();
    }

}
