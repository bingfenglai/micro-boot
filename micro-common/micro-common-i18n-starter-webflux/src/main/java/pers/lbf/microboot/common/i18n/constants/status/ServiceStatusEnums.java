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

package pers.lbf.microboot.common.i18n.constants.status;


import java.io.Serializable;

/**
 * 通用服务状态枚举
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/7/9 0:54
 */
public enum ServiceStatusEnums implements I18nStatus, Serializable {

    /**
     * 一切ok
     */
    OK("操作成功", "00000"),

    /**
     * 业务操作 结果未不满足条件的情况， 而非系统引起的异常
     */
    FAILED("操作失败", "00001"),

    /**
     * 未知异常
     */
    UNKNOWN_ERROR("未知异常", "e9999");

    /**
     * 消息
     */
    private final String message;

    /**
     * 代码
     */
    private final String code;


    ServiceStatusEnums(String message, String code) {
        this.message = message;
        this.code = code;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "ServiceStatus{" +
                "message='" + message + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

}
