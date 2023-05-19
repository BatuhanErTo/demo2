package com.spaghettierol.demo2;

import com.spaghettierol.demo2.repository.ConvenerRepository;
import com.spaghettierol.demo2.repository.ModuleRepository;
import com.spaghettierol.demo2.repository.SessionRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class Demo2Application{
	public static void main(String[] args) {
		SpringApplication.run(Demo2Application.class, args);
	}

}
