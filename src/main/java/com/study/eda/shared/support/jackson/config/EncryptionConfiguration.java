package com.study.eda.shared.support.jackson.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.study.eda.shared.support.jackson.encryption.EncryptionModule;

@Configuration(proxyBeanMethods = false)
public class EncryptionConfiguration {

	@Bean
	public EncryptionModule encryptionModule() {
		return new EncryptionModule();
	}
}
