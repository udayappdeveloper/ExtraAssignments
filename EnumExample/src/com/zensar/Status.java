package com.zensar;

public enum Status {
	NEW(0), REJECTED(1), ACCEPTED(2), COMPLETED(3);

	private int value;

	Status(int value) {
		this.value = value;
	}

	public int getValue() {

		return value;
	}

}
