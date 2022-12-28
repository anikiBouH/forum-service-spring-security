package telran.java45;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


import telran.java45.accounting.dao.UserAccountRepository;
import telran.java45.accounting.dto.RolesResponseDto;
import telran.java45.accounting.dto.UserAccountResponseDto;
import telran.java45.accounting.dto.UserRegisterDto;
import telran.java45.accounting.dto.UserUpdateDto;
import telran.java45.accounting.dto.exceptions.UserExistsException;
import telran.java45.accounting.dto.exceptions.UserNotFoundException;
import telran.java45.accounting.model.UserAccount;
import telran.java45.accounting.service.UserAccountServiceImpl;


@SpringBootTest
@DirtiesContext
public class ForumServiceSpringSecurityApplicationTests {

    @Autowired
    UserAccountRepository repository;

	@Autowired
	UserAccountServiceImpl userAccountServiceImpl;
	
	String login;
	UserAccount javaFunAccount;
	UserAccount denAccount;
	UserAccountResponseDto javaFunAccountResponseDto;
	UserAccountResponseDto denAccountResponseDto;
	UserRegisterDto javaFunRegisterDto;
	UserRegisterDto adminRegisterDto;
	UserRegisterDto denRegisterDto;
	UserUpdateDto javaFunUpdateDto;
	RolesResponseDto javaFunRolesResponseDto;
	@BeforeEach
	void setup(){
		login = "JavaFun";
		
		javaFunAccount = new UserAccount("JavaFun", "1234", "John", "Smith");
		repository.save(javaFunAccount);
				
		javaFunAccountResponseDto = UserAccountResponseDto.builder()
				.login("JavaFun")
				.firstName("John")
				.lastName("Smith")
				.role("USER")
				.build();
		
		javaFunRegisterDto = UserRegisterDto.builder()
				.login("JavaFun")
				.password("1234")
				.firstName("John")
				.lastName("Smith")
				.build();
		
		adminRegisterDto = UserRegisterDto.builder()
				.login("admin")
				.password("admin")
				.firstName("")
				.lastName("")
				.build();
	
		denAccount = new UserAccount("Den", "1234", "Daniel", "Jordan");
		denAccountResponseDto = UserAccountResponseDto.builder()
				.login("Den")
				.firstName("Daniel")
				.lastName("Jordan")
				.role("USER")
				.build();
		denRegisterDto = UserRegisterDto.builder()
				.login("Den")
				.password("1234")
				.firstName("Daniel")
				.lastName("Jordan")
				.build();
	}
	
//	public UserAccountResponseDto addUser(UserRegisterDto userRegisterDto)
	@Test
	void addUser() {
		UserAccountResponseDto expected = denAccountResponseDto;
		UserAccountResponseDto actual = userAccountServiceImpl.addUser(denRegisterDto);
		assertEquals(expected, actual);
		assertThrows(UserExistsException.class,()->{userAccountServiceImpl.addUser(adminRegisterDto);});
	}
	
//	public UserAccountResponseDto getUser(String login)
	@Test
	void getUser() {
		UserAccountResponseDto expected = javaFunAccountResponseDto;
		UserAccountResponseDto actual = userAccountServiceImpl.getUser(login);
		assertEquals(expected, actual);
		assertThrows(UserNotFoundException.class,()->{userAccountServiceImpl.getUser("invalidId");});
	}
//	public UserAccountResponseDto removeUser(String login)
	@Test
	void removeUser() {
		UserAccountResponseDto expected = javaFunAccountResponseDto;
		UserAccountResponseDto actual = userAccountServiceImpl.removeUser(login);
		assertEquals(expected, actual);
		assertThrows(UserNotFoundException.class,()->{userAccountServiceImpl.removeUser("invalidId");});
	}
//	public UserAccountResponseDto editUser(String login, UserUpdateDto userUpdateDto)
	@Test
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
		assertThrows(UserNotFoundException.class,()->{userAccountServiceImpl.editUser("invalidId", javaFunUpdateDto);});
	}
	
//	public RolesResponseDto changeRolesList(String login, String role, boolean isAddRole)
	@Test
	void changeRolesList() {
		javaFunRolesResponseDto = RolesResponseDto.builder()
				.login(login)
				.role("USER")
				.role("ADMINISTRATOR")
				.build();
		RolesResponseDto expected = javaFunRolesResponseDto;
		RolesResponseDto actual = userAccountServiceImpl.changeRolesList(login, "ADMINISTRATOR", true);
		assertEquals(expected, actual);
		assertThrows(UserNotFoundException.class,()->{userAccountServiceImpl.changeRolesList("invalidId", "ADMINISTRATOR", true);});
	}
