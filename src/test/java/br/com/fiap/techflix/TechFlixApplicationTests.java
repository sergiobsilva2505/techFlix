package br.com.fiap.techflix;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

@SpringBootTest
class TechFlixApplicationTests {

	@Test
	@DisplayName("Contexto da aplicação carrega sem erros")
	void contextLoads() {
		// passes if the Spring context starts without errors
	}

	@Test
	@DisplayName("Método main executa sem lançar exceção")
	void mainMethodRunsWithoutException() {
		try (MockedStatic<SpringApplication> mocked = mockStatic(SpringApplication.class)) {
			mocked.when(() -> SpringApplication.run(TechFlixApplication.class, new String[]{}))
					.thenReturn(mock(ConfigurableApplicationContext.class));

			assertDoesNotThrow(() -> TechFlixApplication.main(new String[]{}));
		}
	}

	@Test
	@DisplayName("Método main repassa os argumentos para o SpringApplication")
	void mainMethodForwardsArgumentsToSpringApplication() {
		String[] args = {"--server.port=9090"};
		try (MockedStatic<SpringApplication> mocked = mockStatic(SpringApplication.class)) {
			mocked.when(() -> SpringApplication.run(TechFlixApplication.class, args))
					.thenReturn(mock(ConfigurableApplicationContext.class));

			TechFlixApplication.main(args);

			mocked.verify(() -> SpringApplication.run(TechFlixApplication.class, args));
		}
	}

	@Test
	@DisplayName("Método main passa a classe correta para o SpringApplication")
	void mainMethodPassesCorrectApplicationClass() {
		try (MockedStatic<SpringApplication> mocked = mockStatic(SpringApplication.class)) {
			mocked.when(() -> SpringApplication.run(any(Class.class), any(String[].class)))
					.thenReturn(mock(ConfigurableApplicationContext.class));

			TechFlixApplication.main(new String[]{});

			mocked.verify(() -> SpringApplication.run(TechFlixApplication.class, new String[]{}));
		}
	}

}
