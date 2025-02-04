package br.com.ifba.irebus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "br.com.ifba.irebus")
public class IrebusApplication {

	public static void main(String[] args) {
		SpringApplication.run(IrebusApplication.class, args);
	}
}
