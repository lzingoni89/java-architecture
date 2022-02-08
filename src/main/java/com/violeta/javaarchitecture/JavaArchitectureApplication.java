package com.violeta.javaarchitecture;

import com.violeta.javaarchitecture.config.plugins.PluginBase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.plugin.core.config.EnablePluginRegistries;

@SpringBootApplication
@EnablePluginRegistries(PluginBase.class)
public class JavaArchitectureApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaArchitectureApplication.class, args);
	}

}
