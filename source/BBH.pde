PFont font;
PImage img, img2;
VCamera cam = new VCamera();

void setup() {
  fullScreen(FX2D);
  frameRate(60);
  background(0);
  noStroke();
  fill(255);
  font = loadFont("Consolas-12.vlw");
  img = loadImage("metalplate.png");
  img2 = loadImage("metalgrate.png");
}

int x = 200;

void draw() {
  if (keys[22])
    cam.up();
  if (keys[0])
    cam.left();
  if (keys[18])
    cam.down();
  if (keys[3])
    cam.right();
  if (keys[16])
    cam.scale /= 1.05;
  if (keys[4])
    cam.scale *= 1.05;
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

void keyPressed() {
  if (keyCode > 64 && keyCode < 91)
    keys[keyCode - 65] = true;
}

void keyReleased() {
  if (keyCode > 64 && keyCode < 91)
    keys[keyCode - 65] = false;
}