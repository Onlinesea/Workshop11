package Workshop11.SpringBoot;

import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Collections;
import java.util.List;

//Instancing the SpringBootApplication
@SpringBootApplication
public class Application {

	
	//Logger is initialise to read the class 
	private static final Logger logger = LoggerFactory.getLogger((Application.class));
	//Initialising the String so that it can be used for the later part 
	private static final String DEFAULT_PORT_NUMBER = " 3000";

	//Setting the bean filter

	@Bean
	public CommonsRequestLoggingFilter requestLoggingFilter(){
		//Instancing the logger class
		CommonsRequestLoggingFilter logger = new CommonsRequestLoggingFilter();
		//Set the functions that the logger need to true 
		logger.setIncludeClientInfo(true);
		logger.setIncludeHeaders(true);
		logger.setIncludeQueryString(true);
		logger.setIncludePayload(true);
		return logger;
	}

	//The main function
	public static void main(String[] args) {

		//Instancing the new SpringApplication 
		SpringApplication app = new SpringApplication(Application.class);

		//Execute the application by running the class with the respective args
		SpringApplication.run(Application.class,args);


		DefaultApplicationArguments appArgs = new DefaultApplicationArguments(args);

		// To check if the argument contains the string "port"
		List opsVal = appArgs.getOptionValues("port");

		//How to see this in the logger?
		logger.info("opsVal >" + opsVal);

		//Instancing the portNumber that will be used
		String portNumber;

		//If there is no port number argument or the argument do not contain "port", use the port number that is in the environment 
		if(opsVal == null || opsVal.get(0)==null){
			portNumber = System.getenv("PORT");

			//If there is no port number in the environment, use the ddefault port number 
			if(portNumber == null){
				portNumber = DEFAULT_PORT_NUMBER;
			}
		}else{
			portNumber = (String) opsVal.get(0);
		}

		//If there is a port number, set the server.port properties of the app to the portNumber
		if(portNumber != null){
			app.setDefaultProperties(Collections.singletonMap("server.port", portNumber));
		}
		app.run(args);
		logger.info("Web app");
		logger.debug("Web app");

	}

}
