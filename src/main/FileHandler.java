package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import model.Repair;
import model.RepairCause;
import model.RepairFix;
import model.Smell;

public class FileHandler {
	List<String> lines = new ArrayList<String>();

	public FileHandler() {

	}

	public void loadFileAsText(String filename) {
		try {
			File file = new File(filename);
			String line;
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while ((line = bufferedReader.readLine()) != null)
				lines.add(line);
			fileReader.close();
			System.out.println("file " + file.getName() + "loaded");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void printlines() {
		for (String s : lines) {
			System.out.println(s);
		}
	}

	public static void loadSmells(List<Smell> smells) {

		try {
			File file = new File("smells.xml");
			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			NodeList smellList = doc.getElementsByTagName("smell");

			for (int i = 0; i < smellList.getLength(); i++) {
				Node repairNode = smellList.item(i);
				Smell smellTemp = new Smell();
				// System.out.println("\nCurrent Element :" +
				// repairNode.getNodeName());

				if (repairNode.getNodeType() == Node.ELEMENT_NODE) {

					Element repairE = (Element) repairNode;
					smellTemp.setId(Integer.parseInt(repairE.getElementsByTagName("id").item(0).getTextContent()));
					// System.out.println("id: " +
					// repairE.getElementsByTagName("name").item(0).getTextContent());

					smellTemp.setName(repairE.getElementsByTagName("name").item(0).getTextContent());
					// System.out.println("name: " +
					// repairE.getElementsByTagName("name").item(0).getTextContent());

					smellTemp.setPriority(Integer
							.parseInt(repairE.getElementsByTagName("priority").item(0).getTextContent()));
					// System.out.println("priority: " +
					// repairE.getElementsByTagName("name").item(0).getTextContent());
					smells.add(smellTemp);
				}
			}
		} catch (ParserConfigurationException | IOException | SAXException e) {
			e.printStackTrace();
		}

	}

	public static void printSmells(List<Smell> smells) {
		System.out.println("number of smells:" + smells.size());
		for (Smell smell : smells) {
			smells.get(1);
			System.out.println("name:      " + smell.getName());
			System.out.println("id:        " + smell.getId());
			System.out.println("priority:  " + smell.getPriority());
		}
		System.out.println("ASDASDASDWQDQW:::: "+ smells.get(0).getId());

	}

	public static void loadRepairs(List<Repair> repairs, List<Smell> smells) {

		try {
			File file = new File("repairs.xml");
			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			NodeList repairList = doc.getElementsByTagName("repair");

			for (int i = 0; i < repairList.getLength(); i++) {
				Node repairNode = repairList.item(i);
				Repair repairTemp = new Repair();
				// System.out.println("\nCurrent Element :" +
				// repairNode.getNodeName());

				if (repairNode.getNodeType() == Node.ELEMENT_NODE) {

					Element repairE = (Element) repairNode;
					repairTemp.setName(repairE.getElementsByTagName("name").item(0).getTextContent());
					// System.out.println("name: " +
					// repairE.getElementsByTagName("name").item(0).getTextContent());

					NodeList causeXML = repairE.getElementsByTagName("cause");
					for (int j = 0; j < causeXML.getLength(); j++) {
						repairTemp.getCauses().add(smells.get(Integer.parseInt(causeXML.item(j).getTextContent())));
						// System.out.println("cause: " +
						// causeXML.item(j).getTextContent());
					}

					NodeList fixXML = repairE.getElementsByTagName("fix");
					for (int j = 0; j < fixXML.getLength(); j++) {
						repairTemp.getFixes().add(smells.get(Integer.parseInt(fixXML.item(j).getTextContent())));
						// System.out.println("fix: " +
						// fixXML.item(j).getTextContent());
					}
					repairs.add(repairTemp);
				}
			}
		} catch (ParserConfigurationException | IOException | SAXException e) {
			e.printStackTrace();
		}
	}

	public static void printrepairs(List<Repair> repairs) {
		for (Repair repair : repairs) {
			System.out.println("name: " + repair.getName());
			for (RepairCause cause : repair.getCauses()) {
				System.out.println("cause:" + cause.getSmell().getName());
			}
			for (RepairFix fix : repair.getFixes()) {
				System.out.println("fix: " + fix.getSmell().getName());
			}
		}
	}

}