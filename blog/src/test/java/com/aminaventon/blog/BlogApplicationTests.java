package com.aminaventon.blog;

import com.aminaventon.blog.controller.MainController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class BlogApplicationTests {

	@Autowired
	private MainController mainController;

	@Test
	void contextLoads() throws Exception {
		assertThat(mainController).isNotNull();
	}

}
