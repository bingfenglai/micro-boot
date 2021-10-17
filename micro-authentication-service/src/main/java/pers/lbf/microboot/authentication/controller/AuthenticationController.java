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

package pers.lbf.microboot.authentication.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.lbf.microboot.common.core.domain.result.IResult;
import pers.lbf.microboot.common.i18n.domain.result.Result;
import reactor.core.publisher.Mono;
import reactor.netty.http.server.HttpServerRequest;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/10/14 20:34
 */
@RestController
public class AuthenticationController {


    @PostMapping("/login")
    public Mono<IResult<Object>> login(HttpServerRequest request) {
        return Mono.just(Result.ok(request.params()));
    }
}
