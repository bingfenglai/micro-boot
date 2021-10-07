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

package pers.lbf.microboot.common.log.handler;

import cn.hutool.core.bean.BeanUtil;
import pers.lbf.microboot.common.log.constants.enums.LogDurabilityChannelEnum;
import pers.lbf.microboot.common.log.domain.entity.LogInfoEntity;
import pers.lbf.microboot.common.log.domain.entity.OperationLogEntity;
import pers.lbf.microboot.common.log.domain.params.CreateAuthcLogParams;
import pers.lbf.microboot.common.log.domain.params.CreateOperationLogParams;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/10/6 0:28
 */
public class RpcLogDurabilityHandler extends AbstractLogDurabilityHandler {


    public RpcLogDurabilityHandler(LogDurabilityChannelEnum channel, AbstractLogDurabilityHandler next) {
        super(channel, next);
    }

    /**
     * 持久化方法
     *
     * @param logInfo 日志信息
     */
    @Override
    protected void doSave(LogInfoEntity logInfo) {
        if (logInfo instanceof CreateOperationLogParams) {
            this.saveOperationLog(logInfo);
            return;
        }

        if (logInfo instanceof CreateAuthcLogParams) {
            this.saveAuthLog(logInfo);
            return;
        }

        this.saveTaskLog(logInfo);
    }

    @Override
    protected void saveOperationLog(LogInfoEntity logInfo) {
        CreateOperationLogParams params = (CreateOperationLogParams) logInfo;
        OperationLogEntity operationLogEntity = BeanUtil.copyProperties(params, OperationLogEntity.class);

        // TODO
    }

    @Override
    protected void saveAuthLog(LogInfoEntity logInfo) {

    }

    @Override
    protected void saveTaskLog(LogInfoEntity logInfo) {

    }
}
