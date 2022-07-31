package com.glauber.nfeuploadservice.core.xml;

import org.jdom2.input.SAXBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class XmlConfig {

	@Bean
	public SAXBuilder saxBuilder() {
		return new SAXBuilder();
	}
}
