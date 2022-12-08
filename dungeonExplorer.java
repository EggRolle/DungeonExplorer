import hsa.Console;
import java.awt.*;
import java.util.*;
import java.io.*;     
import javax.imageio.*;  

//Dungeon Explorer
//Nophil Mehboob
//Last Edited: Jan 19/2016
//Main class for dungeon explorer game  

public class dungeonExplorer
{
  static Console c;
  static Console d;

    
  public static void main(String[] args)throws IOException
  {
    c = new Console ();
    d = new Console(12,100);
    
    int dungeonChoice;
    int difficultyChoice;
    int villageChoice;
    String characterGender;
    int characterClass;
    String characterName;
    MyCharacter character;
    int partOfDay = 0;
    String theGame = "Y";
    double dayRandom;
    
    Image backgroundBox = ImageIO.read(new File("backgroundBox.png"));   
    Image girlImage = ImageIO.read(new File("girl.png"));
    Image boyImage = ImageIO.read(new File("boy.png"));
    Image tank = ImageIO.read(new File("tank.jpg"));
    Image assassin = ImageIO.read(new File("assassin.png"));
    Image warrior = ImageIO.read(new File("warrior.png")); 
    Image shop = ImageIO.read(new File("shop.jpg"));
    Image startScreen = ImageIO.read(new File("startScreen.png"));
    
    c.drawImage(startScreen,0,0,null);
    c.readString();
    c.clear();
    c.drawString("What gender would you like your character to be? (M/F)",2,300);
    c.drawImage(girlImage,200,40,null);
    c.drawImage(boyImage,12,40,null);
    characterGender = c.readString();
    c.clear();
    
    c.drawString("What class would you like your character to be? (1,2,3)",2,300);
    c.drawImage(tank,12,40,null);
    c.drawString("1. Tank ",40,230);
    c.drawImage(assassin,200,40,null);
    c.drawString("2. Assassin ",200,230);
    c.drawImage(warrior,400,40,null);
    c.drawString("3. Warrior ",450,230);
    characterClass = c.readInt();
    c.clear();
    
    
    c.println("Enter the name you would like for your hero");
    characterName = c.readLine();
    
    character = new MyCharacter(characterGender,characterName,characterClass);
    character.classBonus();
    
    while(theGame.equals("Y")){
      partOfDay += 1;
      if(partOfDay == 3){
        character.addDay();
        partOfDay = 0;
      }
      if(character.getHealth() <= 0){
        character.setHealth(1);
      }
      if(character.checkLevel() == true){
        character.levelUp();
      }
      if(character.getDays()%2 == 0 && partOfDay == 0){
        dayRandom = Math.random();
        if(dayRandom < .34){
          d.clear();
          d.println("You found a pouch carrying 150g while wandering around town. Enter Ok to continue");
          character.earnMoney(150);
          d.readLine();
        }
        else if(dayRandom > .33 && dayRandom < .67){
          d.clear();
          d.println("You obtained new found strength and instantly gained a level. Enter Ok to continue");
          character.levelUp();
          d.readLine();
        }
        else{
          d.clear();
          d.println("You found a potion while wandering around town. Enter Ok to continue");
          character.findPotion();
          d.readLine();
        }
      }
      else{
      }
      d.clear();
      d.drawString("Selection Menu: ",5,30);
      d.drawString("1. Explore a dungeon",5,50);
      d.drawString("2. Rest to restore stats (50g)",5,70);
      d.drawString("3. Visit the shop",5,90);
      d.drawString("4. Use a potion: " + character.getPotions() + " left",5,110);
      d.drawString("5. Quit the game",5,130);
      dungeonExplorer.drawTown(character);
      villageChoice = d.readInt();
      if(villageChoice == 1){
        d.clear();
        d.drawString("Selection Menu: ",5,30);
        d.drawString("1. Explore a cave",5,50);
        d.drawString("2. Explore the beach",5,70);
        d.drawString("3. Explore the forest",5,90);
        d.drawString("4. Explore a desert",5,110);
        d.drawString("5. Return to town",5,130);
        dungeonChoice = d.readInt();
        d.clear();
        if(dungeonChoice == 5){
        }
        else{
        d.drawString("What difficulty would you like? (1-5)",5,50);
        difficultyChoice = d.readInt();
        dungeonExplorer.battle(dungeonChoice,difficultyChoice,character);
        }
    }
      else if(villageChoice == 2){
        d.clear();
        character.rest();
        d.println("You feel refreshed. Health and mana have been returned to full. Enter Ok to continue");
        d.readLine();
        
      }
      else if (villageChoice == 3){
        dungeonExplorer.shop(character);
  }
      else if(villageChoice == 4){
        d.clear();
        d.println();
        if(character.usePotion() == true){
          d.println("You gained 50 health! Enter Ok to continue");
          d.readLine();
          if(character.getHealth() > character.getMaxHealth()){
            character.healthCap();
          }
        }
        else{
          d.println("You don't have any potions! Enter Ok to continue");
          d.readLine();
        }
      }
      else if(villageChoice == 5){
        d.clear();
        d.println();
        d.println("Are you sure? (Y/N)");
        String quitChoice = d.readLine();
        if(quitChoice.equalsIgnoreCase("Y")){
          System.exit(0);
        }
        else{
           }
           
      }
    }
    }
  
