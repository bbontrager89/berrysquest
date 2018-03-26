import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class bbh extends PApplet {

PFont font;

VCamera cam = new VCamera();
VTileset tileset;
VRoom room;

public void setup() {
  
  frameRate(60);
  background(0);
  noStroke();
  fill(255);
  font = loadFont("Consolas-12.vlw");
  tileset = new VTileset(new PImage[] {
    loadImage("metalwall.png"), // 0.0
    loadImage("metalplate.png"), // 0.1
    loadImage("metalplate.png"), // 0.2
    loadImage("metalplate.png"), // 0.3
    loadImage("metalplate.png"), // 0.4
    loadImage("metalplate.png"),//0.5
    loadImage("metalplate.png"),//0.6
    loadImage("metalplate.png"),//0.7
    loadImage("metalplate.png"),//0.8
    loadImage("metalplate.png"),//0.9
    loadImage("metalplate.png"), // 1.0
    loadImage("metalplatenw.png"),//1.1
    loadImage("metalplaten.png"),//1.2
    loadImage("metalplatene.png"),//1.3
    loadImage("metalplatew.png"),//1.4
    loadImage("metalplatec.png"),//1.5
    loadImage("metalplatee.png"),//1.6
    loadImage("metalplatesw.png"),//1.7
    loadImage("metalplates.png"),//1.8
    loadImage("metalplatese.png"),//1.9
    loadImage("metalgrate.png") // 2.0
  });
  room = new VRoom(new float[][] {
  {0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f},
  {0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f},
  {0.0f, 1.0f, 2.0f, 1.1f, 1.2f, 1.2f, 1.3f, 2.0f, 1.0f, 0.0f},
  {0.0f, 1.0f, 1.0f, 1.4f, 1.5f, 1.5f, 1.6f, 1.0f, 1.0f, 0.0f},
  {0.0f, 1.0f, 1.0f, 1.4f, 0.0f, 0.0f, 1.6f, 1.0f, 1.0f, 0.0f},
  {0.0f, 1.0f, 1.0f, 1.4f, 0.0f, 0.0f, 1.6f, 1.0f, 1.0f, 0.0f},
  {0.0f, 1.0f, 1.0f, 1.4f, 1.5f, 1.5f, 1.6f, 1.0f, 1.0f, 0.0f},
  {0.0f, 1.0f, 2.0f, 1.7f, 1.8f, 1.8f, 1.9f, 2.0f, 1.0f, 0.0f},
  {0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f},
  {0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f},
  {0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f}
}, new int[][] {{1, 1, 5}, {1, 7, 5}, {8, 1, 5}, {8, 7, 5}}, createGraphics(10 * 64, 11 * 64 + 64));
  cam.pos.x = (room.tiles.length * 64) / 2.0f;
  cam.pos.y = (room.tiles.length * 64) / -2.0f;
}

int x = 200;

public void draw() {
  if (keys[22])
    cam.up();
  if (keys[0])
    cam.left();
  if (keys[18])
    cam.down();
  if (keys[3])
    cam.right();
  if (keys[16])
    cam.scale /= 1.05f;
  if (keys[4])
    cam.scale *= 1.05f;
  background(0);
  cam.shift();
  room.display();
  cam.unshift();
  fill(255);
  textFont(font);
  text("Framerate: (" + (int)frameRate + " / 60)"
    + "\nSteps: " + frameCount
    + "\n--------------------------------"
    + "\nPosition: (" + (round(cam.pos.x) == cam.pos.x ? "" : "~") + round(cam.pos.x) + ", "
  /**/    + (round(cam.pos.y) == cam.pos.y ? "" : "~") + round(cam.pos.y) + ")"
    + "\nMouse: (" + (round(cam.mouse.x) == cam.mouse.x ? "" : "~") + round(cam.mouse.x) + ", "
  /**/    + (round(cam.mouse.y) == cam.mouse.y ? "" : "~") + round(cam.mouse.y) + ")"
    + "\nScale: " + cam.scale + "x", 2, 2, 226, 89);
  noStroke();
}

boolean[] keys = new boolean[26];

public void keyPressed() {
  if (keyCode > 64 && keyCode < 91)
    keys[keyCode - 65] = true;
}

public void keyReleased() {
  if (keyCode > 64 && keyCode < 91)
    keys[keyCode - 65] = false;
}
class Bullet{
  int x;
  int y;
  int size;
  int vx;
  int vy;
  int baseDam;
  double multDam = baseDam * 1.0f; //for them power ups xd
  
  Bullet(){
    baseDam = 5;
    multDam = baseDam * 1.0f; //for powerups :D
    size = 5; //is this too small
  }//finish, because I'm sure this ain't done ~bb
  
  //Bullet(int baseDam, 
  
  public boolean canPhase(VTileset tileset, float id){
    if(tileset.isWall(id))
      return false;
    else
      return true;
  }
  
  
  public void update(int x, int y){
    x += vx;
    y += vy;
  }
  
  public void display(){
    fill(0); // color of bullet
    ellipse(x, y, size, size); // perfect circular bullet shape
  }
  
  /*
  To Do:
  -Damage
  -can bullet go through a solid object? (can be useful if we decide to make a phasegun bullet) check
  -
  */
  
  //Not sure if we need these if we can just do something like Bullet.x = yaddaspam, but if you don't like it then delete it. ~BB
  public int getX(){
    return x;
  }
  
  public void setX(int x){
    this.x = x;
  }
  
  public int getY(){
    return y;
  }
  
  public void setY(int y){
    this.y = y;
  }
  
  public int getSize(){
    return size;
  }
  
  public void setSize(int size){
    this.size = size;
  }
  
  public int getVX(){
    return vx;
  }
  
  public void setVX(int vx){
    this.vx = vx;
  }
  
  public int getVY(){
    return vy;
  }
  
  public void setVY(int vy){
    this.vy = vy;
  }
  
  
  
  
  
  
  
}
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
class Player extends Entity{
    /*
  To do:
  -Move functions
  -Shoot functions
  -Collide check
  -Health update function (right?)
  -player entity needs to be at the center of the camera
  */
  
  
  
}
class VCamera {
  // imported from Hive, apologies for any bad code
  // . . . but it'll work for our specifications :) -vm
  PVector pos;
  float scale;
  PVector mouse;

  VCamera() {
    this.pos = new PVector(0, 0);
    this.scale = 1;
    this.mouse = new PVector(0, 0);
  }

  public void shift() {
    pushMatrix();
    translate(width / 2, height / 2);
    translate(-this.pos.x * this.scale, this.pos.y * this.scale);
    scale(this.scale);
  }

  public void unshift() {
    popMatrix();
  }

  public void up() {
    this.pos.y += 10 / this.scale;
  }

  public void left() {
    this.pos.x -= 10 / this.scale;
  }

  public void down() {
    this.pos.y -= 10 / this.scale;
  }

  public void right() {
    this.pos.x += 10 / this.scale;
  }

  public void in() {
    this.scale /= 1.05f;
  }

  public void out() {
    this.scale *= 1.05f;
  }

  public void mouse() {
    mouse.x = (-width / 2 + mouseX) / this.scale + this.pos.x;
    mouse.y = (height / 2 - mouseY) / this.scale + this.pos.y;
  }

  public void drag() {
    this.pos.x -= (mouseX - pmouseX) / this.scale;
    this.pos.y += (mouseY - pmouseY) / this.scale;
  }

  public void wheel(float e) { // TODO: zoom toward mouse pos
    if (e > 0)
      this.scale /= 1.1025f;
    else if (e < 0)
      this.scale *= 1.1025f;
  }
}
class VRoom {
  float[][] tiles;
  int[][] lights;
  PGraphics lightmap;
  
  VRoom(float[][] tiles, int[][] lights, PGraphics lightmap) {
    this.tiles = tiles;
    this.lights = lights;
    this.lightmap = lightmap;
    this.lightmap.beginDraw();
    for(int y = 0; y < this.tiles.length; y++) {
      for(int x = 0; x < this.tiles[y].length; x++) {
        float bright = 0.0f;
        for(int l = 0; l < this.lights.length; l++) {
          bright += this.lights[l][2] / (1 + Math.pow(dist(x, y, this.lights[l][0], this.lights[l][1]), 2));
        }
        if(bright > 1.0f) {
          bright = 1.0f;
        }
        this.lightmap.noStroke();
        this.lightmap.fill(0, 0, 0, (1.0f - bright) * 255);
        if(tileset.isWall(this.tiles[y][x])) {
          this.lightmap.rect(x * 64, y * 64, 64, 128);
        } else {
          this.lightmap.rect(x * 64, (y + 1) * 64, 64, 64);
        }
      }
    }
    this.lightmap.endDraw();
  }
  
  public void display() {
    for(int y = 0; y < this.tiles.length; y++) {
      for(int x = 0; x < this.tiles[y].length; x++) {
        tileset.drawTile(this.tiles[y][x], x * 64, y * 64);
        //if(y != 0 && !tileset.isWall(this.tiles[y][x]) && tileset.isWall(this.tiles[y - 1][x])) {
          //fill(0, 32);
          //rect(x * 64, y * 64, 64, 48);
        //}
      }
    }
    for(int l = 0; l < this.lights.length; l++) {
      fill(255);
      rect(this.lights[l][0] * 64 + 16, this.lights[l][1] * 64 + 24, 32, 16);
    }
    image(lightmap, 0, -64);
  }
}
class VTileset {
  PImage[] tiles;
  
  VTileset(PImage[] tiles) {
    this.tiles = tiles;
  }
  
  public boolean isWall(float id) {
    if(id < 1.0f) {
      return true;
    } else {
      return false;
    }
  }
  
  public void drawTile(float id, float x, float y) {
    if(!this.isWall(id)) {
      image(this.tiles[(int) (id * 10)], x, y);
    } else {
      image(this.tiles[(int) (id * 10)], x, y - 64);
    }
  }
}
  public void settings() {  fullScreen(FX2D); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#FF0A0A", "--hide-stop", "bbh" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