//	public void changePassword(String login, String newPassword)
	@Test
	void changePassword() {
		assertThrows(UserNotFoundException.class,()->{userAccountServiceImpl.changePassword("invalidId", null);});
	}
        
}
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.TestPropertySource;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.when;
//import static org.mockito.Mockito.any;
//
//
//import java.util.Optional;
//
//import telran.java45.accounting.dao.UserAccountRepository;
//import telran.java45.accounting.dto.RolesResponseDto;
//import telran.java45.accounting.dto.UserAccountResponseDto;
//import telran.java45.accounting.dto.UserRegisterDto;
//import telran.java45.accounting.dto.UserUpdateDto;
//import telran.java45.accounting.dto.exceptions.UserExistsException;
//import telran.java45.accounting.dto.exceptions.UserNotFoundException;
//import telran.java45.accounting.model.UserAccount;
//import telran.java45.accounting.service.UserAccountServiceImpl;
//
//@SpringBootTest
//class ForumServiceSpringSecurityApplicationTests {
//	
//	@MockBean
//	UserAccountRepository repository;
//	
//	@Autowired
//	UserAccountServiceImpl userAccountServiceImpl;
//	
//	String login;
//	UserAccount javaFunAccount;
//	UserAccountResponseDto javaFunAccountResponseDto;
//	UserRegisterDto javaFunRegisterDto;
//	UserRegisterDto adminRegisterDto;
//	UserUpdateDto javaFunUpdateDto;
//	RolesResponseDto javaFunRolesResponseDto;
//	@BeforeEach
//	void setup(){
//		login = "JavaFun";
//		javaFunAccount = new UserAccount("JavaFun", "1234", "John", "Smith");
//		javaFunAccountResponseDto = UserAccountResponseDto.builder()
//				.login("JavaFun")
//				.firstName("John")
//				.lastName("Smith")
//				.role("USER")
//				.build();
//		javaFunRegisterDto = UserRegisterDto.builder()
//				.login("JavaFun")
//				.password("1234")
//				.firstName("John")
//				.lastName("Smith")
//				.build();
//		adminRegisterDto = UserRegisterDto.builder()
//				.login("admin")
//				.password("admin")
//				.firstName("")
//				.lastName("")
//				.build();
//		when(repository.existsById(any())).thenReturn(true);
//		when(repository.existsById("JavaFun")).thenReturn(false);
//		
//		when(repository.findById(any())).thenReturn(Optional.empty());
//		when(repository.findById("JavaFun")).thenReturn(Optional.ofNullable(javaFunAccount));
//	}
//	
////	public UserAccountResponseDto addUser(UserRegisterDto userRegisterDto)
//	@Test
//	void addUser() {
//		UserAccountResponseDto expected = javaFunAccountResponseDto;
//		UserAccountResponseDto actual = userAccountServiceImpl.addUser(javaFunRegisterDto);
//		assertEquals(expected, actual);
//		assertThrows(UserExistsException.class,()->{userAccountServiceImpl.addUser(adminRegisterDto);});
//	}
//	
////	public UserAccountResponseDto getUser(String login)
//	@Test
//	void getUser() {
//		UserAccountResponseDto expected = javaFunAccountResponseDto;
//		UserAccountResponseDto actual = userAccountServiceImpl.getUser(login);
//		assertEquals(expected, actual);
//		assertThrows(UserNotFoundException.class,()->{userAccountServiceImpl.getUser("invalidId");});
//	}
////	public UserAccountResponseDto removeUser(String login)
//	@Test
//	void removeUser() {
//		UserAccountResponseDto expected = javaFunAccountResponseDto;
//		UserAccountResponseDto actual = userAccountServiceImpl.removeUser(login);
//		assertEquals(expected, actual);
//		assertThrows(UserNotFoundException.class,()->{userAccountServiceImpl.removeUser("invalidId");});
//	}
////	public UserAccountResponseDto editUser(String login, UserUpdateDto userUpdateDto)
//	@Test
//	void editUser() {
//		javaFunUpdateDto = UserUpdateDto.builder()
//				.firstName("Johnnn")
//				.lastName("Smith")
//				.build();
//		UserAccountResponseDto expected = javaFunAccountResponseDto;
//		expected.setFirstName(javaFunUpdateDto.getFirstName());
//		expected.setLastName(javaFunUpdateDto.getLastName());
//		UserAccountResponseDto actual = userAccountServiceImpl.editUser(login, javaFunUpdateDto);
//		assertEquals(expected, actual);
//		assertThrows(UserNotFoundException.class,()->{userAccountServiceImpl.editUser("invalidId", javaFunUpdateDto);});
//	}
//	
////	public RolesResponseDto changeRolesList(String login, String role, boolean isAddRole)
//	@Test
//	void changeRolesList() {
//		javaFunRolesResponseDto = RolesResponseDto.builder()
//				.login(login)
//				.role("USER")
//				.role("ADMINISTRATOR")
//				.build();
//		RolesResponseDto expected = javaFunRolesResponseDto;
//		RolesResponseDto actual = userAccountServiceImpl.changeRolesList(login, "ADMINISTRATOR", true);
//		assertEquals(expected, actual);
//		assertThrows(UserNotFoundException.class,()->{userAccountServiceImpl.changeRolesList("invalidId", "ADMINISTRATOR", true);});
//	}
////	public void changePassword(String login, String newPassword)
//	@Test
//	void changePassword() {
//		assertThrows(UserNotFoundException.class,()->{userAccountServiceImpl.changePassword("invalidId", null);});
//	}
//	
//
//}
