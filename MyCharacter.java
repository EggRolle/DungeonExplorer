import hsa.Console;      
import java.awt.*;       
import java.io.*;        
import javax.imageio.*;  

//MyCharacter class file
//Nophil Mehboob
//Last Edited: Jan 18/2016
//Class file containing attibutes and methods for player created character

public class MyCharacter{
  
  private String gender;
  private String name;
  private int maxHealth;
  private int health;
  private int maxMana;
  private int mana;
  private int maxExp;
  private int exp;
  private int level;
  private int gold;
  private int strength;
  private int days;
  private int classChosen;
  private int potions;
  private String sword;
  private String shield;
  private int shieldLevel;
  private int swordLevel;
  
  public MyCharacter(){
    gender = "M";
    name = "Hero";
    maxHealth = 100;
    health = maxHealth;
    maxMana = 100;
    mana = maxMana;
    maxExp = 100;
    exp = 0;
    level = 1;
    gold = 0;
    strength = 25;
    days = 0;
    classChosen = 2;
    swordLevel = 1;
    shieldLevel = 1;
    sword = "Wooden sword";
    shield = "Wooden shield";
  }
  
  public MyCharacter(String genderChosen, String characterName, int classChosens){
    gender = genderChosen;
    name = characterName;
    maxHealth = 100;
    health = maxHealth;
    maxMana = 100;
    mana = maxMana;
    maxExp = 100;
    exp = 0;
    level = 1;
    gold = 0;
    strength = 25;
    days = 0;
    classChosen = classChosens;
    potions = 0;
    swordLevel = 1;
    shieldLevel = 1;
    sword = "Wooden sword";
    shield = "Wooden shield";
    if(name.equals("Hero")){
      gold = 50000;
    }
  }
  
   public void classBonus(){ 
     if (classChosen == 1){
      maxHealth += 50;
      health += 50;
     }
     else if (classChosen == 2){
      strength += 20;
     }
     else if (classChosen == 3){
       maxMana += 50;
       mana += 50;
     }
   }
   
   public void takeDamage(int damageTaken){
     health -= damageTaken;
   }
   
   public void earnMoney(int moneyEarned){
     gold += moneyEarned;
}
   
   public void gainExp(int expGained){
     exp += expGained;
   }
   
   public int getMaxHealth(){
     return maxHealth;
   }
   public int getStrength(){
     return strength;
   }
   
   public int getMaxMana(){
     return maxMana;
   }
   public int getMaxExp(){
     return maxExp;
   }
   
   public int getLevel(){
     return level;
   }
   public int getGold(){
     return gold;
   }
   
   public int getDays(){
     return days;
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
   public void setHealth(int healths){
     health = healths;
   }
   
   public int getMana(){
     return mana;
   }
   public int getPotions(){
     return potions;
   }
   public void healthCap(){
     health = maxHealth;
   }
   public void findPotion(){
     potions += 1;
   }
   public int generateDamage(int attackType, Console d){ 
     if(attackType == 0){
       return strength;
     }
     else if (attackType == 1 && mana >24){
       mana -= 25;
       return strength*2;
     }
     else if(attackType== 2 && mana >49){
       mana -= 50;
       return strength*3;
     }
     else if(attackType == 3 && mana >74){
       mana -= 75;
       return strength*4;
     }
     else{
     d.clear();
     d.println("Not enough mana, using a regular attack instead");
     d.readLine();
     return strength;
   }
   }
   public boolean checkLevel(){
     if(exp>maxExp){
       return true;
     }
     else{
       return false;
     }
   }
   public boolean checkDead(){
     if(health <= 0){
       return true;
     }
     else{
       return false;
     }
   }
   public boolean usePotion(){
     if(potions > 0){
       health += 50;
       potions -= 1;
       return true;
     }
     else{
       return false;
     }
}
   public void drawCharacter(Console c){
     if(gender.equalsIgnoreCase("M")){
       Image boyImage;
       try{
       boyImage = ImageIO.read(new File("boy.png"));
       c.drawImage(boyImage,25,225,null);
       }
       catch(IOException e){
         boyImage = null;
       }
     }
     else if(gender.equalsIgnoreCase("F")){
       Image girlImage;
       try{
       girlImage = ImageIO.read(new File("girl.png"));
       c.drawImage(girlImage,25,225,null);
     }
       catch(IOException e){
         girlImage = null;
       }
     
}
}
   public void addDay(){
     days += 1;
   }
   public void rest(){
     if(gold > 50){
       gold -= 50;
       health = maxHealth;
       mana = maxMana;
     }
     else{
     }
   }
   
   public int getSwordLevel(){
     return swordLevel;
   }
   
   public int getShieldLevel(){
     return shieldLevel;
   }
   
   public void buyPotion(){
     if (gold > 24){
       potions += 1;
       gold -= 25;
     }
     else{
     }
   }
   public String getSword(){
     return sword;
   }
   public String getShield(){
     return shield;
   }
   
   public void buySword(){
     if(swordLevel == 1 && gold > 99){
       swordLevel = 2;
       gold -= 100;
       strength += 30;
       sword = "Iron sword";
     }
     else if(swordLevel == 2 && gold > 299){
       swordLevel = 3;
       gold -= 300;
       strength += 50;
       sword = "Gold sword";
     }
     else if(swordLevel == 3 && gold > 499){
       swordLevel = 4;
       gold -= 500;
       strength += 100;
       sword = "Diamond sword";
     }
   }
      
   public void buyShield(){
     if(shieldLevel == 1 && gold > 99){
       shieldLevel = 2;
       gold -= 100;
       maxHealth += 30;
       shield = "Iron shield";
     }
     else if(shieldLevel == 2 && gold > 299){
       shieldLevel = 3;
       gold -= 300;
       maxHealth += 50;
       shield = "Gold shield";
     }
     else if(shieldLevel == 3 && gold > 499){
       shieldLevel = 4;
       gold -= 500;
       maxHealth += 100;
       shield = "Diamond shield";
     }
   }
   public void levelUp(){
     exp = 0;
     maxExp += 30;
     maxHealth += (int)(Math.random()*20 + 1);
     maxMana += (int)(Math.random()*30 + 1);
     strength += (int)(Math.random()*15 + 1);
     level += 1;
   }

}
