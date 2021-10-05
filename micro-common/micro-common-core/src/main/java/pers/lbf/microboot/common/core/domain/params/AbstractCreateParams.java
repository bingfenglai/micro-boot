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

package pers.lbf.microboot.common.core.domain.params;

import java.time.LocalDateTime;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/10/5 23:11
 */
public abstract class AbstractCreateParams implements ICreateParams {

    private LocalDateTime createdTime;

    private String createdBy;

    @Override
    public LocalDateTime getCreatedTime() {
        return this.createdTime;
    }

    @Override
    public String getCreatedBy() {
        return this.createdBy;
    }


    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }


    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
