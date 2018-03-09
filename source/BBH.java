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

public class BBH extends PApplet {

PFont font;
PImage img, img2;
VCamera cam = new VCamera();

public void setup() {
  
  frameRate(60);
  background(0);
  noStroke();
  fill(255);
  font = loadFont("Consolas-12.vlw");
  img = loadImage("metalplate.png");
  img2 = loadImage("metalgrate.png");
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
  for(int i = 0; i < x; i++) {
    for(int j = 0; j < x; j++) {
      image(img, i * 64, j * 64);
      if(i == 5 && j == 7)
        image(img2, i * 64, j * 64);
    }
  }
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
  // vroom vroom -vm
  
}
  public void settings() {  fullScreen(FX2D); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#666666", "--hide-stop", "BBH" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
