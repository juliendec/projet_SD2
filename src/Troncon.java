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
}
