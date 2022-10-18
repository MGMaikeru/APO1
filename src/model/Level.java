package model;
import java.util.Random;

public class Level{

	public static final int QUANTITY_TREASURES = 50;
	public static final int QUANTITY_ENEMIES = 25;

	private int id;
	private int requiredPoints;
	private String difficulty;

	private Treasure[] treasures;
	private Enemy[] enemies;

	public Level(int aid, int arequiredPoints){
		id = aid;
		requiredPoints = arequiredPoints;
		treasures = new Treasure[QUANTITY_TREASURES];
		enemies = new Enemy[QUANTITY_ENEMIES];
	}

	/**
	* getId: Gets the level number id.
	* @return id Represents the level number id.
	*/

	public int getId(){
		return id;
	}

	/**
	* getTreasure: Gets the array of treasures of the level.
	* @return treasures Represents the array of treasures.
	*/

	public Treasure[] getTreasures(){
		return treasures;
	}

	/**
	* getEnemies: Gets the array of enemies of the level.
	* @return treasures Represents the array of enemies.
	*/

	public Enemy[] getEnemies(){
		return enemies;
	}

	/**
	* getRequeredPoints: Gets the required points to pass level.
	* @return requiredPoints Represents the points to pass level.
	*/

	public int getRequiredPoints(){
		return requiredPoints;
	}

	/**
	* addTreasureWithObject: Adds the treasure to the level array.
	* @param treasure Represents boject class treasure to add.
	* @param heightSize Represents the height size of the resolution.
	* @param widthSize Represents the width size of the resolution.
	* @return msj Represents the a message with the status of the operation.
	*/

	public String addTreasureWithObject(Treasure treasure, int heightSize, int widthSize){
		String msj = "Maximum capacity reached.";
		boolean isEmpty = false;
		int positionY = assignatePositionY(heightSize);
		int positionX = assignatePositionX(widthSize);
		for (int i = 0; i < QUANTITY_TREASURES && !isEmpty; i++) {
			if (treasures[i] == null) {
				treasures[i] = treasure;
				treasures[i].setPositionY(positionY);
				treasures[i].setPositionX(positionX);
				isEmpty = true;
				msj = ("Treasure added successful. Ubication: " + treasures[i].getPositionX() + ", " + treasures[i].getPositionY());
			}
		}
		return msj;
	}

	/**
	* addEnemyWithObject: Adds the enemy to the level array.
	* @param enemy Represents object class enemy to add.
	* @param heightSize Represents the height size of the resolution.
	* @param widthSize Represents the width size of the resolution.
	* @return msj Represents the a message with the status of the operation.
	*/

	public String addEnemyWithObject(Enemy enemy, int heightSize, int widthSize){
		String msj = "Maximum capacity reached.";
		int positionY = assignatePositionY(heightSize);
		int positionX = assignatePositionX(widthSize);
		boolean isEmpty = false;
		for (int i = 0; i < QUANTITY_ENEMIES && !isEmpty; i++) {
			if (enemies[i] == null) {
				enemies[i] = enemy;
				enemies[i].setPositionY(positionY);
				enemies[i].setPositionX(positionX);
				isEmpty = true;
				msj = ("Enemy added successful. Ubication: " + enemies[i].getPositionX() + ", " + enemies[i].getPositionY());
			}
		}
		return msj;
	}

	/**
	* assignatePositionX: Assignates the object position on x axis.
	* @param widthSize Represents the width size of the resolution.
	* @return positionX Represents position on x axis of the object.
	*/

	public int assignatePositionX(int widthSize){
		Random randomPixel = new Random();
		int positionX = randomPixel.nextInt(widthSize)+1;
		return positionX;
	}

	/**
	* assignatePositionY: Assignates the object position on Y axis.
	* @param heightSize Represents the width size of the resolution.
	* @return positionX Represents position on Y axis of the object.
	*/

	public int assignatePositionY(int heightSize){
		Random randomPixel = new Random();
		int positionY = randomPixel.nextInt(heightSize)+1;
		return positionY;
	}

	/**
	* getAllInformation: Get the information about the level enemies and treasures.
	* @return msjA Represents the lsit with the level objects.
	*/

	public String getAllInformation(){
		int enemyCounter = 0;
		int treasureCounter = 0;
		String msj = "";

		for (int i= 0; i<QUANTITY_ENEMIES; i++) {
			if (enemies[i] != null){
				enemyCounter += 1;	
				msj += enemies[i].toString() + " \n";
			}	
		}
		String msjA = "Enemies: " + enemyCounter + ". \n";
		msjA += msj;

		msj = "";
		for (int i= 0; i<QUANTITY_TREASURES; i++) {
			if (treasures[i] != null){
				treasureCounter += 1;	
				msj += treasures[i].toString() + " \n";
			}	
		}
		String msjB = "Treasures: " + treasureCounter + ". \n";
		msjB += msj;

		msjA += msjB;

		return msjA;
	}
	//public String assignateDifficulty()
}