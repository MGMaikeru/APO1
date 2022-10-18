package ui; 

import java.util.Scanner;
import model.VideoGame;
import model.EnemyType;

/**
* Main: This class contains the visual methods
* and the methods of the options.
* @author MG_13
* @since 17 October
*/

public class Main{

	private static int heightSize = 0;
	private static int widthSize = 0;

	private Scanner reader;
	private VideoGame videoGame;

	public Main(){
		reader = new Scanner(System.in); 
		videoGame = new VideoGame();
	}

	public static void main(String[] args){
		Main main = new Main();  
		int option = 0; 
		option = main.getOptionShowScreenMenu();
		System.out.println(main.banner());
		main.executeScreenOption(option);
		do{

			option = main.getOptionShowMenu(); 
			main.executeOption(option);

		}while(option != 0);

		main.getReader().close();
	}

	public Scanner getReader(){
		return reader;
	}

	public VideoGame getVideoGame(){
		return videoGame; 
	}

	/**
	* getNickName: Validates and informs about the disponibility of the nickname.
	* @return nickName respresentsthe the validate nick name. 
	*/ 

	public String getNickName(){
		String nickName = "";
		int validation = 1;
		int pos = 0;
		String msj = "The nickname is alredy in use.";
		while(validation == 1){
			System.out.println("Type nickName: ");
			nickName = reader.next();
			pos = videoGame.searchPlayerByNickName(nickName);
			if (pos == -1){
				msj = "Nickname accepted.";
				System.out.println(msj);
				validation = 0;
			} else{
				System.out.println(msj);
				validation = 1;
			}
		}
		return nickName;
	}

	public int getOptionShowMenu(){
		int option = 0; 
		System.out.println("<<<<< Welcome to Treasures & Dragons >>>>>");
		System.out.println(
				"1. Register Player.\n" +
				"2. Register Treasure.\n" +
				"3. Register enemy.\n" +
				"4. Change player score.\n" +
				"5. Level up player.\n" +
				"6. Show level enemies and treasures.\n" +
				"7. Show quantity of specific treasure in the game.\n" +
				"8. Show quantity of enemy type in the game.\n" +
				"9. Show treasure most repeated in the game.\n" +
				"10. Show enemy with the highest score in the game.\n" +
				"11. Show quantity of consonants of enemies in the game.\n" +
				"12. Show top 5 players with more score.\n" +
				"0. Exit. ");
		option = reader.nextInt(); 

		return option; 
	}

	public int getOptionShowScreenMenu(){
		int option = 0; 
		System.out.println("<<<<< Choose a resolution >>>>>");
		System.out.println(
				"1. SD (640x480 px).\n" +
				"2. QHD (960x540 px).\n" +
				"3. HD (1280x720 px).\n" +
				"4. FHD (1920x1080 px).\n" +
				"5. QHD (2560x1440 px).\n" +
				"6. UHD (3840x2160 px).\n" +
				"7. UHD 8K (7680x4320 px)."); 
		option = reader.nextInt(); 

		return option; 
	}

	public int getOptionShowTypeEnemyMenu(){
		int option = 0; 
		System.out.println("<<<<< Choose a enemy type >>>>>");
		System.out.println(
				"1. Ogre.\n" +
				"2. Abstract.\n" +
				"3. Boss.\n" +
				"4. Magic.\n"); 

		option = reader.nextInt(); 

		return option; 
	}
	

	/**
	* playerRegister: Solicits the player information and add player to game.  
	*/

	public void playerRegister(){
		String nickName;
		String name;
		String msj;
		nickName = getNickName();
		System.out.println("Type name: ");
		name = reader.next();
		msj = videoGame.addPlayer(name, nickName);
		System.out.println(msj);

	}

	/**
	* registerTreasureToLevel: Solicits the treasure information and add treasure to game and level.  
	*/


	public void registerTreasureToLevel(){
		String msj = "The level is not exist.";
		System.out.println("Type level to add treasure:");
		int posLevel = reader.nextInt();
		int pos = videoGame.searchLevelByIdNumber(posLevel);
		if (pos != -1) {
			System.out.println("Type name: ");
			String name = reader.next();
			System.out.println("Type image url: ");
			String image = reader.next();
			System.out.println("Type points to add: ");
			int points = reader.nextInt();
			System.out.println("How many " + name + " add: ");
			int quantity = reader.nextInt();
			msj = videoGame.addTreasureToLevel(name, image, points, pos, heightSize, widthSize, quantity);
		}

		System.out.println(msj);
	}

	/**
	* registerEnemyToLevel: Solicits the enemy information and add enemy to game and level.  
	*/

	public void registerEnemyToLevel(){
		String msj = "The level is not exist.";
		System.out.println("Type level to add enemy:");
		int posLevel = reader.nextInt();
		int pos = videoGame.searchLevelByIdNumber(posLevel);
		if (pos != -1) {
			System.out.println("Type name: ");
			String name = reader.next();
			int option = getOptionShowTypeEnemyMenu();
			System.out.println("Type points that the enemy add: ");
			int addPoints = reader.nextInt();
			System.out.println("Type points that the enemy eliminate: ");
			int eliminatePoints = reader.nextInt();
			msj = videoGame.addEnemyToLevel(name, option, addPoints, eliminatePoints, pos, heightSize, widthSize);
		}

		System.out.println(msj);
	}


