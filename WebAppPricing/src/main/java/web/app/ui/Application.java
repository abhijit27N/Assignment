package web.app.ui;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication

@ComponentScan("web.app.ui")

public class Application {

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);

	}

}