  public static void drawTown(MyCharacter character){
    try{
     Image statsBox = ImageIO.read(new File("statsBox.png"));
     Image village = ImageIO.read(new File("village.png"));
     c.drawImage(village,0,0,null);
     c.drawImage(statsBox,0,0,null);
     c.setCursor(2,6);
     c.setColor(Color.blue);
     c.print("Name: " + character.getName());
     c.setCursor(3,6);
     c.print("Health: " + character.getHealth() + "/" + character.getMaxHealth() + "       Exp: " + character.getExp() + "/" + character.getMaxExp() + "         " + character.getSword());
     c.setCursor(4,6);
     c.print("Mana: " + character.getMana() + "/" + character.getMaxMana() + "         Days Passed: " + character.getDays() + "     " + character.getShield() + "  Potions: " + character.getPotions());
     c.setCursor(2,28);
     c.print("Level: " + character.getLevel() + "           " + "Gold: " + character.getGold() + "   Strength: " + character.getStrength());
     character.drawCharacter(c);
     
    }
     catch(IOException e){
     }
  }
  public static void battle(int dungeonChoice, int difficultyChoice, MyCharacter character){
    MyMonster monster = new MyMonster(dungeonChoice,difficultyChoice);
    int battleChoice;
    while(character.checkDead() == false && monster.checkDead() == false){
      d.clear();
      dungeonExplorer.drawBattle(character,monster);
      dungeonExplorer.drawBattleStats(character,monster);
      
      character.takeDamage(monster.generateDamage());
      
      //Player give damage
      d.drawString("Selection Menu: ",5,30);
      d.drawString("1. Use a regular Attack",5,50);
      d.drawString("2. Use a skill",5,70);
      d.drawString("3. Use a potion: " + character.getPotions() + " left",5,90);
      d.drawString("4. Run away",5,110);
      battleChoice = d.readInt();
      if(battleChoice == 1){
        d.clear();
        monster.takeDamage(character.generateDamage(0,d));
        d.println("You dealt " + character.getStrength() + " hit points to the monster!");
        d.println();
        d.println("The monster dealt " + monster.getStrength() + " hit points to you! Enter Ok to continue");
        d.readLine();
        if(monster.getHealth() < 0){
          d.println("The monster has been defeated! Enter Ok to continue");
          if(character.getExp() + monster.getExp() > character.getMaxExp()){
            d.println("Your character has leveled up. Enter Ok to continue");
            d.readLine();
        
      }
        }
        else if(character.getHealth() <1){
          d.println("Your character has died! Enter Ok to continue");
          d.readLine();
      }
      }
      else if(battleChoice == 2){
        d.clear();
        d.drawString("Selection Menu: ",5,30);
        d.drawString("1. Use heavy slash (25 mana)",5,50);
        d.drawString("2. Use spirit strike (50 mana)",5,70);
        d.drawString("3. Use final frontier (75 mana)",5,90);
        int battleChoice2 = d.readInt();
        int skillDamage = character.generateDamage(battleChoice2,d);
        monster.takeDamage(skillDamage);
        d.clear();
        d.println();
        if(skillDamage == character.getStrength()){
        d.println("You dealt " + character.getStrength() + " hit points to the monster!");
        }
        else if(skillDamage == character.getStrength()*2){
        d.println("You dealt " + character.getStrength()*2 + " hit points to the monster!");
        }
        else if(skillDamage == character.getStrength()*3){
        d.println("You dealt " + character.getStrength()*3 + " hit points to the monster!");
        }
        else if(skillDamage == character.getStrength()*4){
        d.println("You dealt " + character.getStrength()*4 + " hit points to the monster!");
        }
        d.println();
        d.println("The monster dealt " + monster.getStrength() + " hit points to you! Enter Ok to continue");
        d.readLine();
        if(monster.getHealth() < 1){
          d.println("The monster has been defeated! Enter Ok to continue");
          d.readLine();
          if(character.getExp() + monster.getExp() > character.getMaxExp()){
            d.println("Your character has leveled up. Enter Ok to continue");
            d.readLine();
    }
          }
        else if(character.getHealth() <1){
          d.println("Your character has died! Enter Ok to continue");
          d.readLine();
      }
      }
      else if(battleChoice == 3){
        d.clear();
        if(character.usePotion() == true){
          d.println("Your character has healed 50 points! Enter Ok to continue");
          d.readLine();
          if(character.getHealth() > character.getMaxHealth()){
            character.healthCap();
          }
        }
        else{
          d.println("Your character had no potions! Enter Ok to continue");
          d.readLine();
          
        }
        if(character.getHealth() <1){
          d.println();
          d.println("Your character has died! Enter Ok to continue");
          d.readLine();
      }
      else{
        
      }
      
      //Player take damage
      
      }
      else if(battleChoice == 4){
        break;
    }
      
      if(monster.checkDead() == true){
        character.gainExp(monster.getExp());
        character.earnMoney(monster.getGold());
      }
      else{
      }
      }
      
    }

