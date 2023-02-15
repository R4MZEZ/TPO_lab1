package task1;

public class ArcTg {
//  public static double calculateArcsin(double x){
//    if (Math.abs(x) > 1) {
//      return Double.NaN;
//    }
//    double ans = x;
//    double tmp = 1;
//    double divider = 3;
//    double multiplier = 1;
//    while (tmp > 0.001){
//      multiplier *= (divider - 2) / (divider - 1);
//      tmp = multiplier * Math.pow(x, divider) / divider;
//      ans += tmp;
//      divider += 2;
//    }
//    return ans;
//  }

  public static double calculateArctg(double x){
    if (Math.abs(x) > 1) {
      return Double.NaN;
    }
    double ans = x;
    double tmp = 1;
    double divider = 3;
    int sign = -1;
    while (Math.abs(tmp) > 0.001){
      tmp = sign * Math.pow(x, divider) / divider;
      ans += tmp;
      divider += 2;
      sign *= -1;
    }
    return ans;
  }

}

