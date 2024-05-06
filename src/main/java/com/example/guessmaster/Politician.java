package com.example.guessmaster;

public class Politician extends Person {
	private String party;

	public Politician() {
		super();
		party = "N/A";
	}

	public Politician(Politician politician) {
		super(politician);
		this.party = politician.party;
	}

	public Politician(String name, Date born, double difficulty, String gender, String party) {
		super(name, born, difficulty, gender);
		this.party = party;
	}


	public Politician clone() {
		Politician clone = new Politician(super.getName(), super.getBorn(), super.getDifficulty(), super.getGender(), party);
		return clone;
	}

	public String toString() {
		return(super.toString() + "\nParty: " + party);
	}

	public String entityType() {
		return("This entity is a politician");
	}
}

