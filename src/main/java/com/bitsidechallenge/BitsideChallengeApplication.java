package com.bitsidechallenge;

import com.bitsidechallenge.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.shell.standard.ShellMethod;

@SpringBootApplication
public class BitsideChallengeApplication {



	public static void main(String[] args) {
		SpringApplication.run(BitsideChallengeApplication.class, args);
	}

}
