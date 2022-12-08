import hsa.Console;      
import java.awt.*;       
import java.io.*;        
import javax.imageio.*;  

//MyMonster class file
//Nophil Mehboob
//Last Edited: Jan 19/2016
//Class file containing attibutes and methods for monsters found

public class MyMonster{
  
  private int code;
  private String name;
  private int maxHealth;
  private int health;
  private int level;
  private int gold;
  private int strength;
  private int exp;

  public MyMonster(int monsterCode, int difficulty){
    code = monsterCode;
    if (code == 1){
      name = "Minotaur";
    }
    else if (code == 2){
      name = "Octopus";
    }
    else if (code == 3){
      name = "Ent";
    }
    else{
      name = "Cactuar";
    }
    difficulty -= 1;
    int monsterMax = difficulty*10+10;
    level = (int)(Math.random()*monsterMax)+difficulty*10;
    maxHealth = 50 + (20*level);
    health = maxHealth;
    gold = 7*level;
    strength = 5*level;
    exp = 20+10*level;
  }
  
  public void takeDamage(int damageTaken){
     health -= damageTaken;
   }
   
   public int getMaxHealth(){
     return maxHealth;
   }
   public int getStrength(){
     return strength;
   }
   
   public int getLevel(){
     return level;
   }
   public int getGold(){
     return gold;
   }
   
   public int getHealth(){
     return health;
   }
   
   public String getName(){
     return name;
   }
   
   public int getExp(){
     return exp;
   }
   public int generateDamage(){ 
       return strength;
     }
   public boolean checkDead(){
     if(health <= 0){
       return true;
     }
     else{
       return false;
     }
   }
   public void drawMonster(Console c){
     if(code == 1){
       Image cave;
       Image caveMonster;
       try{
       cave = ImageIO.read(new File("cave.png"));
       c.drawImage(cave,0,0,null);
       caveMonster = ImageIO.read(new File("caveMonster.png"));
       c.drawImage(caveMonster,500,225,null);
       }
       catch(IOException e){
         cave = null;
         caveMonster = null;
       }
     }
     else if(code == 2){
       Image beach;
       Image beachMonster;
       try{
       beach = ImageIO.read(new File("beach.jpg"));
       c.drawImage(beach,0,0,null);
       beachMonster = ImageIO.read(new File("beachMonster.png"));
       c.drawImage(beachMonster,500,225,null);
       }
       catch(IOException e){
         beach = null;
         beachMonster = null;
       }
     }
     else if(code == 3){
       Image forest;
       Image forestMonster;
       try{
       forest = ImageIO.read(new File("forest.gif"));
       c.drawImage(forest,0,0,null);
       forestMonster = ImageIO.read(new File("forestMonster.png"));
       c.drawImage(forestMonster,500,225,null);
       }
       catch(IOException e){
         forest = null;
         forestMonster = null;
   }
     }
     else if(code == 4){
       Image desert;
       Image desertMonster;
       try{
       desert = ImageIO.read(new File("desert.png"));
       c.drawImage(desert,0,0,null);
       desertMonster = ImageIO.read(new File("desertMonster.png"));
       c.drawImage(desertMonster,500,225,null);
       }
       catch(IOException e){
         desert = null;
         desertMonster = null;
   }
     }
   }
}