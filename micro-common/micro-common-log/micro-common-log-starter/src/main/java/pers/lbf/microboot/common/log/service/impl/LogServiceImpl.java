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

package pers.lbf.microboot.common.log.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pers.lbf.microboot.common.log.domain.entity.LogInfoEntity;
import pers.lbf.microboot.common.log.manager.LogDurabilityManager;
import pers.lbf.microboot.common.log.service.LogService;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.common.core.result.Result;


/**
 * 操作日志写入实现类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/10/5 13:13
 */
@Service
@AllArgsConstructor
@Slf4j
public class LogServiceImpl implements LogService {

    private final LogDurabilityManager logDurabilityManager;


    @Override
    public IResult<Boolean> save(LogInfoEntity logInfoEntity) {
        logDurabilityManager.saveLog(logInfoEntity);
        return Result.success();
    }
}
