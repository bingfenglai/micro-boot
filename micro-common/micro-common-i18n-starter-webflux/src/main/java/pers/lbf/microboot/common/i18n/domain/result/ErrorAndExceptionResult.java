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

package pers.lbf.microboot.common.i18n.domain.result;

import pers.lbf.microboot.common.core.domain.result.BaseResult;
import pers.lbf.microboot.common.core.domain.result.IResult;
import pers.lbf.microboot.common.core.status.IStatus;

/**
 * 异常处理封装结果类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/7/9 16:10
 */
public class ErrorAndExceptionResult extends BaseResult<String> implements IResult<String> {

    /**
     * 访问路径
     */
    private final String path;

    private boolean success;

    public static ErrorAndExceptionResult getInstance(IStatus status, String path) {
        return new ErrorAndExceptionResult(status, path);
    }

    private ErrorAndExceptionResult(IStatus status, String path) {
        this.path = path;
        this.code = status.getCode();
        this.message = status.getMessage();
    }

    @Override
    public String toString() {
        return "ErrorAndExceptionResult{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", path='" + path + '\'' +
                '}';
    }

    public String getPath() {
        return path;
    }

    @Override
    public Boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
