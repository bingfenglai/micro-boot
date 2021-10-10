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

package pers.lbf.microboot.common.core.exception;

import pers.lbf.microboot.common.core.status.IStatus;

/**
 * 异常接口
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/10/9 9:13
 */
public interface IMicroException {

    /**
     * 异常消息提示
     *
     * @return 异常消息提示
     */
    String getMessage();

    /**
     * 异常状态码
     *
     * @return 异常状态码
     */
    String getCode();


    Object[] getParams();

    String getServiceName();

    static IMicroException getInstance(IStatus status, Object[] params, String serviceName) {
        return new IMicroException() {
            /**
             * 异常消息提示
             *
             * @return 异常消息提示
             */
            @Override
            public String getMessage() {
                return status.getMessage();
            }

            /**
             * 异常状态码
             *
             * @return 异常状态码
             */
            @Override
            public String getCode() {
                return status.getCode();
            }

            @Override
            public Object[] getParams() {
                return params;
            }

            @Override
            public String getServiceName() {
                return serviceName;
            }
        };
    }

    static IMicroException getInstance(IStatus status) {
        return IMicroException.getInstance(status, null, null);
    }
}
