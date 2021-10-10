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


import pers.lbf.microboot.common.core.constant.ServiceStatusConstants;
import pers.lbf.microboot.common.core.status.IStatus;

/**
 * 微服务应用公共异常类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @Description TODO
 * @date 2020/12/18 10:21
 */
public class MicroServiceException extends RuntimeException implements IMicroException {

    /**
     * 错误消息提示
     */
    protected String message;

    /**
     * 异常编码
     */
    protected String exceptionCode;

    /**
     * 服务调用参数
     */
    protected Object[] params;

    /**
     * 异常所属模块
     */
    protected String serviceName;

    public static MicroServiceException getInstance(IStatus status) {
        return new MicroServiceException(status);
    }

    public static MicroServiceException getInstance(IStatus status, Object[] params, String module) {
        return new MicroServiceException(status.getCode(), status.getMessage(), params, module);
    }

    public static MicroServiceException getInstance() {
        return new MicroServiceException();
    }

    public MicroServiceException(IStatus status) {
        super();
        this.exceptionCode = status.getCode();
        this.message = status.getMessage();

    }

    public MicroServiceException() {
        this.message = ServiceStatusConstants.default_error_message;
        this.exceptionCode = ServiceStatusConstants.default_error_code;
    }

    public MicroServiceException(String message, String exceptionCode, Object[] params, String ServiceName) {
        super(message);
        this.message = message;
        this.exceptionCode = exceptionCode;
        this.params = params;
        this.serviceName = ServiceName;
    }

    public MicroServiceException(String message, String exceptionCode) {
        super(message);
        this.message = message;
        this.exceptionCode = exceptionCode;
    }

    public MicroServiceException(String message, Throwable cause, String exceptionCode, Object[] params, String ServiceName) {
        super(message, cause);
        this.message = message;
        this.exceptionCode = exceptionCode;
        this.params = params;
        this.serviceName = ServiceName;
    }

    public MicroServiceException(Throwable cause, String message, String exceptionCode, Object[] params, String ServiceName) {
        super(cause);
        this.message = message;
        this.exceptionCode = exceptionCode;
        this.params = params;
        this.serviceName = ServiceName;
    }

    public MicroServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String exceptionCode, Object[] parmas, String ServiceName) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.message = message;
        this.exceptionCode = exceptionCode;
        this.params = parmas;
        this.serviceName = ServiceName;
    }

    @Override
    public String getMessage() {
        return message;
    }

    /**
     * 异常状态码
     *
     * @return 异常状态码
     */
    @Override
    public String getCode() {
        return this.exceptionCode;
    }

    @Override
    public Object[] getParams() {
        return this.params;
    }

    @Override
    public String getServiceName() {
        return this.serviceName;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getExceptionCode() {
        return exceptionCode;
    }

    public void setExceptionCode(String exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
