package com.p2mj.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.p2mj.mall.dao")
public class P2mjMallApplication {

	public static void main(String[] args) {
		SpringApplication.run(P2mjMallApplication.class, args);
	}

}
