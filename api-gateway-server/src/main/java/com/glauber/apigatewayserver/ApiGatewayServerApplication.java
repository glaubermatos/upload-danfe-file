package com.glauber.apigatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;

@SpringBootApplication
@EnableEurekaClient
public class ApiGatewayServerApplication {
	
	public static final String NFE_UPLOAD_SERVICE = "http://localhost:9001";
	public static final String NFE_PROCESS_SERVICE = "http://localhost:9002";
	
	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
			
			.route("upload", r -> r.path("/notas-fiscais")
					.and()
					.method(HttpMethod.POST)
					.uri("lb://nfe-upload-service"+"/notas-fiscais"))
			
			.route("list", r -> r.path("/notas-fiscais")
					.and()
					.method(HttpMethod.GET)
					.uri("lb://nfe-process-service"+"/notas-fiscais"))
			
			.route("duplicates", r -> r.path("/notas-fiscais/{numeroNotaFiscal}/duplicatas")
					.and()
					.method(HttpMethod.GET)
					.filters(f -> f.rewritePath("/(?<numeroNotaFiscal>)/duplicatas", "/${numeroNotaFiscal}"))
					.uri("lb://nfe-process-service"+"/notas-fiscais"))

			.route("delete", r -> r.path("/notas-fiscais/{numeroNotaFiscal}")
					.and()
					.method(HttpMethod.DELETE)
					.filters(f -> f.rewritePath("/(?<numeroNotaFiscal>)", "/${numeroNotaFiscal}"))
					.uri("lb://nfe-process-service"+"/notas-fiscais"))
			.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayServerApplication.class, args);
	}
}
	

