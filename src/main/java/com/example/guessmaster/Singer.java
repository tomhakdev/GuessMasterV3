package com.example.guessmaster;

public class Singer extends Person {
	private String debutAlbum;
	private Date debutAlbumReleaseDate;

	public Singer() {
		super();
		debutAlbum = "N/A";
		debutAlbumReleaseDate = new Date(0, 0, 0000);
	}

	public Singer(Singer singer) {
		super(singer);
		this.debutAlbum = singer.debutAlbum;
		this.debutAlbumReleaseDate = singer.debutAlbumReleaseDate;
	}

	public Singer(String name, Date born, double difficulty, String gender, String debutAlbum, Date debutAlbumReleaseDate) {
		super(name, born, difficulty, gender);
		this.debutAlbum = debutAlbum;
		this.debutAlbumReleaseDate = debutAlbumReleaseDate;
	}


	public Singer clone() {
		Singer clone  = new Singer(super.getName(), super.getBorn(),super.getDifficulty(), super.getGender(), debutAlbum, debutAlbumReleaseDate);
		return clone;
	}

	public String toString() {
		return(super.toString() + "\nDebut Album: " + debutAlbum + "\nDebut Album Release Date: " + debutAlbumReleaseDate);
	}

	public String entityType() {
		return("This entity is a singer");
	}

}
