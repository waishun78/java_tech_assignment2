package cz.cvut.fit.tjv.social_network;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiMain {
    public static void main(String[] args) {
        var app = new SpringApplication(ApiMain.class);
        app.run(ApiMain.class, args);
    }
}
