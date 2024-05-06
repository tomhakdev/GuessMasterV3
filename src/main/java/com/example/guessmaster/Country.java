package com.example.guessmaster;

public class Country extends Entity{
	private String capital;

	public Country() {
		super();
		capital = "N/A";
	}

	public Country(Country country) {
		super(country);
		this.capital = country.capital;
	}

	public Country(String name, Date born, double difficulty, String capital) {
		super(name, born, difficulty);
		this.capital = capital;
	}

	public Country clone() {
		Country clone = new Country(super.getName(), super.getBorn(), super.getDifficulty(), capital);
		return clone;
	}

	public String toString() {
		return(super.toString() + "Capital: " + capital);
	}

	public String entityType() {
		return("This entity is a country");
	}
}
