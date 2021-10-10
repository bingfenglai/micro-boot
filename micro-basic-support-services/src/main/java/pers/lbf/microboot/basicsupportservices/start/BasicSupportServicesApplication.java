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
