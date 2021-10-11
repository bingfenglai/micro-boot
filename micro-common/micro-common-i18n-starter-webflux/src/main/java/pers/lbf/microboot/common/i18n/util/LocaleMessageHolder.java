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

package pers.lbf.microboot.common.i18n.util;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import pers.lbf.microboot.common.context.util.ApplicationContextHelper;
import pers.lbf.microboot.common.core.status.IStatus;

import static pers.lbf.microboot.common.i18n.config.I18nAutoConfiguration.CUSTOM_MESSAGE_SOURCE;

/**
 * 本地化消息提示工具类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/10/9 12:14
 */
@Component
@AllArgsConstructor
public class LocaleMessageHolder {

    public static String getMessage(IStatus status) {
        ResourceBundleMessageSource messageSource = ApplicationContextHelper.getBean(CUSTOM_MESSAGE_SOURCE, ResourceBundleMessageSource.class);
        return messageSource.getMessage(status.getCode(), null, LocaleContextHolder.getLocale());
    }

    public static String getMessage(IStatus status, Object[] args) {
        MessageSource messageSource = ApplicationContextHelper.getBean(CUSTOM_MESSAGE_SOURCE, MessageSource.class);
        return messageSource.getMessage(status.getCode(), args, LocaleContextHolder.getLocale());
    }


}
