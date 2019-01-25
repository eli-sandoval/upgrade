package entities;

import java.util.Arrays;

import lombok.Getter;

@Getter
public enum StateEnum {
	ALABAMA("Alabama", "AL"),
	ALASKA("Alaska", "AK"),
	ARIZONA("Arizona", "AZ"),
	ARKANSAS("Arkansas", "AR"),
	CALIFORNIA("California", "CA"),
	CONNECTICUT("Connecticut", "CT"),
	DELAWARE("Delaware", "DE"),
	DISTRICT_OF_COLUMBIA("District of Columbia", "DC"),
	FLORIDA("Florida", "FL"),
	GEORGIA("Georgia", "GA"),
	HAWAII("Hawaii", "HI"),
	IDAHO("Idaho", "ID"),
	ILLINOIS("Illinois", "IL"),
	INDIANA("Indiana", "IN"),
	KANSAS("Kansas", "KS"),
	KENTUCKY("Kentucky", "KY"),
	LOUISIANA("Louisiana", "LA"),
	MAINE("Maine", "ME"),
	MARYLAND("Maryland", "MD"),
	MASSACHUSETTS("Massachusetts", "MA"),
	MICHIGAN("Michigan", "MI"),
	MINNESOTA("Minnesota", "MN"),
	MISSISSIPPI("Mississippi", "MS"),
	MISSOURI("Missouri", "MO"),
	MONTANA("Montana", "MT"),
	NEBRASKA("Nebraska", "NE"),
	NEVADA("Nevada", "NV"),
	NEW_HAMPSHIRE("New Hampshire", "NH"),
	NEW_JERSEY("New Jersey", "NJ"),
	NEW_MEXICO("New Mexico", "NM"),
	NEW_YORK("New York", "NY"),
	NORTH_CAROLINA("North Carolina", "NC"),
	NORTH_DAKOTA("North Dakota", "ND"),
	OHIO("Ohio", "OH"),
	OKLAHOMA("Oklahoma", "OK"),
	OREGON("Oregon", "OR"),
	PENNSYLVANIA("Pennsylvania", "PA"),
	RHODE_ISLAND("Rhode Island", "RI"),
	SOUTH_CAROLINA("South Carolina", "SC"),
	SOUTH_DAKOTA("South Dakota", "SD"),
	TENNESSEE("Tennessee", "TN"),
	TEXAS("Texas", "TX"),
	UTAH("Utah", "UT"),
	VERMONT("Vermont", "VT"),
	VIRGINIA("Virginia", "VA"),
	WASHINGTON("Washington", "WA"),
	WISCONSIN("Wisconsin", "WI"),
	WYOMING("Wyoming", "WY");

	private String label;
	private String abbreviation;

	StateEnum(String label, String abbreviation) {
		this.label = label;
		this.abbreviation = abbreviation;
	}

	public static StateEnum valueOf(String label, String abbreviation) {
		return Arrays.stream(StateEnum.values())
			.filter(stateEnum -> stateEnum.getLabel().equalsIgnoreCase(label) && stateEnum.getAbbreviation().equalsIgnoreCase(abbreviation))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException(String.format("StateEnum with label=%s and abbreviation=%s not found",
				label,
				abbreviation)));
	}
}