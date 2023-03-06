public class Ligne {
  private int id;
  private String numero;
  private Station stationDepart;
  private Station stationDestination;
  public enum typeTransportEnum {
    METRO("metro"), BUS("bus"), TRAM("tram");
    private String type;

    typeTransportEnum(String type) {
      this.type = type;
    }

    public String getType() {
      return type;
    }

  }
  private String typeTransport;
  private int tempAttente;

  public Ligne(int id, String numero, Station stationDepart, Station stationDestination,
      String typeTransport, int tempAttente) {
    this.id = id;
    this.numero = numero;
    this.stationDepart = stationDepart;
    this.stationDestination = stationDestination;
    this.typeTransport = typeTransport;
    this.tempAttente = tempAttente;
  }

  @Override
  public String toString() {
    return super.toString();
  }
}
