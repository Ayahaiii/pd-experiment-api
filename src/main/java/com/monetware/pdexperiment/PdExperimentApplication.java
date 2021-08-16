package com.monetware.pdexperiment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

@EnableSwagger2
@SpringBootApplication
@MapperScan("com.monetware.pdexperiment.business.dao")
public class PdExperimentApplication {

    public static void main(String[] args) {
        SpringApplication.run(PdExperimentApplication.class, args);
    }

}
