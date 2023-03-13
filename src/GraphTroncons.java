import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GraphTroncons {

	protected Map<Ligne, Troncon> correspondanceIataAirport;


	public GraphTroncons(File lignes, File troncons) throws Exception {
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
				System.out.println(ligneInstance.toString());
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
				System.out.println(tronconInstance.toString());
			}

			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("Le fichier n'a pas été trouvé.");
		}

	}




}
