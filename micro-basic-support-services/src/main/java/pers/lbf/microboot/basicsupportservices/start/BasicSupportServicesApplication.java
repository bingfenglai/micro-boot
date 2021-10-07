/*
 *    Copyright (c) 2018-2025, shenghua All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: shenghua
 */

package pers.lbf.microboot.basicsupportservices.start;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 基础支撑服务应用启动类
 *
 * @author 赖柄沣
 * @since 2021/10/7 13:43
 */
@Slf4j
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "pers.lbf.microboot.basicsupportservices")
public class BasicSupportServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(BasicSupportServicesApplication.class, args);
        log.info("===========================================");
        log.info("=====+++++++++基础支撑服务启动成功+++++++======");
        log.info("===========================================");
    }
}
