package geektime.im.lecture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


@SpringBootApplication(scanBasePackages = {"geektime.im.lecture"})
@ServletComponentScan(basePackages = {"geektime.im.lecture.controller"})
public class Starter {


    public static void main(String[] args) {

        System.setProperty("Log4jContextSelector", "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");
        SpringApplication.run(Starter.class, args);
    }

}
