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

import pers.lbf.microboot.common.context.constant.ServiceStatusConstant;

import java.io.Serializable;
import java.util.Objects;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/7/9 0:23
 */
public abstract class BaseResult<T> implements IResult<T>, Serializable {

    /**
     * 响应代码
     */
    protected String code;

    /**
     * 响应消息
     */
    protected String message;


    public BaseResult() {
        this.code = ServiceStatusConstant.SUCCESSFUL_OPERATION_CODE;
        this.message = ServiceStatusConstant.SUCCESSFUL_OPERATION;
    }

    public BaseResult(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 获取响应代码
     *
     * @return code
     */
    @Override
    public String getCode() {
        return this.code;
    }

    /**
     * 获取响应消息
     *
     * @return message
     */
    @Override
    public String getMessage() {
        return this.message;
    }

    /**
     * 请求是否成功
     *
     * @return success flag
     */
    @Override
    public Boolean isSuccess() {
        return Objects.equals(this.code, ServiceStatusConstant.SUCCESSFUL_OPERATION_CODE);
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 获取响应数据
     *
     * @return t
     */
    @Override
    public T getData() {
        return null;
    }

    @Override
    public String toString() {
        return "BaseResult{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
