package task3;

import java.util.HashMap;
import java.util.Map;

public class Life {

  Map<CoordsPair, Human> creatureMap;
  Atmosphere atmosphere;

  public Life(Atmosphere atmosphere) {
    this.atmosphere = atmosphere;
    this.creatureMap = new HashMap<>();
  }

  public boolean addCreature(Human human, CoordsPair coords) {
    if (creatureMap.get(coords) != null) {
      return false;
    }
    int visibility = 2;
    if (!atmosphere.moonVisible) {
      visibility -= 1;
    }
    if (atmosphere.isRarefied()) {
      visibility -= 1;
    }

    human.setRangeOfVision(visibility);
    human.coordX = (int) coords.getKey();
    human.coordY = (int) coords.getValue();
    creatureMap.put(coords, human);
    updateFOV(human);
    return true;
  }

  public boolean moveCreature(CoordsPair oldCoords, CoordsPair newCoords) {
    if (creatureMap.get(oldCoords) == null) {
      return false;
    }
    Human human = creatureMap.get(oldCoords);
    boolean forward = (int) newCoords.getKey() > (int) oldCoords.getKey();
    int xDist = Math.abs((int) newCoords.getKey() - (int) oldCoords.getKey());
    int yDist = Math.abs((int) newCoords.getKey() - (int) oldCoords.getKey());

    for (int i = 0; i < xDist; i++) {
      if (!human.makeStep(forward, true)) {
        updateCoords(oldCoords);
        return false;
      }
      if (checkForCollision(human)){
        return false;
      }
      updateFOV(human);

    }

    forward = (int) newCoords.getValue() > (int) oldCoords.getValue();

    for (int i = 0; i < yDist; i++) {
      if (!human.makeStep(forward, false)) {
        updateCoords(oldCoords);
        return false;
      }
      if (checkForCollision(human)){
        return false;
      }
      updateFOV(human);
    }

    updateCoords(oldCoords);
    return true;
  }

  private void updateFOV(Human human) {
    human.fieldOfVision.clear();
    int coordX = human.coordX;
    int coordY = human.coordY;
    for (int i = 1; i <= human.getRangeOfVision(); i++) {
      try {
        if (creatureMap.get(new CoordsPair(coordX + i, coordY)) != null) {
          human.fieldOfVision.add(creatureMap.get(new CoordsPair(coordX + i, coordY)));
        }
      } catch (NullPointerException ignored) {
      }
    }
    for (int i = 1; i <= human.getRangeOfVision(); i++) {
      try {
        if (creatureMap.get(new CoordsPair(coordX - i, coordY)) != null) {
          human.fieldOfVision.add(creatureMap.get(new CoordsPair(coordX - i, coordY)));
        }
      } catch (NullPointerException ignored) {
      }
    }
    for (int i = 1; i <= human.getRangeOfVision(); i++) {
      try {
        if (creatureMap.get(new CoordsPair(coordX, coordY + i)) != null) {
          human.fieldOfVision.add(creatureMap.get(new CoordsPair(coordX, coordY + i)));
        }
      } catch (NullPointerException ignored) {
      }
    }
    for (int i = 1; i <= human.getRangeOfVision(); i++) {
      try {
        if (creatureMap.get(new CoordsPair(coordX, coordY - i)) != null) {
          human.fieldOfVision.add(creatureMap.get(new CoordsPair(coordX, coordY - i)));
        }
      } catch (NullPointerException ignored) {
      }
    }
  }

  private void updateCoords(CoordsPair oldCoords) {
    Human human = creatureMap.get(oldCoords);
    creatureMap.put(oldCoords, null);
    CoordsPair newCoords = new CoordsPair(human.coordX, human.coordY);
    creatureMap.put(newCoords, human);
  }

  private boolean checkForCollision(Human human){
    if (creatureMap.containsKey(new CoordsPair(human.coordX, human.coordY))
        && creatureMap.get(new CoordsPair(human.coordX, human.coordY)) != null) {
      System.out.println(human.name + " столкнулся с " + creatureMap.get(
          new CoordsPair(human.coordX, human.coordY)).name + "!");
      return true;
    }
    return false;
  }
}
