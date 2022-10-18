package model;

public class VideoGame{

	public static final int QUANTITY_LEVELS = 11;
	public static final int QUANTITY_PLAYERS = 20;
	public static final int QUANTITY_TREASURES = 50;
	public static final int QUANTITY_ENEMIES = 25;

	private  Player[] players;
	private Level[] levels;
	private Treasure[] treasures;
	private Enemy[] enemies;
	
	//private Screen screen;

	public VideoGame(){
		levels = new Level[QUANTITY_LEVELS];
		levels[1] = new Level(1, 15);
		levels[2] = new Level(2, 30);
		levels[3] = new Level(3, 60);
		levels[4] = new Level(4, 120);
		levels[5] = new Level(5, 240);
		levels[6] = new Level(6, 480);
		levels[7] = new Level(7, 500);
		levels[8] = new Level(8, 1000);
		levels[9] = new Level(9, 2000);
		levels[10] = new Level(10, 4000);

		players = new Player[QUANTITY_PLAYERS];
		treasures = new Treasure[QUANTITY_TREASURES];
	}

	/**
	 * addPlayer: Crate a new object class player and add to the players array.
	 * @param name Represents the name of the  player.
	 * @param nickName Represents the nickname of the player.
	 * @return msj Represents the a message with the status of the operation.
	 */

	public String addPlayer(String name, String nickName){
		Player newPlayer = new Player(nickName, name);
		String msj = "Maximum capacity reached.";
		boolean isEmpty = false;
		for (int i = 0;i < QUANTITY_PLAYERS && !isEmpty; i++) {
			if (players[i] == null) {
				players[i] = newPlayer;
				isEmpty = true;
				msj = "Player added successful.";
			}
		}
		
		return msj;
	}

	/**
	 * addTreasureToLevel: Crate a new object class treasure.
	 * @param name Represents the name of the treasure.
	 * @param image Represents the url of the treasure image.
	 * @param points Represents the points that the treasure adds.
	 * @param pos Represents the number of the level to add the treasure.
	 * @param heightSize Represents the height size of the resolution.
	 * @param widthSize Represents the width size of the resolution.
	 * @param quantity Represents the quantity of treasures to add.
	 * @return msj Represents the a message with the status of the operation.
	 */

	public String addTreasureToLevel(String name, String image, int points, int pos, int heightSize, int widthSize, int quantity){
		String msj = "";

		for (int i=0; i<quantity; i++) {
			Treasure newTreasure = new Treasure(name, points, image);
			msj += addTreasureWithObject(newTreasure, heightSize, widthSize, pos);
		}
		
		return msj;
	}

	/**
	 * addTreasureWithObject: Add to level and videogame in treaure array
	 * @param treasure Represents boject class treasure to add.
	 * @param heightSize Represents the height size of the resolution.
	 * @param widthSize Represents the width size of the resolution.
	 * @param pos Represents the number of the level to add the treasure.
	 * @return msj Represents the a message with the status of the operation.
	 */ 

	public String addTreasureWithObject(Treasure treasure, int heightSize, int widthSize, int pos){
		String msj = "Maximum capacity reached.";
		String msjS = "";
		boolean isEmpty = false;
		for (int i = 0; i < QUANTITY_TREASURES && !isEmpty; i++) {
			if (treasures[i] == null) {
				treasures[i] = treasure;
				isEmpty = true;
				msjS += levels[pos].addTreasureWithObject(treasure, heightSize, widthSize) + ".\n";
				msj = msjS;
			}
		}
		return msj;
	}

	/**
	 * addEnemyToLevel: Crate a new object class Enemy and add to level.
	 * @param name Represents the name of the enemy.
	 * @param option Represents the option to choose the enemy type.
	 * @param addPoints Represents the points that the enemy adds.
	 * @param eliminatePoints Represents the points that the enemy eliminates.
	 * @param pos Represents the number of the level to add the treasure.
	 * @param heightSize Represents the height size of the resolution.
	 * @param widthSize Represents the width size of the resolution.
	 * @return msj Represents the a message with the status of the operation.
	 */
	
	public String addEnemyToLevel(String name, int option, int addPoints, int eliminatePoints, int pos, int heightSize, int widthSize){
		String msj = "The enemy was not added.";
		Enemy newEnemy = new Enemy(name, option, addPoints, eliminatePoints);
		msj = levels[pos].addEnemyWithObject(newEnemy, heightSize, widthSize);
		return msj;
	}

	/**
	 * searchPlayerByNickName: Searchs a player from his nickname.
	 * @param nickName Represents the nickname fo the player to search.
	 * @return pos Represents the existence and position of the player.
	 */

