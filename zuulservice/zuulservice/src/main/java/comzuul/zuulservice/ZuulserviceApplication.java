package comzuul.zuulservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import comzuul.zuulservice.filter.ErrorFilter;
import comzuul.zuulservice.filter.PostFilter;
import comzuul.zuulservice.filter.PreFilter;
import comzuul.zuulservice.filter.RouteFilter;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class ZuulserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulserviceApplication.class, args);
	}

	@Bean
	public PreFilter preFilter() {
		return new PreFilter();
	}

	@Bean
	public PostFilter postFilter() {
		return new PostFilter();
	}

	@Bean
	public ErrorFilter errorFilter() {
		return new ErrorFilter();
	}

	@Bean
	public RouteFilter routeFilter() {
		return new RouteFilter();
	}

}
