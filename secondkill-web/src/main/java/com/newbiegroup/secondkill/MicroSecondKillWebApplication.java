package com.newbiegroup.secondkill;

import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.stereotype.Repository;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/5/17 15:42
 */
@SpringBootApplication(scanBasePackages = {"com.newbiegroup.secondkill"})
@MapperScan(value = "com.newbiegroup.secondkill.dao", annotationClass = Repository.class)
@NacosPropertySource(dataId = "example", autoRefreshed = true)
public class MicroSecondKillWebApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(MicroSecondKillWebApplication.class);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MicroSecondKillWebApplication.class);
    }
}
