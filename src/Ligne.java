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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Ligne ligne = (Ligne) o;

    if (id != ligne.id) {
      return false;
    }
    if (tempAttente != ligne.tempAttente) {
      return false;
    }
    if (!Objects.equals(numero, ligne.numero)) {
      return false;
    }
    if (!Objects.equals(stationDepart, ligne.stationDepart)) {
      return false;
    }
    if (!Objects.equals(stationDestination, ligne.stationDestination)) {
      return false;
    }
    return Objects.equals(typeTransport, ligne.typeTransport);
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (numero != null ? numero.hashCode() : 0);
    result = 31 * result + (stationDepart != null ? stationDepart.hashCode() : 0);
    result = 31 * result + (stationDestination != null ? stationDestination.hashCode() : 0);
    result = 31 * result + (typeTransport != null ? typeTransport.hashCode() : 0);
    result = 31 * result + tempAttente;
    return result;
  }

  @Override
  public String toString() {
    return super.toString();
  }
}