	public int searchPlayerByNickName(String playerNickName){
		int pos = -1; 
		boolean isFound = false; 
		for(int i = 0; i < QUANTITY_PLAYERS && !isFound; i++){
			if (players[i]!= null){
				if(players[i].getNickName().equals(playerNickName)){
					pos = i; 
					isFound = true; 
				}
			}
		}

		return pos; 
	}

	/**
	 * searchLevelByIdNumber: Searchs a level from his id number.
	 * @param levelId Represents the number id of the level to search.
	 * @return pos Represents the existence and position of the level.
	 */

	public int searchLevelByIdNumber(int levelId){
		int pos = -1; 
		boolean isFound = false; 
		for(int i = 1; i < QUANTITY_LEVELS && !isFound; i++){
			if (levels[i]!= null){
				if(levels[i].getId()==levelId){
					pos = i; 
					isFound = true; 
				}
			}
		}

		return pos; 
	}

	public String changeScore(int newScore, int pos){
		players[pos].setScore(newScore);
		String msj = ("Now "+ players[pos].getNickName() + " has a score of " + players[pos].getScore());
		return msj;
	}

	/**
	 * checkLevelUp: Check the requeriment of level and level up player or shows a "Insufficient score" message.
	 * @param pos Represents the plauer position in array.
	 * @return msj Represents the a message with the status of the operation.
	 */

	public String checkLevelUp(int pos){
		int idLevel = players[pos].getLevel();
		int score = players[pos].getScore();
		int requiredPoints = levels[idLevel].getRequiredPoints();
		String msj = "";
		msj = ("The required points to pass of level " + players[pos].getLevel() + " has not been initialized. Please configure the level.");

		if (requiredPoints != 0) {
			if (score>requiredPoints){
				players[pos].levelUp();
				msj = ("Congratulations. The player is now on level " + players[pos].getLevel());
		
			} else{
				msj = ("Insufficient score. To level up is required " + requiredPoints + " points and the player has " + score);
		
			}
		}		

		return msj;
	}

	/**
	 * consultLevelInformation: Get the information from the level.
	 * @param levelId Represents the number id of the level to gets the information.
	 * @return msj Represents the a message with the status of the operation.
	 */

	public String consultLevelInformation(int idLevel){
		String msj = "";
		msj = levels[idLevel].getAllInformation();
		return msj;
	}

	/**
	 * countTreasure: Counts the times that one treasure be in the game.
	 * @param name Represents the name of the treasure.
	 * @return msj Represents the a message with the status of the operation.
	 */

	public String countTreasure(String name){
		int pos = 0;
		int quanTeasures = 0;
		String msj = "This treasure doesn't exist.";
		for (int i = 1; i<QUANTITY_LEVELS; i++){
			pos = 0;
			while(pos!=50){
				if (levels[i].getTreasures()[pos] != null) {
					if (levels[i].getTreasures()[pos].getName().equals(name)) {
						quanTeasures += 1;
						msj = "There are "+ quanTeasures + " " + name + " in the game.";
					}
				}
				pos += 1;
			}
		}
		return msj;
	}

	/**
	 * countEnemy: Counts the times that one enemy type be in the game.
	 * @param type Represents the type to search enemies.
	 * @return msj Represents the a message with the status of the operation.
	 */

	public String countEnemy(EnemyType type){
		int pos = 0;
		int quanEnemies = 0;
		String msj = "This enemy doesn't exist.";
		for (int i = 1; i<QUANTITY_LEVELS; i++){
			pos = 0;
			while(pos!=25){
				if (levels[i].getEnemies()[pos] != null) {
					if (levels[i].getEnemies()[pos].getType().equals(type)) {
						quanEnemies += 1;
						msj = "There are "+ quanEnemies + " " + type + " enemies in the game.";
					}
				}
				pos += 1;
			}
		}
		return msj;
	}
	
	/**
	 * getMostRepeatedTreasure: Gets the times and the name of the most repeated treasure.
	 * @param type Represents the type to search enemies.
	 * @return msj Represents the a message with the status of the operation.
	 */

	public String getMostRepeatedTreasure(){
		String[] names;
		names = new String[QUANTITY_TREASURES];
		String msj = "There is not tresures registered in the game.";
		for (int i = 0; i<QUANTITY_TREASURES; i++){
			if (treasures[i] != null){
				names[i] = treasures[i].getName();
			}
		}

		String aux = "";
		int contador = 0;
		String mostRepeated = "";
		int contMostRepeated = 0;

		for (int i = 0; i<names.length; i++){
			if (names[i] != null){
				aux = names[i];
				int pos = 0;
				contador = 0;
				while(pos < QUANTITY_TREASURES){
					if (aux.equals(names[pos])){
						contador+=1;
					}
					pos += 1;
				}
			}
			if (contMostRepeated<contador){
				contMostRepeated = contador;
				mostRepeated = aux;
			}

		}

		msj = mostRepeated + " with " + contMostRepeated + " in all game.";
		return msj;	
	}

