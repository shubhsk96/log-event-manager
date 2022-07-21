package com.creditsuisse.assignment;

import com.creditsuisse.assignment.log.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.ObjectUtils;

@SpringBootApplication
public class JavaApplication implements ApplicationRunner {

	@Autowired
	private LogService logService;

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(JavaApplication.class);
		application.setHeadless(false);
		application.run(args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		String[] arguments = args.getSourceArgs();
		if(ObjectUtils.isEmpty(args)) {
			throw new IllegalArgumentException("Illegal or Inappropriate arguments passed.");
		}
		logService.parseLogs(arguments[0]);
	}
}
