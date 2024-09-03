package com.aminaventon.blog;

import com.aminaventon.blog.model.User;
import com.aminaventon.blog.repo.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Test class to test Service and Repository Layers
 */
@Import(TestcontainersConfiguration.class)
@SpringBootTest(classes = BlogApplication.class)
class BlogApplicationTests {

	@Autowired
	private UserRepository userRepository;

	/**
	 * This method tests query findByEmail() in UserRespositoru
	 * @throws NullPointerException
	 */
	@Test
	void testFindByEmail() throws NullPointerException {
		User user = new User();

		user.setFirstName("TestUser");
		user.setLastName("TestUser");
		user.setEmail("test@test.com");
		user.setPassword("password");

		userRepository.save(user);

		assertThat(user.getEmail()).isEqualTo(userRepository.findByEmail(user.getEmail()).getEmail());
	}

}
