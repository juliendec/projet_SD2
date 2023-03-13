import java.util.Objects;

public class Troncon {
  private int numeroLigne;
  private Station stationDepart;
  private Station stationDestination;
  private int dureeTroncon;

  public Troncon(int numeroLigne, Station stationDepart, Station stationDestination,
      int dureeTroncon) {
    this.numeroLigne = numeroLigne;
    this.stationDepart = stationDepart;
    this.stationDestination = stationDestination;
    this.dureeTroncon = dureeTroncon;
  }

  public int getNumeroLigne() {
    return numeroLigne;
  }

  public Station getStationDepart() {
    return stationDepart;
  }

  public Station getStationDestination() {
    return stationDestination;
  }

  public int getDureeTroncon() {
    return dureeTroncon;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Troncon troncon = (Troncon) o;

    if (numeroLigne != troncon.numeroLigne) {
      return false;
    }
    if (dureeTroncon != troncon.dureeTroncon) {
      return false;
    }
    if (!Objects.equals(stationDepart, troncon.stationDepart)) {
      return false;
    }
    return Objects.equals(stationDestination, troncon.stationDestination);
  }

  @Override
  public int hashCode() {
    int result = numeroLigne;
    result = 31 * result + (stationDepart != null ? stationDepart.hashCode() : 0);
    result = 31 * result + (stationDestination != null ? stationDestination.hashCode() : 0);
    result = 31 * result + dureeTroncon;
    return result;
  }

  @Override
  public String toString() {
    return "Troncon{ " +
        "numeroLigne= " + numeroLigne +
        ", stationDepart= " + stationDepart +
        ", stationDestination= " + stationDestination +
        ", dureeTroncon= " + dureeTroncon +
        "}\n";
  }
}
