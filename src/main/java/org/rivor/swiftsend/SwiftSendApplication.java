package org.rivor.swiftsend;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@ServletComponentScan
@Slf4j
@EnableScheduling
public class SwiftSendApplication {

    public static void main(String[] args) {
        SpringApplication.run(SwiftSendApplication.class, args);
        log.info("Start server successfully！");
    }

}
