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
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/10/9 9:12
 */
public class MicroBusinessException extends MicroServiceException implements IMicroException {


    public static MicroBusinessException getInstance() {
        return new MicroBusinessException();
    }

    public static MicroBusinessException getInstance(IStatus status) {
        return new MicroBusinessException(status);
    }

    public static MicroBusinessException getInstance(IStatus status, Object[] params, String serviceName) {
        return new MicroBusinessException(status.getCode(), status.getMessage(), params, serviceName);
    }


    public MicroBusinessException(IStatus statusEnum) {
        super(statusEnum);
    }

    public MicroBusinessException() {
        super();
    }

    public MicroBusinessException(String message, String exceptionCode, Object[] params, String module) {
        super(message, exceptionCode, params, module);
    }

    public MicroBusinessException(String message, String exceptionCode) {
        super(message, exceptionCode);
    }


    public MicroBusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String exceptionCode, Object[] parmas, String module) {
        super(message, cause, enableSuppression, writableStackTrace, exceptionCode, parmas, module);

    }
}
