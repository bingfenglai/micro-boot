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


import pers.lbf.microboot.common.context.util.ApplicationContextHelper;
import pers.lbf.microboot.common.log.config.DefaultLogConfigProperties;
import pers.lbf.microboot.common.log.constants.enums.LogDurabilityChannelEnum;
import pers.lbf.microboot.common.log.domain.entity.LogInfoEntity;

/**
 * 日志持久化处理器
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/10/6 0:03
 */
public abstract class AbstractLogDurabilityHandler {

    private final LogDurabilityChannelEnum channel;

    private static final DefaultLogConfigProperties LOG_CONFIG_PROPERTIES;

    private final AbstractLogDurabilityHandler next;

    static {
        LOG_CONFIG_PROPERTIES = ApplicationContextHelper.getBean(DefaultLogConfigProperties.class);
    }


    public AbstractLogDurabilityHandler(LogDurabilityChannelEnum channel, AbstractLogDurabilityHandler next) {
        this.channel = channel;
        this.next = next;
    }

    public void save(LogInfoEntity logInfo) {
        if (AbstractLogDurabilityHandler.LOG_CONFIG_PROPERTIES.getLogDurabilityChannel().equals(this.channel)) {
            this.doSave(logInfo);
            return;
        }

        if (this.next != null) {
            this.next.doSave(logInfo);
        }
    }

    /**
     * 持久化方法
     *
     * @param logInfo 日志信息
     */
    protected abstract void doSave(LogInfoEntity logInfo);

    protected abstract void saveOperationLog(LogInfoEntity logInfo);

    protected abstract void saveAuthLog(LogInfoEntity logInfo);

    protected abstract void saveTaskLog(LogInfoEntity logInfo);
}
