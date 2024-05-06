package com.example.guessmaster;

public class Person extends Entity {
	private String gender;

	public Person() {
		super();
		gender = "N/A";
	}

	public Person(Person person) {
		super(person);
		this.gender = person.gender;
	}

	public Person(String name, Date born, double difficulty, String gender) {
		super(name, born, difficulty);
		this.gender = gender;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}


	public Person clone() {
		Person clone  = new Person(super.getName(), super.getBorn(),super.getDifficulty(), gender);
		return clone;
	}

	//adds to closing message of entity;
	public String toString() {
		return (super.toString() + "Gender: " + gender);
	}

	public String entityType() {
		return("This entity is a Person");
	}

}
