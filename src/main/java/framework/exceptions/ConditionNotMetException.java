package framework.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ConditionNotMetException extends RuntimeException {
	private final String message;
}
