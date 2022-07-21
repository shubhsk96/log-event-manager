package com.creditsuisse.assignment;

import com.creditsuisse.assignment.log.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.ObjectUtils;

/**
 * Entry point class for spring application
 * @author Shubham K
 */
@SpringBootApplication
public class JavaApplication implements ApplicationRunner {

	@Autowired
	private LogService logService;

	/**
	 * Main method to execute application
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(JavaApplication.class);
		application.run(args);
	}

	/**
	 * Run method to initiate log processing
	 * @param args command line arguments
	 */
	@Override
	public void run(ApplicationArguments args) {
		String[] arguments = args.getSourceArgs();
		if(ObjectUtils.isEmpty(args)) {
			throw new IllegalArgumentException("Illegal or Inappropriate arguments passed.");
		}
		logService.parseLogs(arguments[0]);
	}
}
