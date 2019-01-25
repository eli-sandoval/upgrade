package framework;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import org.apache.commons.lang.RandomStringUtils;

@Data
@AllArgsConstructor
@Builder
public class UserInformation {
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String state;
	private String zipCode;
	private String dateOfBirth;

	static UserInformation random() {
		return UserInformation.builder()
			.firstName(RandomStringUtils.randomAlphabetic(10))
			.lastName(RandomStringUtils.randomAlphabetic(10))
			.address(RandomStringUtils.randomAlphabetic(15))
			.city("Baltimore")
			.state("MD")
			.zipCode(RandomStringUtils.randomNumeric(5))
			.dateOfBirth("01/01/1980")
			.build();
	}
}
