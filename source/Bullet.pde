class Bullet{
  int x;
  int y;
  int size;
  int vx;
  int vy;
  int baseDam;
  double multDam = baseDam * 1.0; //for them power ups xd
  
  Bullet(){
    baseDam = 5;
    multDam = baseDam * 1.0; //for powerups :D
    size = 5; //is this too small
  }//finish, because I'm sure this ain't done ~bb
  
  //Bullet(int baseDam, 
  
  boolean canPhase(VTileset tileset, float id){
    if(tileset.isWall(id))
      return false;
    else
      return true;
  }
  
  
  void update(int x, int y){
    x += vx;
    y += vy;
  }
  
  void display(){
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
  int getX(){
    return x;
  }
  
  void setX(int x){
    this.x = x;
  }
  
  int getY(){
    return y;
  }
  
  void setY(int y){
    this.y = y;
  }
  
  int getSize(){
    return size;
  }
  
  void setSize(int size){
    this.size = size;
  }
  
  int getVX(){
    return vx;
  }
  
  void setVX(int vx){
    this.vx = vx;
  }
  
  int getVY(){
    return vy;
  }
  
  void setVY(int vy){
    this.vy = vy;
  }
  
  
  
  
  
  
  
}