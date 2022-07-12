package Workshop11.SpringBoot;

import java.util.logging.Logger;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class Application {
	
	private static final Logger logger = LoggerFactory.getLogger((Application.class));
	private static final String DEFAULT_PORT_NUMBER = " 3000";

	//Setting the bean filter

	@Bean
	public CommonsRequestLoggingFilter requestLoggingFilter(){
		CommonsRequestLoggingFilter logger = new CommonsRequestLoggingFilter();
		logger.setIncludeClientInfo(true);
		logger.setIncludeHeaders(true);
		logger.setIncludeQueryString(true);
		logger.setIncludePayload(true);
		return logger;
	}
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Application.class);
		StringApplication.run(Application.class,args);
		DefaultApplicationArguments appArgs = new DefaultApplicationArguments(args);
		List opsVal = appArgs.getOptionValues("port");
		logger.info("opsVal >" + opsVal);
		String portNumber;
		if(opsVal == null || opsVal.get(0)==null){
			portNumber = System.getenv("PORT");
			if(portNumber == null){
				portNumber = DEFAULT_PORT_NUMBER;
			}
		}else{
			portNumber = (String) opsVal.get(0);
		}
		if(portNumber != null){
			app.setDefaultProperties(Collections.singletonMap("server.port", portNumber));
		}
		app.run(args);
		//logger.info("Web app");
		//logger.debug("Web app");

	}

}
