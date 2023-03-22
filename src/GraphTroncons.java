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
import java.util.*;

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

				ligneTronconMap.put(ligneInstance,new HashSet<>());

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

		Deque<Troncon> tronconsDeque = new ArrayDeque<>();
		Troncon troncon;
		System.out.println("arriver : " + provenance.get(stationDes));
		while((troncon = provenance.get(stationDes)) != null){
			tronconsDeque.addFirst(troncon);
			stationDes = troncon.getStationDepart();
		}

		toStringDeque(tronconsDeque);

		return null;
	}

	public Deque<Troncon> calculerCheminMinimisantTempsTransport(String stationDep, String stationDes ) {
		Station stationDepart = new Station(stationDes);
		Station stationDestination = new Station(stationDep);
		Map<Station, Integer> distances = new HashMap<>();
		Map<Station, Station> provenance = new HashMap<>();
		Set<Station> visites = new HashSet<>();
		PriorityQueue<Station> filePriorite = new PriorityQueue<>((s1, s2) -> distances.get(s1) - distances.get(s2));

		for (Station station : stationTronconMap.keySet()) {
			distances.put(station, Integer.MAX_VALUE);
		}
		distances.put(stationDepart, 0);
		filePriorite.add(stationDepart);

		while (!filePriorite.isEmpty()) {
			Station station = filePriorite.poll();
			if (visites.contains(station)) {
				continue;
			}
			visites.add(station);
			if (station.equals(stationDestination)) {
				break;
			}

			for (Troncon troncon : stationTronconMap.get(station)) {
				Station destination = troncon.getStationDestination();
				if (visites.contains(destination)) {
					continue;
				}
				int nouvelleDistance = distances.get(station) + troncon.getDureeTroncon();
				if (nouvelleDistance < distances.get(destination)) {
					distances.put(destination, nouvelleDistance);
					provenance.put(destination, station);
					filePriorite.add(destination);
				}
			}
		}


		Deque<Troncon> itineraire = new ArrayDeque<>();
		Station station = stationDestination;
		while (provenance.containsKey(station)) {
			Station predecesseur = provenance.get(station);

			Troncon troncon = null;

			for (Troncon tronconFor : stationTronconMap.get(station)) {
				if (tronconFor.getStationDestination().equals(predecesseur)) {
						troncon = tronconFor;
						break;
				}
			}

			itineraire.addLast(troncon);
			station = predecesseur;
		}

		toStringDeque(itineraire);
		return itineraire;
	}

	private void toStringDeque(Deque<Troncon> tronconsDeque) {

		List<Ligne> lignesSet = ligneTronconMap.keySet().stream().toList();

		Deque<Troncon> itineraire = new ArrayDeque<>();
		Deque<Integer> itineraireCouts = new ArrayDeque<>();

		itineraire.addLast(new Troncon(Integer.MIN_VALUE,null,null,Integer.MIN_VALUE));


		for (Troncon troncon1 : tronconsDeque) {
		//	System.out.println(troncon1.getNumeroLigne()  + " - "  + troncon1.getStationDepart() + " - " + troncon1.getStationDestination() + " - " + troncon1.getDureeTroncon());
			Troncon tronconPrecedant = itineraire.getLast();
			if (troncon1.getNumeroLigne() == tronconPrecedant.getNumeroLigne()) {
				itineraire.removeLast();
				Troncon tronconMix = new Troncon(troncon1.getNumeroLigne(), tronconPrecedant.getStationDepart(),
						troncon1.getStationDestination(), troncon1.getDureeTroncon()
						+ tronconPrecedant.getDureeTroncon());
				itineraire.addLast(tronconMix);
				int nbrTroncons = itineraireCouts.removeLast() +1;
				if (nbrTroncons == 0){
					itineraireCouts.addLast(1);
				}else {
					itineraireCouts.addLast(nbrTroncons);
				}

			} else {
				itineraireCouts.addLast(1);
				itineraire.addLast(troncon1);
			}
		}

		itineraire.removeFirst();

		int dureeTransport = 0;
		int dureeTotal = 0;
		int nbrTronconTot = 0;

		Troncon tronconFinal = itineraire.getLast();

		for (Troncon troncon: itineraire) {

			int index = lignesSet.indexOf(new Ligne(troncon.getNumeroLigne(),null,null,null,null,0));
			Ligne ligne = lignesSet.get(index);
			if (!troncon.equals(tronconFinal)){
				dureeTotal+=  ligne.getTempAttente();
			}
			dureeTotal += troncon.getDureeTroncon();
			dureeTransport += troncon.getDureeTroncon();
			int nbrTroncon = itineraireCouts.removeFirst();
			nbrTronconTot += nbrTroncon;
			System.out.println("Deplacement [ligne=" + troncon.getNumeroLigne()
								+ ", depart=" + troncon.getStationDepart()
								+ ", arrivee=" + troncon.getStationDestination()
								+ ", duree= " + troncon.getDureeTroncon()
								+ ", attente moyenne=" + ligne.getTempAttente()
								+ ", type de transport=" + ligne.getTypeTransport()
								+ ", nbTroncon=" + nbrTroncon
								+ ", direction=" + ligne.getStationDestination() + ']');
		}

		System.out.println( "nbTroncon=" + nbrTronconTot + '\n' + "dureeTransport=" + dureeTransport + ", durreeTotal=" + dureeTotal);

		//TODO est ce que les donnee sont juste pour le plus court ? c est chelou par rapport a la fiche du prof
	}

}

