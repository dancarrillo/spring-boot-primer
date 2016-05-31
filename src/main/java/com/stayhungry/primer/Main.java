package com.stayhungry.primer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.stayhungry.primer")
@EnableAutoConfiguration
/**
 * 
 * @author dcarrillo
 *
 */
public class Main extends SpringBootServletInitializer{
	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}
}