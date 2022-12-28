package telran.java45;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;


import java.util.List;
import java.util.Optional;

import telran.java45.accounting.dao.UserAccountRepository;
import telran.java45.accounting.dto.RolesResponseDto;
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
	
	String login;
	UserAccount javaFunAccount;
	UserAccountResponseDto javaFunAccountResponseDto;
	UserRegisterDto javaFunRegisterDto;
	UserUpdateDto javaFunUpdateDto;
	RolesResponseDto javaFunRolesResponseDto;
	@BeforeEach
	void setup(){
		login = "JavaFun";
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
		
		when(repository.existsById(any())).thenReturn(true);
		when(repository.existsById("JavaFun")).thenReturn(false);
		
		when(repository.findById(any())).thenReturn(null);
		when(repository.findById("JavaFun")).thenReturn(Optional.ofNullable(javaFunAccount));
	}
	
//	public UserAccountResponseDto addUser(UserRegisterDto userRegisterDto)
	@Test
	void addUser() {
		UserAccountResponseDto expected = javaFunAccountResponseDto;
		UserAccountResponseDto actual = userAccountServiceImpl.addUser(javaFunRegisterDto);
		assertEquals(expected, actual);
	}
//	public UserAccountResponseDto getUser(String login)
	@Test
	void getUser() {
		UserAccountResponseDto expected = javaFunAccountResponseDto;
		UserAccountResponseDto actual = userAccountServiceImpl.getUser(login);
		assertEquals(expected, actual);
	}
//	public UserAccountResponseDto removeUser(String login)
	@Test
	void removeUser() {
		UserAccountResponseDto expected = javaFunAccountResponseDto;
		UserAccountResponseDto actual = userAccountServiceImpl.removeUser(login);
		assertEquals(expected, actual);
	}
//	public UserAccountResponseDto editUser(String login, UserUpdateDto userUpdateDto)
	void editUser() {
		javaFunUpdateDto = UserUpdateDto.builder()
				.firstName("Johnnn")
				.lastName("Smith")
				.build();
		UserAccountResponseDto expected = javaFunAccountResponseDto;
		expected.setFirstName(javaFunUpdateDto.getFirstName());
		expected.setLastName(javaFunUpdateDto.getLastName());
		UserAccountResponseDto actual = userAccountServiceImpl.editUser(login, javaFunUpdateDto);
		assertEquals(expected, actual);
	}
//	public RolesResponseDto changeRolesList(String login, String role, boolean isAddRole)
	void changeRolesList() {
		javaFunRolesResponseDto = RolesResponseDto.builder()
				.login(login)
				.role("USER")
				.role("ADMINISTRATOR")
				.build();
		RolesResponseDto expected = javaFunRolesResponseDto;
		RolesResponseDto actual = userAccountServiceImpl.changeRolesList(login, "ADMINISTRATOR", true);
		assertEquals(expected, actual);
	}
//	public void changePassword(String login, String newPassword)
	
	

}
