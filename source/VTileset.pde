class VTileset {
  PImage[] tiles;
  
  VTileset(PImage[] tiles) {
    this.tiles = tiles;
  }
  
  boolean isWall(float id) {
    if(id < 1.0) {
      return true;
    } else {
      return false;
    }
  }
  
  void drawTile(float id, float x, float y) {
    if(!this.isWall(id)) {
      image(this.tiles[(int) (id * 10)], x, y);
    } else {
      image(this.tiles[(int) (id * 10)], x, y - 64);
    }
  }
}