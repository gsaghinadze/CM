package ge.com.cm;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("ge.com")
public class App {
    
    private static final Logger logger = LogManager.getLogger(App.class);
    
    public static void main(String args[]) {
        logger.info(" startup !!!!!!!!!!!!!!");
        SpringApplication.run(App.class, args);
    }
}