	/**
	 * getHigherScoreEnemy: Gets the name and points of higher score enemy.
	 * @return msj Represents the a message with the status of the operation.
	 */

	public String getHigherScoreEnemy(){
		String higherNameEnemy = "";
		int aux = 0;
		String msj = "There is not enemy registered in the game.";
		for (int i = 1; i<QUANTITY_LEVELS; i++){
			int pos = 0;
			while(pos!=25){
				if (levels[i].getEnemies()[pos] != null) {
					if (aux<levels[i].getEnemies()[pos].getAddPoints()){
						aux = levels[i].getEnemies()[pos].getAddPoints();
						higherNameEnemy = levels[i].getEnemies()[pos].getIdName();
						int level = i;
						msj = higherNameEnemy + " is the higher score enemy with " + aux + " points, and he is on level " + level + ".";
					}
				}
				pos += 1;
			}
		}
		return msj;
	}

	/**
	 * getConsonants: Gets the quantity of enemies names in game.
	 * @param enemyName Represents teh name of the enemy to count consonants.
	 * @return consonants Represents quantity of consonants found in the name.
	 */

	public int getConsonants(String enemyName){
		int consonants = 0;
		for (int i=0 ; i<enemyName.length(); i++){
			char lt = enemyName.charAt(i);
			if(lt == 'a' || lt == 'e' || lt == 'i' || lt == 'o' || lt == 'u' || lt == 'A' || lt == 'E' || lt == 'I' || lt == 'O' || lt == 'U'  ){
				consonants = consonants + 0;
			}else if(lt != ' '){
				consonants++;
			}
		}
		return consonants;
	}

	/**
	 * consonantsInEnemies: Gets the enemies names and organize the information.
	 * @return consonantsInEn Represents the a message with the list of constonant by name.
	 */

	public String consonantsInEnemies(){
		String consonantsInEn =  "";
		int consonants = 0;
		int totalConsonants = 0;
		for (int i = 1; i<QUANTITY_LEVELS; i++){
			int pos = 0;
			while(pos!=25){
				if (levels[i].getEnemies()[pos] != null) {
					consonants = getConsonants(levels[i].getEnemies()[pos].getIdName());
					totalConsonants += consonants;
					consonantsInEn += "\n" + (i + 1) + ". There are " + consonants + " consonants in the name [" + levels[i].getEnemies()[pos].getIdName() + "].";
				}
				pos += 1;
			}
		}
		consonantsInEn += "\n Total consonants in game: " + totalConsonants;
		return consonantsInEn;
	}

	/**
	 * getTop: Get the top 5 player with higher score.
	 * @param array Represents the array with all players scores. 
	 * @return mayor Represents the higher and ordered scores.
	 */

	public int[] getTop(int[] array) {

		int mayor[] = new int[2];

		for (int i = 0; i < array.length; i++) {

			if (array[i] > mayor[0]) {
				mayor[0] = array[i];
				mayor[1] = i;
			}

		}

		return mayor;
	}

	/**
	 * getAllScores: Get all players scores in the game.
	 * @return allScores Represents the array with all players scores in the game.
	 */

	public int[] getAllScores(){

		int[] allScores = new int[20];

		for (int i = 0; i < QUANTITY_PLAYERS; i++ ) {

			if (players[i]!= null) {

				allScores[i] = players[i].getScore();

			}
		}

		return allScores;
	}

	/**
	 * getNameCoincidence: Get player name if the score is him.
	 * @param coincidence Represents the score to get the player name.
	 * @return name Represents the player name.
	 */

	public String getNameCoincidence(int coincidence) {

		String name = "";
		boolean match = false;
		while (!match) {

			for (int i = 0; i < players.length ; i++) {
				if (players[i] != null) {
					if (coincidence == players[i].getScore()) {
						name = players[i].getNickName();
						match = true;

					}
				}

			}

		}
		return name;
	}

	/**
	 * getOrderedScores: Get the list with the top players.
	 * @return top5 Represents the list with the top.
	 */

	public String getOrderedScores() {

		String top5 = "";

		int[] actuallyScore = getAllScores(); 

		int[] orderedScore = new int[20];

		for (int i = 0; i < actuallyScore.length; i++) {

			int[] result = getTop(actuallyScore);
			orderedScore[i] = result[0];
			actuallyScore[(int)result[1]] = -1;

		}


		for (int j = 0; j < 5; j++) {
			if (orderedScore[j] != 0) {
				top5 += "\nTOP " + (j + 1) + " : " + getNameCoincidence(orderedScore[j]) + " Score: " + orderedScore[j];
			}

		}

		return top5;
	}

}