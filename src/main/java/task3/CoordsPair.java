package task3;

import javafx.util.Pair;

public class CoordsPair extends Pair implements Comparable {

  /**
   * Creates a new pair
   *
   * @param key   The key for this pair
   * @param value The value to use for this pair
   */
  public CoordsPair(int key, int value) {
    super(key, value);
  }

  @Override
  public int compareTo(Object o) {
    if (((CoordsPair) o).getKey() == this.getKey()
        && ((CoordsPair) o).getValue() == this.getValue()) {
      return 0;
    }else
      return -1;
  }
}
