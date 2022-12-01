package com.newbiegroup.secondkill.mq;

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
 * @date 2021/7/27 10:59
 */
@SpringBootApplication(scanBasePackages = {"com.newbiegroup.secondkill"})
@MapperScan(value = "com.newbiegroup.secondkill.dao", annotationClass = Repository.class)
public class MqApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(MqApplication.class);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MqApplication.class);
    }
}
