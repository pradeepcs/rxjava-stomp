package org.csp.re.rxj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */

@SpringBootApplication
public class Application {
	
	public static ConfigurableApplicationContext context = null;

	static {
		loadAppProperties();
	}

	public static void loadAppProperties() {
		context = new ClassPathXmlApplicationContext("/spring/integration/spring-config.xml");
	}
	
    public static void main(String... args) {
    	SpringApplication.run(Application.class, args);
    }
}
