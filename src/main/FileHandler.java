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
				if (repairNode.getNodeType() == Node.ELEMENT_NODE) {

					Element repairE = (Element) repairNode;
					smellTemp.setId(Integer.parseInt(repairE.getElementsByTagName("id").item(0).getTextContent()));
					smellTemp.setName(repairE.getElementsByTagName("name").item(0).getTextContent());
					smellTemp.setPriority(
							Integer.parseInt(repairE.getElementsByTagName("priority").item(0).getTextContent()));
					smells.add(smellTemp);
				}
			}
		} catch (ParserConfigurationException | IOException | SAXException e) {
			e.printStackTrace();
		}
	}

	public static void loadRepairs(List<Repair> repairs, List<Smell> smells) {

		try {
			File file = new File("repairs.xml");
			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			NodeList repairList = doc.getElementsByTagName("repair");
			Integer couter = 0;

			for (int i = 0; i < repairList.getLength(); i++) {
				Node repairNode = repairList.item(i);
				Repair repairTemp = new Repair();
				if (repairNode.getNodeType() == Node.ELEMENT_NODE) {

					Element repairE = (Element) repairNode;
					repairTemp.setId(Integer.parseInt(repairE.getElementsByTagName("id").item(0).getTextContent()));
					repairE.getElementsByTagName("id").item(0).setTextContent("dsadsa");
					couter++;
					repairTemp.setName(repairE.getElementsByTagName("name").item(0).getTextContent());
					repairTemp.setDescription(repairE.getElementsByTagName("description").item(0).getTextContent());

					NodeList fixXML = repairE.getElementsByTagName("fix");
					for (int j = 0; j < fixXML.getLength(); j++) {
						Node fixNode = fixXML.item(j);

						if (fixNode.getNodeType() == Node.ELEMENT_NODE) {
							Element fixE = (Element) fixNode;

							RepairFix repairFixTepm = new RepairFix();
							NodeList fixsmellXML = fixE.getElementsByTagName("fixsmell");
							repairFixTepm.setSmell(smells.get(Integer.parseInt(fixsmellXML.item(0).getTextContent())));
							NodeList priorityXML = fixE.getElementsByTagName("priority");
							repairFixTepm.setPriority(Integer.parseInt(priorityXML.item(0).getTextContent()));

							repairTemp.getFixes().add(repairFixTepm);
						}
					}

					NodeList causeXML = repairE.getElementsByTagName("cause");
					for (int j = 0; j < causeXML.getLength(); j++) {

						Node fixNode = causeXML.item(j);

						if (fixNode.getNodeType() == Node.ELEMENT_NODE) {
							Element causeE = (Element) fixNode;

							RepairCause repaircauseTemp = new RepairCause();
							NodeList causesmellXML = causeE.getElementsByTagName("causesmell");
							repaircauseTemp
									.setSmell(smells.get(Integer.parseInt(causesmellXML.item(0).getTextContent())));
							NodeList probabilityXML = causeE.getElementsByTagName("probabilty");
							repaircauseTemp.setProbabilty(Integer.parseInt(probabilityXML.item(0).getTextContent()));

							repairTemp.getCauses().add(repaircauseTemp);
						}
					}

					repairs.add(repairTemp);
				}
			}
		} catch (ParserConfigurationException | IOException | SAXException e) {
			e.printStackTrace();
		}
	}

	public static void printSmells(List<Smell> smells) {
		System.out.println("number of smells:" + smells.size());
		for (Smell smell : smells) {
			System.out.println("id:        " + smell.getId());
			System.out.println("name:      " + smell.getName());
			System.out.println("priority:  " + smell.getPriority());
			System.out.println("--------------------");
		}
		System.out.println("------smells end-------------");
	}

	public static void printrepairs(List<Repair> repairs) {
		for (Repair repair : repairs) {
			System.out.println("id          : " + repair.getId());
			System.out.println("name        : " + repair.getName());
			System.out.println("description : " + repair.getDescription());

			for (RepairFix fix : repair.getFixes()) {
				System.out.println("fix   : " + fix.getSmell().getName());
				System.out.println("prior : " + fix.getPriority());
				System.out.println("------------");

			}
			for (RepairCause cause : repair.getCauses()) {
				System.out.println("cause :" + cause.getSmell().getName());
				System.out.println("prob  :" + cause.getProbabilty());
				System.out.println("------------");

			}
			System.out.println("----next----");
		}
		System.out.println("--------repairs end----------");
	}

}