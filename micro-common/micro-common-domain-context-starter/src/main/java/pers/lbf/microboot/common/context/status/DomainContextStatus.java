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

package pers.lbf.microboot.common.context.status;

import org.springframework.context.MessageSource;
import pers.lbf.microboot.common.context.util.ApplicationContextHelper;
import pers.lbf.microboot.common.core.status.IStatus;

import java.util.Locale;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/10/9 17:35
 */
public enum DomainContextStatus implements IStatus {


    /**
     * bean 未找到
     */
    BEAN_NOT_FOUND_STATU("dc001");

    private final String code;

    DomainContextStatus(String code) {
        this.code = code;
    }

    /**
     * 状态编码
     *
     * @return code
     */
    @Override
    public String getCode() {
        return this.code;
    }

    /**
     * 状态消息
     *
     * @return msg
     */
    @Override
    public String getMessage() {
        MessageSource messageSource = ApplicationContextHelper.getBean(MessageSource.class);
        return messageSource.getMessage(this.code, null, Locale.getDefault());
    }
}
