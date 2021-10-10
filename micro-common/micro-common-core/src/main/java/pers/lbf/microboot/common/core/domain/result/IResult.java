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

package pers.lbf.microboot.common.core.domain.result;

import pers.lbf.microboot.common.core.status.IStatus;

/**
 * json响应结果接口
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/7/9 0:20
 */
public interface IResult<T> {

    /**
     * 获取响应代码
     *
     * @return code
     */
    String getCode();

    /**
     * 获取响应消息
     *
     * @return message
     */
    String getMessage();

    /**
     * 获取响应数据
     *
     * @return t
     */
    T getData();

    /**
     * 请求是否成功
     *
     * @return success flag
     */
    Boolean isSuccess();

    /**
     * 默认成功的响应
     *
     * @return
     */
    static IResult<Boolean> success() {
        return new IResult<Boolean>() {
            /**
             * 获取响应代码
             *
             * @return code
             */
            @Override
            public String getCode() {
                return IStatus.successStatus().getCode();
            }

            /**
             * 获取响应消息
             *
             * @return message
             */
            @Override
            public String getMessage() {
                return IStatus.unknownStatus().getMessage();
            }

            /**
             * 获取响应数据
             *
             * @return t
             */
            @Override
            public Boolean getData() {
                return true;
            }

            /**
             * 请求是否成功
             *
             * @return success flag
             */
            @Override
            public Boolean isSuccess() {
                return true;
            }
        };
    }
}
