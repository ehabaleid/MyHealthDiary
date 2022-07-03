package com.ehabaleid.healthdiary;

import com.ehabaleid.healthdiary.data.ExerciseRepository;
import com.ehabaleid.healthdiary.data.FoodRepository;
import com.ehabaleid.healthdiary.data.MemoryRepository;
import com.ehabaleid.healthdiary.data.UserRepository;
import com.ehabaleid.healthdiary.model.user.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCrypt;

@EnableJpaAuditing
@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = {UserRepository.class, FoodRepository.class, MemoryRepository.class, ExerciseRepository.class})

public class HealthDiaryApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthDiaryApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(UserRepository userRepository) {
		return args -> {
			User bob = new User("Bob Smith", "test@gmail.com", BCrypt.hashpw("pass", BCrypt.gensalt()), "Bob@gmail.com", "ADMIN", true);
			bob.setEnabled(true);
			userRepository.save(bob);
		};
	}

}
