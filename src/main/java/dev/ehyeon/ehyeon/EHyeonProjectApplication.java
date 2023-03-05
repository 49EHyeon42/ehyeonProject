package dev.ehyeon.ehyeon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // JPA Auditing 활성화
@SpringBootApplication
public class EHyeonProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(EHyeonProjectApplication.class, args);
    }
}
