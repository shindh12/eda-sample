package com.study.eda.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.MySQLContainer;

@TestConfiguration(proxyBeanMethods = false)
class TestEdaSampleApplication {

	public static void main(String[] args) {
		SpringApplication.from(BookLibraryApplication::main).with(TestEdaSampleApplication.class).run(args);
	}

	@Bean
	@ServiceConnection
	MySQLContainer<?> mysqlContainer() {
		return new MySQLContainer<>("mysql:latest");
	}

}
