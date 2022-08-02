package com.glauber.apigatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class ApiGatewayServerApplication {
	
	public static final String NFE_UPLOAD_SERVICE = "http://localhost:9001";
	public static final String NFE_PROCESS_SERVICE = "http://localhost:9002";
	
	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
			.route("upload", r -> r.path("/notas-fiscais")
					.filters(f -> f.addRequestHeader("accept", "application/text")
							.addRequestHeader("content-type", "multipart/form-data"))
					.uri(NFE_UPLOAD_SERVICE + "/notas-fiscais"))
			
			.route("list", r -> r.path("/notas-fiscais")
				.filters(f -> f.addRequestHeader("accept", "text/plain;charset=UTF8"))
				.uri(NFE_PROCESS_SERVICE + "/notas-fiscais"))
			
			.route("host_route", r -> r.host("*.myhost.org")
				.uri("http://httpbin.org"))
			.route("rewrite_route", r -> r.host("*.rewrite.org")
				.filters(f -> f.rewritePath("/foo/(?<segment>.*)", "/${segment}"))
				.uri("http://httpbin.org"))
//				.route("hystrix_route", r -> r.host("*.hystrix.org")
//					.filters(f -> f.hystrix(c -> c.setName("slowcmd")))
//					.uri("http://httpbin.org"))
//				.route("hystrix_fallback_route", r -> r.host("*.hystrixfallback.org")
//					.filters(f -> f.hystrix(c -> c.setName("slowcmd").setFallbackUri("forward:/hystrixfallback")))
//					.uri("http://httpbin.org"))
//				.route("limit_route", r -> r
//					.host("*.limited.org").and().path("/anything/**")
//					.filters(f -> f.requestRateLimiter(c -> c.setRateLimiter(redisRateLimiter())))
//					.uri("http://httpbin.org"))
			.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayServerApplication.class, args);
	}
}
	

