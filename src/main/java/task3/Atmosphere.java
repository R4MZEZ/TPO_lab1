package task3;

public class Atmosphere {
  public Weather curWeather;
  public double density; // плотность
  private boolean isRarefied; // разреженность
  public double humidity; // влажность
  public boolean moonVisible;

  public Atmosphere(Weather curWeather, double density, double humidity, boolean MoonVisible) {
    this.curWeather = curWeather;
    this.density = density;
    this.humidity = humidity;
    this.isRarefied = density < 1.293;
    this.moonVisible = MoonVisible;
  }

  public enum Weather{
    RAINY,
    SUNNY,
    CLOUDY,
    STORM,
    SNOW
  }

  public boolean isRarefied() {
    return isRarefied;
  }

  public void setRarefied(boolean rarefied) {
    isRarefied = rarefied;
  }
}
