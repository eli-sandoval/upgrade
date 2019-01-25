package framework;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class User {
	private Credentials credentials;
	private UserInformation userInformation;

	public static User random() {
		return User.builder()
				.credentials(Credentials.random())
				.userInformation(UserInformation.random())
				.build();
	}
}
