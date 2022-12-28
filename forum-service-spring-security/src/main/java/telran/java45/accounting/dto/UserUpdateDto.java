package telran.java45.accounting.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserUpdateDto {
	String firstName;
	String lastName;
}
