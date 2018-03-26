PFont font;

VCamera cam = new VCamera();
VTileset tileset;
VRoom room;

void setup() {
  fullScreen(FX2D);
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
  {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0},
  {0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0},
  {0.0, 1.0, 2.0, 1.1, 1.2, 1.2, 1.3, 2.0, 1.0, 0.0},
  {0.0, 1.0, 1.0, 1.4, 1.5, 1.5, 1.6, 1.0, 1.0, 0.0},
  {0.0, 1.0, 1.0, 1.4, 0.0, 0.0, 1.6, 1.0, 1.0, 0.0},
  {0.0, 1.0, 1.0, 1.4, 0.0, 0.0, 1.6, 1.0, 1.0, 0.0},
  {0.0, 1.0, 1.0, 1.4, 1.5, 1.5, 1.6, 1.0, 1.0, 0.0},
  {0.0, 1.0, 2.0, 1.7, 1.8, 1.8, 1.9, 2.0, 1.0, 0.0},
  {0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0},
  {0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0},
  {0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0}
}, new int[][] {{1, 1, 5}, {1, 7, 5}, {8, 1, 5}, {8, 7, 5}}, createGraphics(10 * 64, 11 * 64 + 64));
  cam.pos.x = (room.tiles.length * 64) / 2.0;
  cam.pos.y = (room.tiles.length * 64) / -2.0;
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

void keyPressed() {
  if (keyCode > 64 && keyCode < 91)
    keys[keyCode - 65] = true;
}

void keyReleased() {
  if (keyCode > 64 && keyCode < 91)
    keys[keyCode - 65] = false;
}