	/**
	* changePlayerScore: Solicits the player nick name and change the player score.  
	*/

	public void changePlayerScore(){
		int newScore = 0;
		System.out.println("Type nickName to change score: ");
		String nickName = reader.next();
		String msj = "This player doesn't exist.";
		int pos = videoGame.searchPlayerByNickName(nickName);
		if (pos != -1){
			System.out.println("Type new score: ");
			newScore = reader.nextInt();
			msj = videoGame.changeScore(newScore, pos);
		}
		System.out.println(msj);
	}

	/**
	* levelUpPlayer: Solicits the player nickname and verify the score.  
	*/

	public void levelUpPlayer(){
		System.out.println("Type nickName to level up: ");
		String nickName = reader.next();
		String msj = "This player doesn't exist.";
		int pos = videoGame.searchPlayerByNickName(nickName);
		if (pos != -1){
			msj = videoGame.checkLevelUp(pos);
		}
		System.out.println(msj);
	}

	/**
	* consultEnemyTreasuresOfLevel: Solicits the level number and shows the enemys and tresures of level.  
	*/

	public void consultEnemyTreasuresOfLevel(){
		String msj = "The solicited level doesn't exist";
		System.out.println("Type the number level to consult: ");
		int posLevel = reader.nextInt();
		int pos = videoGame.searchLevelByIdNumber(posLevel);
		if (pos != -1) {
			msj = videoGame.consultLevelInformation(pos);
			System.out.println(msj);
		}
	}

	/**
	* consultQuantityTreasure: Solicits the treasure name and shows the tresures with the name.  
	*/

	public void consultQuantityTreasure(){
		System.out.println("Type treasure name: ");
		String treasureName = reader.next();
		String msj = videoGame.countTreasure(treasureName);
		System.out.println(msj);
	}

	/**
	* consultQuantityEnemy: Solicits the enemy type and shows the enemies with the type.  
	*/

	public void consultQuantityEnemy(){
		EnemyType type;
		int option = getOptionShowTypeEnemyMenu();
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
		String msj = videoGame.countEnemy(type);
		System.out.println(msj);
	}

	/**
	* showMostRepeatedTreasure: Shows the most repetead treasure.  
	*/

	public void showMostRepeatedTreasure(){
		String msj = videoGame.getMostRepeatedTreasure();
		System.out.println(msj);
	}

	/**
	* showHigherScoreEnemy: Shows the higher score enemy.  
	*/

	public void showHigherScoreEnemy(){
		String msj = videoGame.getHigherScoreEnemy();
		System.out.println(msj);
	}

	/**
	* showHigherScoreEnemy: Shows the quantity of consonants of every enemy.  
	*/

	public void showEnemyConsonantsQuantity(){
		String msj = videoGame.consonantsInEnemies();
		System.out.println(msj);
	}

	/**
	* showPLayersTop: Shows the top 5 of higher score player in game.  
	*/

	public void showPLayersTop(){
		String msj = videoGame.getOrderedScores();
		System.out.println(msj);
	}

	public void executeOption(int option){

		switch(option){
			case 1: 
				playerRegister();
				break; 

			case 2: 
				registerTreasureToLevel();
				break;

			case 3: 
				registerEnemyToLevel();
				break; 

			case 4: 
				changePlayerScore();
				break;

			case 5: 
				levelUpPlayer();
				break; 

			case 6: 
				consultEnemyTreasuresOfLevel();
				break;

			case 7: 
				consultQuantityTreasure();
				break;

			case 8: 
				consultQuantityEnemy();
				break;

			case 9: 
				showMostRepeatedTreasure();
				break;

			case 10:
				showHigherScoreEnemy();
				break;

			case 11:
				showEnemyConsonantsQuantity();
				break;

			case 12:
				showPLayersTop();
				break;

			case 0: 
				System.out.println("Exit program.");
				break; 

			default: 
				System.out.println("Invalid Option");
				break; 
		}
	}


	public void executeScreenOption(int option){


		switch(option){
			case 1: 
				heightSize = 480;
				widthSize = 640;
				
				break; 

			case 2: 
				heightSize = 540;
				widthSize = 960;
				break; 

			case 3: 
				heightSize = 720;
				widthSize = 1280;
				break; 
			
			case 4: 
				heightSize = 1080;
				widthSize = 1920;
				break; 
			
			case 5: 
				heightSize = 1440;
				widthSize = 2560;
				break; 
			
			case 6: 
				heightSize = 2160;
				widthSize = 3840;
				break; 

			case 7: 
				heightSize = 4320;
				widthSize = 7680;
				break;

			default: 
				System.out.println("Invalid Option");
				break; 
		}
	}

