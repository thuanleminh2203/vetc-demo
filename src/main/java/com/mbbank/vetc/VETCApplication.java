package com.mbbank.vetc;

import com.mbbank.vetc.config.MongoProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(
		scanBasePackages = {"com.mbbank.vetc"},
		exclude = EmbeddedMongoAutoConfiguration.class)
@EnableMongoAuditing
@EnableConfigurationProperties(value = {MongoProperties.class})
@EnableFeignClients
@EnableScheduling
public class VETCApplication {
	public static void main(String[] args) {
		System.setProperty("java.net.useSystemProxies", "true");
//		System.setProperty("jdk.http.auth.tunneling.disabledSchemes", "false");
//		System.setProperty("jdk.http.auth.proxying.disabledSchemes", "false");
		SpringApplication.run(VETCApplication.class, args);
	}


}
