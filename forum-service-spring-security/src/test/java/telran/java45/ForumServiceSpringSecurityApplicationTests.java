package telran.java45;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


import java.util.List;
import java.util.Optional;

import telran.java45.accounting.dao.UserAccountRepository;
import telran.java45.accounting.dto.UserAccountResponseDto;
import telran.java45.accounting.dto.UserRegisterDto;
import telran.java45.accounting.dto.UserUpdateDto;
import telran.java45.accounting.model.UserAccount;
import telran.java45.accounting.service.UserAccountServiceImpl;

@SpringBootTest
@TestPropertySource(properties = {"spring.data.mongodb.uri=mongodb+srv://vasili:1234.com@java45-aniki.v8yutwc.mongodb.net/telran?retryWrites=true&w=majority"})
class ForumServiceSpringSecurityApplicationTests {
	
	@MockBean
	UserAccountRepository repository;
	
	@Autowired
	UserAccountServiceImpl userAccountServiceImpl;
	
	UserAccount javaFunAccount;
	UserAccountResponseDto javaFunAccountResponseDto;
	UserRegisterDto javaFunRegisterDto;
	UserUpdateDto javaFunUpdateDto;
	@BeforeEach
	void setup(){
		javaFunAccount = new UserAccount("JavaFun", "1234", "John", "Smith");
		javaFunAccountResponseDto = UserAccountResponseDto.builder()
														 .login("JavaFun")
														 .firstName("John")
														 .lastName("Smith")
														 .roles(List.of("USER"))
														 .build();
		javaFunRegisterDto = UserRegisterDto.builder()
											.login("JavaFun")
											.password("1234")
											.firstName("John")
											.lastName("Smith")
											.build();
		
		when(repository.existsById("JavaFun")).thenReturn(false);
		when(repository.findById("JavaFun")).thenReturn(Optional.ofNullable(javaFunAccount));
	}
	
	@Test
	void existsById() {
		
	}
	
	@Test
	void addUser() {
		UserAccountResponseDto expected = javaFunAccountResponseDto;
		System.out.println(expected);
		UserAccountResponseDto actual = userAccountServiceImpl.addUser(javaFunRegisterDto);
		System.out.println(actual);
		assertEquals(expected, actual);
	}

}
