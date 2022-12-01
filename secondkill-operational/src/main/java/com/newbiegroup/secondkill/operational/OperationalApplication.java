package com.newbiegroup.secondkill.operational;

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
 * @date 2022/1/4 15:39
 */
@SpringBootApplication(scanBasePackages = {"com.newbiegroup.secondkill"})
@MapperScan(value = "com.newbiegroup.secondkill.dao", annotationClass = Repository.class)
public class OperationalApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(OperationalApplication.class);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(OperationalApplication.class);
    }
}
