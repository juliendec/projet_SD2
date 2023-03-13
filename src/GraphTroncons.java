import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Formatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import java.util.SortedSet;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GraphTroncons {

	protected Map<Ligne, Set<Troncon> > ligneTronconMap;

	private Map<Station, Set<Troncon> > stationTronconMap;


	public GraphTroncons(File lignes, File troncons) throws Exception {
		this.stationTronconMap = new HashMap<>();
		this.ligneTronconMap = new HashMap<>();
		constructLigneFromTXT(lignes);
		constructTronconFromTXT(troncons);
	}

	public void constructLigneFromTXT(File file) throws Exception {
		try {
			Scanner scanner = new Scanner(file);

			while (scanner.hasNextLine()) {
				String ligne = scanner.nextLine();
				String[] attributs = ligne.split(",");

				int id = Integer.parseInt(attributs[0]);
				String numero = attributs[1];
				Station stationDepart = new Station(attributs[2]);
				Station stationArrive = new Station(attributs[3]);
				String typeTransport = attributs[4];
				int dureeTroncon = Integer.parseInt(attributs[5]);

				Ligne ligneInstance = new Ligne(id, numero, stationDepart, stationArrive, typeTransport, dureeTroncon);



			//	System.out.println(ligneInstance.toString());
			}

			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("Le fichier n'a pas été trouvé.");
		}

	}


	public void constructTronconFromTXT(File file) throws Exception {
		try {
			Scanner scanner = new Scanner(file);

			while (scanner.hasNextLine()) {
				String ligne = scanner.nextLine();
				String[] attributs = ligne.split(",");

				int numeroLigne = Integer.parseInt(attributs[0]);
				Station stationDepart = new Station(attributs[1]);
				Station stationArrive = new Station(attributs[2]);
				int dureeTroncon = Integer.parseInt(attributs[3]);

				Troncon tronconInstance = new Troncon(numeroLigne, stationDepart, stationArrive, dureeTroncon);
				if (!stationTronconMap.containsKey(tronconInstance.getStationDepart())){
					stationTronconMap.put(tronconInstance.getStationDepart(),new HashSet<>());
				}
				stationTronconMap.get(tronconInstance.getStationDepart()).add(tronconInstance);


			//	System.out.println(tronconInstance.toString());
			}

			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("Le fichier n'a pas été trouvé.");
		}

	}


	//bfs
	Deque<Troncon> calculerCheminMinimisantNombreTroncons(String stationDepart, String stationDestination){
		Station stationDep = new Station(stationDepart);
		Station stationDes = new Station(stationDestination);

		HashMap<Station,Troncon> provenance = new HashMap<>();
		LinkedList<Station> file = new LinkedList<>();
		Set<Station> visite = new HashSet<>();
		visite.add(stationDep);
		file.addLast(stationDep);

		while (!visite.contains(stationDes) && !file.isEmpty()) {
			Station stationActuelleDepart = file.removeFirst();
			for (Troncon troncon : stationTronconMap.get(stationActuelleDepart)) {
				Station stationActuelleDest = troncon.getStationDestination();
				if (!visite.contains(stationActuelleDest)) {
					file.add(stationActuelleDest);
					visite.add(stationActuelleDest);
					provenance.put(stationActuelleDest,troncon);
				}
			}
		}



		/*Deque<Troncon> tronconList = new LinkedList<>();
		Station stationtemp = stationDes;
		Station stationTempDest = null;

		while (stationtemp.equals(stationDep)){
			stationTempDest = provenance.get(stationtemp).getStationDepart();
			for (Troncon troncon : stationTronconMap.get(stationTempDest)) {
				if (troncon.getStationDestination().equals(stationtemp)){
					tronconList.addFirst(troncon);
					System.out.println(troncon.getStationDepart().toString());
				}
			}
			stationtemp = stationTempDest;
		}
*/

		Deque<Troncon> tronconsDeque = new ArrayDeque<>();
		Troncon troncon;
		System.out.println("arriver : " + provenance.get(stationDes));
		while((troncon = provenance.get(stationDes)) != null){
			System.out.println("dans while");
			tronconsDeque.addFirst(troncon);
			stationDes = troncon.getStationDepart();
		}

		for (Troncon troncon1 : tronconsDeque) {
			System.out.println(troncon1.getStationDepart() + " - " + troncon1.getStationDestination());
		}

		//afficherDeque(tronconList);

		return null;
	}



		//djikstra
	List<Troncon> calculerCheminMinimisantTempsTransport(Station stationDepart, Station stationDestination){
	return null;
	}



	void afficherDeque(Deque<Troncon> deque){

		for (Troncon troncon : deque) {
			System.out.println(troncon.toString());
		}
	}

}
