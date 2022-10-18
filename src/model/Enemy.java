package model;

public class Enemy{

	private String idName;
	private EnemyType type;
	private int addPoints;
	private int eliminatePoints;
	private int positionY;
	private int positionX;
	private int option;

	public Enemy(String aidName, int aoption, int aaddPoints, int aeliminatePoints){
		option = aoption;
		idName = aidName;
		addPoints = aaddPoints;
		eliminatePoints = aeliminatePoints;
		positionX = 1;
		positionY = 1;
		setTypeEnemy();
	}

	/**
	* getIdName: Get the enemy name.
	* @return idName Represents the enemy name.
	*/

	public String getIdName(){
		return idName;
	}

	/**
	* getType: Get the enemy type.
	* @return type Represents the enemy type.
	*/

	public EnemyType getType(){
		return type;
	}

	/**
	* setPositionX: Set the enemy position.
	* @param aapositionX Represents the new position of enemy.
	*/

	public void setPositionX(int apositionX){
		positionX = apositionX;
	}

	/**
	* setPositionY: Set the enemy position.
	* @param aapositionY Represents the new position of enemy.
	*/

	public void setPositionY(int apositionY){
		positionY = apositionY;
	}

	/**
	* getAddPoints: Get the enemy points that add.
	* @return addPoints Represents the enemy points that add.
	*/

	public int getAddPoints(){
		return addPoints;
	}

	/**
	* getPositionY: Get the enemy position in axis Y.
	* @return positonY Represents the enemy position in axis Y.
	*/

	public double getPositionY(){
		return positionY;
	}

	/**
	* getPositionX: Get the enemy position in axis X.
	* @return POsitionX Represents the enemy position in axis X.
	*/

	public int getPositionX(){
		return positionX;
	}

	public void setTypeEnemy(){
		if(option == 1){
			type = EnemyType.OGRE; 
		}
		else if(option == 2){
			type = EnemyType.ABSTRACT; 
		}
		else if(option == 3){
			type = EnemyType.BOSS; 
		}
		else{
			type = EnemyType.MAGIC; 
		}
	}

	public String toString(){
		String msj = "Name: " + idName + " Type: " + type + ".";
		return msj;
	}
}