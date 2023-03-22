import java.util.Objects;

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

  public int getId() {
    return id;
  }

  public String getNumero() {
    return numero;
  }

  public Station getStationDepart() {
    return stationDepart;
  }

  public Station getStationDestination() {
    return stationDestination;
  }

  public String getTypeTransport() {
    return typeTransport;
  }

  public int getTempAttente() {
    return tempAttente;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Ligne ligne = (Ligne) o;

    return getId() == ligne.getId();
  }

  @Override
  public int hashCode() {
    return getId();
  }

  @Override
  public String toString() {
    return super.toString();
  }
}
