package com.example.guessmaster;
import android.annotation.SuppressLint;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;
import android.os.Bundle;
import android.content.DialogInterface;

public class GuessMaster extends AppCompatActivity {
	private	TextView entityName;
	private	TextView ticketSum;
	private EditText userIn;
	private ImageView entityImage;
	String input;
	private int numberOfCandidateEntities;
	private final Entity[] entities;
	private int tickets = 0;
	private int newIndex;

	Country usa = new Country(
			"United States",
			new Date("July", 4, 1776),
			0.1,
			"Washington D.C");
	Politician trudeau =  new Politician
			("Justin Trudeau",
					new Date("December", 25, 1971),
					0.25,
					"Male",
					"Liberal");
	Singer dion  = new Singer(
			"Celine Dion",
			new Date("March", 30, 1968),
			0.5,
			"Female",
			"La voix du bon Dieu",
			new Date("November", 6, 1981));
	Person Tom =  new Person(
			"Tom Haklai", new Date(
			"August", 31, 2004),
			1,
			"Male");

	public void addEntity(Entity entity) {
		Entity copy = entity.clone();
		entities[numberOfCandidateEntities++] = copy;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guess_activity);

		Button guessButton = (Button) findViewById(R.id.btnGuess);
		userIn = (EditText) findViewById(R.id.guessinput);
		ticketSum = (TextView) findViewById(R.id.ticket);
		entityName = (TextView) findViewById(R.id.entityName);
		Button btnclearContent = (Button) findViewById(R.id.btnClear);
		entityImage = (ImageView) findViewById(R.id.entityImage);

		this.addEntity(this.usa);
		this.addEntity(this.trudeau);
		this.addEntity(this.dion);
		this.addEntity(this.Tom);

		AlertDialog.Builder alert = new AlertDialog.Builder(GuessMaster.this);
		this.playGame(alert);

		btnclearContent.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				changeEntity();
			}
		});

		guessButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				playGame(newIndex);
			}
		});
	}

	public int randomEntityInd() {
		Random randInt = new Random();
		return  randInt.nextInt(numberOfCandidateEntities);
	}

	private void changeEntity() {
		userIn.getText().clear();
		newIndex = randomEntityInd();
		playGame(newIndex);
	}

	public GuessMaster() {
		numberOfCandidateEntities = 0;
		entities = new Entity[100];
	}

	private void ImageSetter(){
		if (entityName.getText().equals("United States")){
			entityImage.setImageResource(R.drawable.usaflag);
		} else if (entityName.getText().equals("Justin Trudeau")){
			entityImage.setImageResource(R.drawable.justint);
		} else if (entityName.getText().equals(("Celine Dion"))){
			entityImage.setImageResource(R.drawable.celidion);
		}  else {
			entityImage.setImageResource(R.drawable.creator);
		}
	}

	public void setUpGameUI(Entity entity) {
		entityName.setText(entity.getName());
		ImageSetter();
	}

	public void processAnswer(Entity entity, String answer) {
		answer = answer.replace("\n", "").replace("\r", "");

		if (!answer.equals("")) {
			Date date = new Date(answer);
			AlertDialog.Builder answeralert = new AlertDialog.Builder(GuessMaster.this);

			if (!date.equals(entity.getBorn())) {
				answeralert.setTitle("Incorrect");
				answeralert.setMessage(date.precedes(entity.getBorn()) ? "Try a later date" : "Try an earlier date");
				answeralert.setNegativeButton("Ok", (dialogInterface, i) -> {
					userIn.setText("");
					playGame(entity);
				});
			} else {
				answeralert.setTitle(("You won"));
				answeralert.setMessage("BINGO! " + entity.closingMessage());
				answeralert.setNegativeButton("Continue ", new DialogInterface.OnClickListener() {
					@SuppressLint("SetTextI18n")
					@Override
					public void onClick(DialogInterface dialogInterface, int i) {
						Toast.makeText(getBaseContext(),"You got "+entity.getAwardedTicketNumber()+" tickets!", Toast.LENGTH_SHORT).show();
						tickets += entity.getAwardedTicketNumber();
						ticketSum.setText("Tickets: " + tickets);
						continueGame();
					}
				});
			}

			AlertDialog dialog = answeralert.create();
			dialog.show();
		}
	}

	public void playGame(Entity entity) {
		setUpGameUI(entity);
		processAnswer(entity, userIn.getText().toString());
	}

	public void continueGame() {
		newIndex = randomEntityInd();
		Entity entity = entities[newIndex];
		String name = entity.getName();
		entityName.setText(name);
		ImageSetter();
		userIn.getText().clear();
	}

	public void playGame(int entityInd) {
		if (entityInd < 0  || entityInd >= numberOfCandidateEntities) {
			System.exit(0);
		}
		playGame(entities[entityInd]);
	}

	public void playGame(AlertDialog.Builder alert) {
		newIndex = randomEntityInd();
		if (newIndex < 0 || newIndex >= numberOfCandidateEntities) {
			System.exit(0);
		}
		welcomeToGame(entities[newIndex], alert);
		playGame(entities[newIndex]);
	}

	public void welcomeToGame(Entity Entity, AlertDialog.Builder alert){

		alert.setTitle(("GuessMaster"));
		alert.setMessage(Entity.welcomeMessage());
		alert.setCancelable(false);

		alert.setNegativeButton("START GAME", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(getBaseContext(), "Starting...", Toast.LENGTH_SHORT).show();
			}
		});
		AlertDialog dialog = alert.create();
		dialog.show();
	}

	public static void main(String[] args) {

	}
}




