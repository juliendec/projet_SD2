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
}
