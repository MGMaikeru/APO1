package model;
import java.awt.Image;

public class Treasure{

	private String name;
	private Image image;
	private int points;
	private int positionX;
	private int positionY;

	public Treasure(String aname, int apoints, String image){
		name = aname;
		points = apoints;
		positionX = 1;
		positionY = 1;
	}

	/**
	* getName: Get the treasure name.
	* @return name Represents the treasure name.
	*/

	public String getName(){
		return name;
	}

	/**
	* getPoints: Get the treasure points that add.
	* @return getPoints Represents the treasure points that add.
	*/

	public int getPoints(){
		return points;
	}

	/**
	* getPositionY: Get the treasure position in axis X.
	* @return positonY Represents the treasure position in axis X.
	*/

	public int getPositionX(){
		return positionX;
	}

	/**
	* getPositionY: Get the treasure position in axis Y.
	* @return positonY Represents the treasure position in axis Y.
	*/

	public int getPositionY(){
		return positionY;
	}

	/**
	* setPositionX: Set the treasure position.
	* @param aapositionX Represents the new position of treasure.
	*/

	public void setPositionX(int apositionX){
		positionX = apositionX;
	}

	/**
	* setPositionY: Set the treasure position.
	* @param aapositionY Represents the new position of treasure.
	*/

	public void setPositionY(int apositionY){
		positionY = apositionY;
	}

	public String toString(){
		String msj = "Name: " + name + ".";
		return msj;
	}

}