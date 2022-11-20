package kr.kwangan2.jpaexam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		
		SpringApplication.run(Application.class, args);

//SpringApplication application = new SpringApplication(Application.class);
//		
//		application.setWebApplicationType(WebApplicationType.SERVLET);//이제 웹에서 돌릴꺼니까.
//		application.run(args);	
	}

}
