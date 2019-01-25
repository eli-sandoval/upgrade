package framework.integration.tests;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

import org.testng.annotations.Test;

import entities.State;
import entities.StateEnum;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;

@Slf4j
public class StateListIT {
	private static final String URL = "https://credapi.credify.tech/api/loanapp/v1/states";

	@Test
	public void testGetStates_minAge19WithOnlyAlabama() {
		List<State> states = getStates();

		List<State> minAge19States = states
			.stream()
			.filter(st -> st.getMinAge() > 18)
			.collect(Collectors.toList());
		assertThat(minAge19States.size()).isEqualTo(1);
		assertThat(minAge19States).containsOnlyOnce(State.builder()
			.stateEnum(StateEnum.ALABAMA)
			.minLoanAmount(1000)
			.minAge(19)
			.build());
	}

	@Test
	public void testGetStates_loanAmount3005WithOnlyGeorgia() {
		List<State> states = getStates();

		List<State> loanAmount3005States = states
			.stream()
			.filter(st -> st.getMinLoanAmount() == 3005)
			.collect(Collectors.toList());
		assertThat(loanAmount3005States.size()).isEqualTo(1);
		assertThat(loanAmount3005States).containsOnlyOnce(State.builder()
			.stateEnum(StateEnum.GEORGIA)
			.minLoanAmount(3005)
			.minAge(18)
			.build());
	}

	@Test
	public void testGetStates_allStatesAreValid() {
		StateEnum[] stateEnums = StateEnum.values();

		List<State> states = getStates();

		assertThat(states.size()).isEqualTo(48);
		states.forEach(state -> assertThat(stateEnums).contains(state.getStateEnum()));
	}

	private List<State> getStates() {
		Response response = doGetRequest(URL);
		Map<String, List<Map>> responseMap = response.jsonPath().getMap("$");
		return responseMap.get("states").stream()
			.map(State::of)
			.collect(Collectors.toList());
	}

	private Response doGetRequest(String endpoint) {
		RestAssured.defaultParser = Parser.JSON;
		return given().headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
			.when().get(endpoint)
			.then().contentType(ContentType.JSON).extract().response();
	}
}
