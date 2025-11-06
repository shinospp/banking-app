package com.spp.banking_app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankingAppApplication {

	static Logger log = LoggerFactory.getLogger(BankingAppApplication.class);

	public static void main(String[] args) {
		log.info("inside BankingAppApplication......");
		log.info("second log......");
		SpringApplication.run(BankingAppApplication.class, args);
	}

}
