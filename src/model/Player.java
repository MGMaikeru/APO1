package model;

public class Player{

	private String nickName;
	private String name;
	private int score;
	private int lives;
	private int level;

	public Player(String anickName, String aname){
		name = aname;
		nickName = anickName;
		score = 10;
		lives = 5;
		level = 1;
	}

	/**
	* getNickName: Get the player nickname.
	* @return nickName Represents the player nickname.
	*/

	public String getNickName(){
		return nickName;
	}

	public void setScore(int newScore){
		score = newScore;
	}

	/**
	* getScore: Get the player socre.
	* @return score Represents the player socre.
	*/

	public int getScore(){
		return score;
	}

	/**
	* getLevel: Get the player level.
	* @return level Represents the player level.
	*/

	public int getLevel(){
		return level;
	}

	public void levelUp(){
		level += 1;
	}
}