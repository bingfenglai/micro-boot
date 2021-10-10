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

package pers.lbf.microboot.common.core.status;

import pers.lbf.microboot.common.core.constant.ServiceStatusConstants;

/**
 * 状态接口类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/7/9 0:55
 */
public interface IStatus {
    /**
     * 状态编码
     *
     * @return code
     */
    String getCode();

    /**
     * 状态消息
     *
     * @return msg
     */
    String getMessage();

    /**
     * 成功状态
     *
     * @return
     */
    static IStatus successStatus() {
        return new IStatus() {
            /**
             * 状态编码
             *
             * @return code
             */
            @Override
            public String getCode() {
                return ServiceStatusConstants.SUCCESSFUL_OPERATION_CODE;
            }

            /**
             * 状态消息
             *
             * @return msg
             */
            @Override
            public String getMessage() {
                return ServiceStatusConstants.SUCCESSFUL_OPERATION;
            }
        };
    }

    /**
     * 未知错误状态
     *
     * @return
     */
    static IStatus unknownStatus() {
        return new IStatus() {
            /**
             * 状态编码
             *
             * @return code
             */
            @Override
            public String getCode() {
                return ServiceStatusConstants.default_error_code;
            }

            /**
             * 状态消息
             *
             * @return msg
             */
            @Override
            public String getMessage() {
                return ServiceStatusConstants.default_error_message;
            }
        };
    }


}
