package org.xunyin.officeautomationdemo;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.xunyin.officeautomationdemo.chat.WebSocketServer;
import org.xunyin.officeautomationdemo.properties.MinioProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = MinioProperties.class)
public class OfficeAutomationDemoApplication {


    public static void main(String[] args) {

        SpringApplication.run(OfficeAutomationDemoApplication.class, args);
    }

}
