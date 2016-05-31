package com.stayhungry.primer.data;

public class Identity {
	private final String firstName;
	private final String lastName;
	
	public Identity(String firstName, String lastName){
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
}