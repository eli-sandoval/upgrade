package framework;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import org.apache.commons.lang.RandomStringUtils;

@Data
@AllArgsConstructor
@Builder
class Credentials {
	private String email;
	private String password;

	private static final String EMAIL_PATTERN_SUFIX = "@upgrade-challenge.com";
	private static final String EMAIL_PATTERN_PREFIX = "candidate+";

	static Credentials random() {
		return Credentials.builder()
			.email(String.format("%s%s%s", EMAIL_PATTERN_PREFIX, RandomStringUtils.randomNumeric(10), EMAIL_PATTERN_SUFIX))
			.password(RandomStringUtils.randomNumeric(2) + RandomStringUtils.randomAlphabetic(4).toUpperCase() + RandomStringUtils.randomAlphabetic(2).toLowerCase())
			.build();
	}
}
