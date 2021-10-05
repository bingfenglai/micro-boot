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

import pers.lbf.microboot.common.context.status.IStatus;
import pers.lbf.microboot.common.context.status.impl.ServiceStatusEnum;

import java.io.Serializable;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/7/9 0:35
 */
public class Result<T> extends BaseResult<T> implements IResult<T>, Serializable {

    /**
     * 响应的数据
     */
    private T data;


    public static <T> IResult<T> ok(T data) {
        return new Result<>(data);
    }

    public static <T> IResult<T> success() {
        return new Result<>(ServiceStatusEnum.OK);

    }


    public static <T> IResult<T> error(IStatus status) {
        return new Result<>(status);
    }

    public static <T> IResult<T> error(String message) {
        return new Result<>(ServiceStatusEnum.UNKNOWN_ERROR.getCode(), message);
    }


    public static <T> IResult<T> error() {
        return new Result<>(ServiceStatusEnum.UNKNOWN_ERROR);
    }


    private Result(T data) {
        super();
        this.data = data;
    }

    private Result(String code, String message) {
        super(code, message);
    }

    private Result(IStatus status) {
        super(status.getCode(), status.getMessage());
    }


    /**
     * 获取响应数据
     *
     * @return t
     */
    @Override
    public T getData() {

        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

}
