package com.mbbank.vetc.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.data.mongodb")
@Data
public class MongoProperties {
	private String uri;

	private String database;

	private boolean autoIndexCreation;
}
