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
        float bright = 0.0;
        for(int l = 0; l < this.lights.length; l++) {
          bright += this.lights[l][2] / (1 + Math.pow(dist(x, y, this.lights[l][0], this.lights[l][1]), 2));
        }
        if(bright > 1.0) {
          bright = 1.0;
        }
        this.lightmap.noStroke();
        this.lightmap.fill(0, 0, 0, (1.0 - bright) * 255);
        if(tileset.isWall(this.tiles[y][x])) {
          this.lightmap.rect(x * 64, y * 64, 64, 128);
        } else {
          this.lightmap.rect(x * 64, (y + 1) * 64, 64, 64);
        }
      }
    }
    this.lightmap.endDraw();
  }
  
  void display() {
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