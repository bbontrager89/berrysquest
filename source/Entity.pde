//Going to copy and paste an entity class from my past project and edit it as necessary here.

class Entity
{

  String name;
  int hp; // Health in hearts, hearts measured every 10 HP
  int maxHP;
  int spd; // Speed
  
  Entity(){
    name = "Unnamed Entity";
    hp = 30;
    maxHP = 30; // 3 hearts, we'll go with a hearts system in this game, but to make it easy
    //we will have it in tens so that we can have bullets that don't do float damage :)
    spd = 1;
  }
  
  /**
   * Enter in this order... (RPG player obj)
   * @param name
   * @param hp
   * @param maxHP
   * @param spd
   */
  Entity(String name, int hp, int maxHP, int spd){
    this.name = name;
    this.hp = hp;
    this.maxHP = maxHP;
  }
  

  
}