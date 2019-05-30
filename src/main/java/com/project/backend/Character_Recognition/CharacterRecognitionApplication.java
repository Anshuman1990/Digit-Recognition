package com.project.backend.Character_Recognition;

import com.project.backend.Character_Recognition.config.FileStorageProperties;
import com.project.backend.Character_Recognition.service.HelloServiceFactory;
import com.project.backend.Character_Recognition.service.HelloService;
import nu.pattern.OpenCV;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class CharacterRecognitionApplication {

    public static void main(String[] args) {

        SpringApplicationBuilder builder = new SpringApplicationBuilder(CharacterRecognitionApplication.class);
        builder.headless(false);
        ConfigurableApplicationContext context = builder.run(args);
        OpenCV.loadShared();
    }

    @Bean(name = "helloServiceFactory")
    public HelloServiceFactory helloFactory() {
        return new HelloServiceFactory();
    }

    @Bean(name = "helloServicePython")
    public HelloService helloServicePython() throws Exception {
        return helloFactory().getObject();

    }

}
