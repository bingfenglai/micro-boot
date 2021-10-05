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

package pers.lbf.microboot.common.log.domain.params;

import lombok.Data;
import pers.lbf.microboot.common.log.domain.entity.LogInfoEntity;

/**
 * 创建认证日志
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/10/5 23:58
 */

@Data
public class CreateAuthcLogParams implements LogInfoEntity {
    private CreateOperationLogParams createOperationLogParams;

    /**
     * 业务规则时间内请求认证次数
     */
    private Integer count;
}
