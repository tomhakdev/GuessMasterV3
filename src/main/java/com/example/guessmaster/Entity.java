package com.example.guessmaster;

public abstract class Entity {
	private String name;
	private Date born;
	private double difficulty; //0 to 1

	public Entity() {
		Date born = new Date();
		this.born = born;
		this.name = "Name";
		this.difficulty = 0;
	}

	public Entity(String name, Date birthDate, double difficulty) {
		if (birthDate == null) {
			System.out.println("Fatal Error.");
			System.exit(0);
		}

		this.name = name;
		this.born = new Date(birthDate);
		this.difficulty = difficulty;

	}


	public Entity(Entity entity) {
		this.name = entity.name;
		this.born = new Date(entity.born); //no privacy leak
		this.difficulty = entity.difficulty;
	}

	public String getName() {
		return name;
	}

	public Date getBorn() {
		return new Date(born);
	}

	public double getDifficulty() {
		return difficulty;
	}

	public String toString() {
		return "Name: "+name+"\n"+"Born at: "+born.toString()+"\n";
	}

	public int getAwardedTicketNumber(){
		return (int) (difficulty * 100);
	}

	public abstract String entityType();

	public abstract Entity clone();

	public String welcomeMessage() {
		return ("Welcome! Let's start the game! " + entityType() + "\n");
	}

	public String closingMessage() {
		return("Congratulations! The detailed information of the entity you guessed is:\n" + toString());
	}
}


