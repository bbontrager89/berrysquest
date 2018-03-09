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

  void shift() {
    pushMatrix();
    translate(width / 2, height / 2);
    translate(-this.pos.x * this.scale, this.pos.y * this.scale);
    scale(this.scale);
  }

  void unshift() {
    popMatrix();
  }

  void up() {
    this.pos.y += 10 / this.scale;
  }

  void left() {
    this.pos.x -= 10 / this.scale;
  }

  void down() {
    this.pos.y -= 10 / this.scale;
  }

  void right() {
    this.pos.x += 10 / this.scale;
  }

  void in() {
    this.scale /= 1.05;
  }

  void out() {
    this.scale *= 1.05;
  }

  void mouse() {
    mouse.x = (-width / 2 + mouseX) / this.scale + this.pos.x;
    mouse.y = (height / 2 - mouseY) / this.scale + this.pos.y;
  }

  void drag() {
    this.pos.x -= (mouseX - pmouseX) / this.scale;
    this.pos.y += (mouseY - pmouseY) / this.scale;
  }

  void wheel(float e) { // TODO: zoom toward mouse pos
    if (e > 0)
      this.scale /= 1.1025;
    else if (e < 0)
      this.scale *= 1.1025;
  }
}