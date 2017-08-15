package com.artem.usenko;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@SpringBootApplication
@EnableJpaRepositories("com.artem.usenko.repository")
public class PhoneBook {
    public static void main(String[] args) throws IOException {
        String prop = System.getProperty("lardi.conf");
        Properties tmp = new Properties();
        tmp.load(new FileReader(new File(prop)));
        String userPath = tmp.getProperty("UserPath");
        String contactPath = tmp.getProperty("ContactPath");
        if (userPath != null) {
            File file = new File(userPath);
            if (!file.exists()) {
                file.createNewFile();
            }
            file = new File(contactPath);
            if (!file.exists()) {
                file.createNewFile();
            }
        }
        SpringApplicationBuilder builder = new SpringApplicationBuilder(PhoneBook.class);

        if (prop != null && !prop.equals("") && new File(prop).exists()) {
            builder.properties("spring.config.location=" + prop);
        }

        builder.run(args);


    }
}
