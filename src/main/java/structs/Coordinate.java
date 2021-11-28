package structs;

public class Coordinate {
  private int x, y;
  public Coordinate parent;

  public Coordinate(int x, int y) {
    this.x = x;
    this.y = y;
    this.parent = null;
  }

  public Coordinate(int x, int y, Coordinate parent) {
    this.x = x;
    this.y = y;
    this.parent = parent;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public Coordinate getParent() {
    return parent;
  }

  public void setX(int x) {
    this.x = x;
  }

  public void setY(int y) {
    this.y = y;
  }

  public void setParent(Coordinate parent) {
    this.parent = parent;
  }
}
