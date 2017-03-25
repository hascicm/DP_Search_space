package model;

import java.util.ArrayList;
import java.util.List;

public class State {
	private List<Smell> smells;
	int fittnes;
	private List<Repair> avanlibleRepairs; // input from rule engine

	public State() {
		setSmells(new ArrayList<Smell>());
		setAvanlibleRepairs(new ArrayList<Repair>());
	}

	public State(List<Smell> smells, List<Repair> avanlibleRepairs) {
		this.setSmells(smells);
		this.setAvanlibleRepairs(avanlibleRepairs);
	}


	public void calculateFitness() {

		// depth, pravdepodobnost, distance, weighter smells
	}

	public void printSmells() {
		System.out.println("state contain smells:");
		for (Smell s : getSmells()) {
			System.out.println(s.getName());
		}
	}

	public void applyRepair(Repair repair) {

		for (int i = 0; i < getSmells().size(); i++) {
			Smell actual = getSmells().get(i);

			if (repair.getFixes().contains(actual)) {
				System.out.println("REMOVING SMELL: " + actual.getName());
				getSmells().remove(i);
				i--;
			}

		}

		// TODO pridat pridavanie pachov sposobenych opravou

	}

	public List<Smell> getSmells() {
		return smells;
	}

	public void setSmells(List<Smell> smells) {
		this.smells = smells;
	}

	public List<Repair> getAvanlibleRepairs() {
		return avanlibleRepairs;
	}

	public void setAvanlibleRepairs(List<Repair> avanlibleRepairs) {
		this.avanlibleRepairs = avanlibleRepairs;
	}
}