	public int validateIntegerOption(){
		int option = 0; 

		if(reader.hasNextInt()){
			option = reader.nextInt(); 
		}
		else{
			// clear reader. 
			reader.nextLine(); 
			option = -1; 
		}

		return option; 
	}

	private String banner(){
		return
			" " +  "\n"
			+	"\n"	+	"                                                                                "
			+	"\n"	+	"                        dd                                                      "
			+	"\n"	+	"                     xxxkx  llll         xkxdddd                                "
			+	"\n"	+	"                  xk  x c;,''''',;cl     dk d::cl dd     lll                    "
			+	"\n"	+	"                xkkxk ;'.',;,'.....':l     dd;''',::cl dd llllllll              "
			+	"\n"	+	"                kk x '';;,,''''..''...:l     c,',,,,,;,;ccl d llccccl           "
			+	"\n"	+	"           dddl kxdl,',,...,c  lc:,,'..,:c   c,,'''',;;:;,',;:l  llc;:l         "
			+	"\n"	+	"         ddddddxkkdc:;....': dk xl;,,,'..'c  c;;,,''''',:c:,'',;;:::,,c         "
			+	"\n"	+	"        ddddddxk d:;:'.,,'',;: kkx :,''...',;,';,,,','','';;:c:;,,,;;;:l        "
			+	"\n"	+	"        dddddxkkd;,c:.'cc;'.., 00 kd:'.....'....,','','',,''';cllllc:cl         "
			+	"\n"	+	"        dddddxkxc;::;c d :,;;,l 0 xl;,,...','...''',..;'.',,,',;lxkdll          "
			+	"\n"	+	"          dddxx ;,;ldddd cd :,x kxd dc,...;:;,'..'';,.,;'..,;:c;,:l cl          "
			+	"\n"	+	"          dddddxl:lddddddx  ,cdk0KKk :,'.; c::;,'..';'.,:,..,lxdl::cl           "
			+	"\n"	+	"                        dk ,l00xxxxdddc,':l:;:;,,.  .'';cl:,'; lcllll           "
			+	"\n"	+	"                        dd:;d0XKKKKK0x:','...,;'..   ....;ll:,,:l               "
			+	"\n"	+	"                       dkd,.: xk0 xdl,'','...;l:..   ... .;l c;:                "
			+	"\n"	+	"            dd l ll   dkx:.':cldd c:;'.....': c,.. ........'c ll                "
			+	"\n"	+	"          dxl,''',c l:;,'..''..':c,......';ll;.. ..',.....  .,l                 "
			+	"\n"	+	"         dkl...',ll,'...'''''...:;....,::ll,..   .,c:.....   .'c                "
			+	"\n"	+	"         x '.',,lx:.':ccc:;,,'.,l,....',,;'..   ..;:;,..... ....c               "
			+	"\n"	+	"        dx:..',; l..,;:::;,,,'.c '.'...,:cc.    ..:c;,..........'l              "
			+	"\n"	+	"        dd,....: ;...'',,,,''..;l......cxxc.   ..':l:;....'......,l             "
			+	"\n"	+	"        l:.....,c' ............';......cxd,    .'::lc;'',:c:,.....,l            "
			+	"\n"	+	"       l:,......,...............'.    .,:;.  .. .clllllldkkdc;;'...;l           "
			+	"\n"	+	"          lcc::cc,,;,,:clc,',,,,,.....',;,...   .c      d 0xdxl,'...c           "
			+	"\n"	+	"        dl,.'..,,,;;'':ll:,.',;:;,,,,,,,;'.     .c       ddk00 :,...;           "
			+	"\n"	+	"        xl.    .'.''.;dxlc:'. ............      .c         k0k  ;...'l          "
			+	"\n"	+	"        xx,.....,....;d :;;'. ...      .'.      .c          xkkx:....l          "
			+	"\n"	+	"        xd,.....:'...'cl;,,.  .'........,'.     'c          d  x:...,l          "
			+	"\n"	+	"        d ,... .;'....'::;..  .,.......';;.     .c           xkd;...;           "
			+	"\n"	+	"         l'... .'.......'.. . .'........;;.     .c           k l...,l           "
			+	"\n"	+	"         :. .. .............. ....  .  .,,.     .:llllllll  xkl...,l            "
			+	"\n"	+	"         :......'.  ......... ....     ....     .';;:ccll    ;...:l             "
			+	"\n"	+	"        ddc'',,::'...............   .......     .',:cl   l:,...;l               "
			+	"\n"	+	"        dxc''',;;,,;:::;;;,''',;'.......... ....,:cl   c;'..,:l                 "
			+	"\n"	+	"          c:::::::::;;;;;;,,,',;....''.',,'..';cl   l:,'';:l                    "
			+	"\n"	+	"                      lllllcccc:::;;;,,,;;,,:l  lc:;;;:c                        "
			+	"\n"	+	"                                    lll   lllccc::cc                            "
			+	"\n"	+	"                                 lc::clllc:::ccl                                "
			+	"\n"	+	"                                c;;;:;::ccl                                     ";
	}

}