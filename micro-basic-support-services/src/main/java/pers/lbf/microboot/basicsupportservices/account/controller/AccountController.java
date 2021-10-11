package pers.lbf.microboot.basicsupportservices.account.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.lbf.microboot.common.core.domain.result.IResult;
import pers.lbf.microboot.common.i18n.constants.status.ServiceStatusEnums;
import pers.lbf.microboot.common.i18n.domain.result.Result;
import reactor.core.publisher.Mono;

/**
 * @author 赖柄沣
 * @since 2021/10/11 13:51
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {

    @GetMapping
    public Mono<IResult<String>> findAccountById() {
        return Mono.just(Result.error(ServiceStatusEnums.UNKNOWN_ERROR));
    }
}
