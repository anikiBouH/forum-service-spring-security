package telran.java45.security;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.java45.accounting.dao.UserAccountRepository;
import telran.java45.accounting.model.UserAccount;
import telran.java45.post.dao.PostRepository;
import telran.java45.post.model.Post;

@Service("customSecurity")
@RequiredArgsConstructor
public class CustomWebSecurity {
	final PostRepository postRepository;
	final UserAccountRepository userRepository;

	public boolean checkPostAuthor(String postId, String userName) {
		Post post = postRepository.findById(postId).orElse(null);
		return post != null && userName.equalsIgnoreCase(post.getAuthor());
	}

	public boolean checkPasswordDateChange(String login) {
		
		UserAccount userAccount = userRepository.findById(login).orElse(null);
		return userAccount != null
				&& userAccount.getPasswordDateChange().isAfter(LocalDate.now());
	}
}