  public static void drawBattle(MyCharacter character, MyMonster monster){
    monster.drawMonster(c);
    character.drawCharacter(c);
}
  public static void drawBattleStats(MyCharacter character, MyMonster monster){
    //Character stats
    try{
      Image battleBox = ImageIO.read(new File("battleBox.png"));
      c.drawImage(battleBox,0,0,null);
    }
    catch(IOException e){
    }
    c.setCursor(2,2);
    c.print(character.getName() + " Level: " + character.getLevel());
    c.setCursor(3,2);
    c.print("Health: " + character.getHealth() + "/" + character.getMaxHealth() + " Mana: " + character.getMana() + "/" + character.getMaxMana());
    c.setCursor(4,2);
    c.print("Exp: " + character.getExp() + "/" + character.getMaxExp() + " Strength: " + character.getStrength());
    //Monster stats
    try{
      Image battleBox = ImageIO.read(new File("battleBox.png"));
      c.drawImage(battleBox,391,0,null);
    }
    catch(IOException e){
    }
    c.setCursor(2,50);
    c.print(monster.getName() + " Level: " + monster.getLevel());
    c.setCursor(3,50);
    c.print("Health: " + monster.getHealth() + "/" + monster.getMaxHealth() + " Strength: " + monster.getStrength());
    c.setCursor(4,50);
    c.print("Exp: " + monster.getExp() + " Gold: " + monster.getGold());
}
  public static void shop(MyCharacter character){
    c.clear();
    d.clear();
    try{
    Image shop = ImageIO.read(new File("shop.png"));
    c.drawImage(shop,0,0,null);
    }
    catch(IOException e){
    }
    d.drawString("Selection Menu: ",5,30);
    d.drawString("1. Buy a potion (25 gold)",5,50);
    if(character.getSwordLevel() == 1){
      d.drawString("2. Buy an iron sword (100 gold)",5,70);
    }
    else if(character.getSwordLevel() == 2){
      d.drawString("2. Buy a gold sword (300 gold)",5,70);  
    }
    else if(character.getSwordLevel() == 3){
      d.drawString("2. Buy a diamond sword (500 gold)",5,70);
    }
    if(character.getShieldLevel() == 1){
      d.drawString("3. Buy an iron shield (100 gold)",5,90);
    }
    else if(character.getShieldLevel() == 2){
      d.drawString("3. Buy a gold shield (300 gold)",5,90);  
    }
    else if(character.getShieldLevel() == 3){
      d.drawString("3. Buy a diamond shield (500 gold)",5,90);
    }
    d.drawString("4. Leave",5,110);
    int shopChoice = d.readInt();
    if (shopChoice == 1){
      character.buyPotion();
    }
    else if (shopChoice == 2){
      character.buySword();
    }
    else if (shopChoice == 3){
      character.buyShield();
    
}
}
}