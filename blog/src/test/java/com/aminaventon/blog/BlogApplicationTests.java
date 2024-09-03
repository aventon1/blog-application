package com.aminaventon.blog;

import com.aminaventon.blog.model.Post;
import com.aminaventon.blog.model.User;
import com.aminaventon.blog.repo.PostRepository;
import com.aminaventon.blog.repo.UserRepository;
import com.aminaventon.blog.service.PostServiceImpl;
import com.aminaventon.blog.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Test class to test Service and Repository Layers
 */
@Import(TestcontainersConfiguration.class)
@SpringBootTest(classes = BlogApplication.class)
class BlogApplicationTests {

	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostServiceImpl postServiceImpl;


	/**
	 * This method tests query findByEmail() in UserRepository
	 * @throws NullPointerException
	 */
	@ParameterizedTest
	@ValueSource(strings = {"test@test.com"})
	void testFindByEmail(String email) throws NullPointerException {
		// create user
		User user = new User();

		user.setFirstName("TestUserFirstName");
		user.setLastName("TestUserLastName");
		user.setEmail(email);
		user.setPassword("password");

		userRepository.save(user);


		// Check if the repository found the user by email
		assertThat(email).isEqualTo(userRepository.findByEmail(user.getEmail()).getEmail());

		userRepository.delete(user);
	}

	/**
	 * This method tests query findByFirstName() in UserRepository
	 * @throws NullPointerException
	 */
	@ParameterizedTest
	@ValueSource(strings = {"TestUserFirstName"})
	void testFindByFirstName(String firstName) throws NullPointerException {

		// create user
		User user = new User();

		user.setFirstName(firstName);
		user.setLastName("TestUserLastName");
		user.setEmail("test@test.com");
		user.setPassword("password");

		userRepository.save(user);

		List<User> users = userRepository.findByFirstName(firstName);

		for (User u : users) {
			// Check if the repository found the user by first name
			assertThat(u.getFirstName()).isEqualTo(firstName);
		}

		userRepository.delete(user);
	}

	/**
	 * This method tests query findByLastName() in UserRepository
	 * @throws NullPointerException
	 */
	@ParameterizedTest
	@ValueSource(strings = {"TestUserLastName"})
	void testFindByLastName(String lastName) throws NullPointerException {

		// create user
		User user = new User();

		user.setFirstName("TestUserFirstName");
		user.setLastName(lastName);
		user.setEmail("test@test.com");
		user.setPassword("password");

		userRepository.save(user);

		List<User> users = userRepository.findByLastName(lastName);

		for (User u : users) {
			// Check if the repository found the user by first name
			assertThat(u.getLastName()).isEqualTo(lastName);
		}

		userRepository.delete(user);
	}

	/**
	 * This method tests save() in UserServiceImpl
	 * @throws NullPointerException
	 */
	@Test
	void testSaveUser() throws NullPointerException {
		// create user
		User user = new User();

		user.setId(100L);
		user.setFirstName("TestUserFirstName");
		user.setLastName("TestUserLastName");
		user.setEmail("email");
		user.setPassword("password");

		userService.save(user);

		// Check if user was saved
		assertThat(userRepository.findById(100L) != null).isTrue();

		userRepository.delete(user);
	}

	/**
	 * This method tests save() in PostServiceImpl
	 * @throws NullPointerException
	 */
	@Test
	void testSavePost() throws NullPointerException {
		// create post
		Post post = new Post();
		post.setId(100L);
		post.setTitle("TestPostTitle");
		post.setContent("TestPostContent");

		postServiceImpl.savePost(post);

		// Check if post was saved
		assertThat(postRepository.findById(100L) != null).isTrue();

		postRepository.delete(post);
	}
}
