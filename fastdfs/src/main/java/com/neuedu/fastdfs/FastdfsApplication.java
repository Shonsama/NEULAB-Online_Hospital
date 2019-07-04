package com.neuedu.fastdfs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan("com.neuedu.fastdfs.model.mapper")
public class FastdfsApplication {
	public static void main(String[] args) {
		SpringApplication.run(FastdfsApplication.class, args);
	}

}
