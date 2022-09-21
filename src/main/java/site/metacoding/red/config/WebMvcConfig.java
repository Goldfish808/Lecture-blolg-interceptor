package site.metacoding.red.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import site.metacoding.red.handler.HelloInterceptor;
import site.metacoding.red.handler.Logininterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new Logininterceptor())
		.addPathPatterns("/s/**")	;
		registry.addInterceptor(new HelloInterceptor())
		.addPathPatterns("/hello/**");
		//.addPathPatterns("/admin/**") 이렇게 여러개를 추가 가능하고,
 		//.excludePathPatterns("/s/boards/**"); 이렇게 제외시킬 주소도 지정이 가능하다	
	}
}
