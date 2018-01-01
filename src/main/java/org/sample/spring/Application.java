package org.sample.spring;

import org.sample.spring.model.Note;
import org.sample.spring.repository.NoteRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableJpaAuditing
@SpringBootApplication
public class Application {

	@Bean
	InitializingBean seedDatabase(NoteRepository noteRepository){

		return () -> {
			noteRepository.save(new Note("T3","C3"));
			noteRepository.save(new Note("T4","C4"));
			noteRepository.save(new Note("T5","C5"));
		};
	}

	@Bean
	CommandLineRunner exampleQuery(NoteRepository noteRepository){
		return args -> {
			noteRepository.findByContentIgnoringCase("c5").forEach(System.err::println);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
