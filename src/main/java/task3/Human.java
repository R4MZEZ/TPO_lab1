package task3;

import java.util.HashSet;
import java.util.Set;

public class Human {

  public String name;
  public int age;
  public int coordX;
  public int coordY;
  public Set<Human> fieldOfVision;
  private int rangeOfVision;

  public Human(String name, int age) {
    this.name = name;
    this.age = age;
    fieldOfVision = new HashSet<>();
  }

  public boolean makeStep(boolean forward, boolean axisX) {
    int newX;
    int newY;
    int step = forward ? 1 : -1;

    newX = axisX ? coordX + step : coordX;
    newY = axisX ? coordY : coordY + step;

    for (Human c : fieldOfVision) {
      if (c.coordX == newX || c.coordY == newY)
        return false;
    }
    coordX = newX;
    coordY = newY;
    return true;
  }

  public int getRangeOfVision() {
    return rangeOfVision;
  }

  public void setRangeOfVision(int rangeOfVision) {
    this.rangeOfVision = rangeOfVision;
  }
}
