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

import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.List;

/**
 * i18n消息源扩展接口
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/10/11 21:08
 */
public interface IExtendI18nMessageSource {

    /**
     * 留给用户扩展消息源的接口
     *
     * @return
     */
    ResourceBundleMessageSource createMessageSource();

    /**
     * 扩展多个消息源的接口
     *
     * @return
     */
    default List<ResourceBundleMessageSource> createMessageSourceList() {
        return null;
    }

    /**
     * 扩展消息源
     *
     * @param messageSource
     */
    default void extendI18nMessageSource(ResourceBundleMessageSource messageSource) {
        List<ResourceBundleMessageSource> messageSourceList = this.createMessageSourceList();
        if (null != messageSourceList && !messageSourceList.isEmpty()) {
            int i = 0;
            for (ResourceBundleMessageSource source : messageSourceList) {
                source.setParentMessageSource(messageSourceList.get(i++));
            }

            ResourceBundleMessageSource ms1 = this.createMessageSource();
            if (null == ms1) {
                messageSource.setParentMessageSource(messageSourceList.get(messageSourceList.size() - 1));
                return;
            }
            ms1.setParentMessageSource(messageSourceList.get(messageSourceList.size() - 1));
            messageSource.setParentMessageSource(ms1);

            return;
        }

        ResourceBundleMessageSource ms1 = this.createMessageSource();
        messageSource.setParentMessageSource(ms1);

    }
}
