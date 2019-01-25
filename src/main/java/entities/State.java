package entities;

import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class State {
	private StateEnum stateEnum;
	private float minLoanAmount;
	private int minAge;

	public static State of(Map map) {
		return State.builder()
			.stateEnum(StateEnum.valueOf(
				(String)map.get("label"),
				(String)map.get("abbreviation")))
			.minLoanAmount((float)map.get("minLoanAmount"))
			.minAge((int)map.get("minAge"))
			.build();
	}
}
