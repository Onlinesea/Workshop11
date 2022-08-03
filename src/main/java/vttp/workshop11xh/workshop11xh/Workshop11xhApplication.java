package vttp.workshop11xh.workshop11xh;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@SpringBootApplication
public class Workshop11xhApplication {

	private static final Logger logger = LoggerFactory.getLogger(Workshop11xhApplication.class);

	private static final String DEFAULT_PORT_NUMBER= "8080";
	public static void main(String[] args) {

		SpringApplication app = new SpringApplication(Workshop11xhApplication.class);
		ApplicationArguments appArg = new DefaultApplicationArguments(args);
		
		String portNumber = DEFAULT_PORT_NUMBER;

		if(null != appArg){
			if(appArg.containsOption("port")){
				portNumber = appArg.getOptionValues("port").get(0);
			}
		}

		app.setDefaultProperties(Collections.singletonMap("server.port", portNumber));
		app.run(args);

	}

	@Bean 
	// To set the information that you want for the logger
	public CommonsRequestLoggingFilter log() {
		CommonsRequestLoggingFilter logger = new CommonsRequestLoggingFilter();
	logger.setIncludeClientInfo(true);
	logger.setIncludeQueryString(true);
	logger.setIncludeHeaders(true);
	logger.setIncludePayload(true);
	return logger;
	}

}
