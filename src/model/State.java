package model;

import java.util.ArrayList;
import java.util.List;

public class State {
	private List<Smell> smells;
	private int fittnes;
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

	public void printAvanlibleRepairs() {
		System.out.println("state avanlible repairs:");
		for (Repair r : getAvanlibleRepairs()) {
			System.out.println(r.getName());
		}
	}

	public void applyRepair(Repair repair) {

		for (int i = 0; i < smells.size(); i++) {
			Smell actual = smells.get(i);

			if (repair.getFixes().contains(actual)) {
				System.out.println("REMOVING SMELL: " + actual.getName());
				getSmells().remove(i);
				i--;
			}
		}

		for (int i = 0; i < repair.getFixes().size(); i++) {
			RepairFix actual = repair.getFixes().get(i);
			System.out.println("found at index: " + smells.indexOf(actual.getSmell()));
			int index;
			if ((index = smells.indexOf(actual.getSmell())) != -1) {
				System.out.println("found at index: " + smells.indexOf(actual.getSmell()));
				smells.remove(index);

			}

		}

		for (int i = 0; i < repair.getCauses().size(); i++) {
			RepairCause actual = repair.getCauses().get(i);

			// TODO work with probablility
			smells.add(actual.getSmell());
		}

	}

	public ArrayList<State> applyRepairs() {
		ArrayList<State> out = new ArrayList<State>();
		int index;

		for (Repair repair : avanlibleRepairs) {
			// copy state
			State state = new State();
			state.setSmells(new ArrayList<Smell>(smells));
			state.setAvanlibleRepairs(new ArrayList<Repair>(avanlibleRepairs));

			// for each fix within this repair
			for (int i = 0; i < repair.getFixes().size(); i++) {
				RepairFix actual = repair.getFixes().get(i);

				// if there is a smell that it can remove, remove it
				if ((index = smells.indexOf(actual.getSmell())) != -1) {
					System.out.println("found at index: " + smells.indexOf(actual.getSmell()));
					state.getSmells().remove(index);
				}
			}

			// for each cause within this repair
			for (int i = 0; i < repair.getCauses().size(); i++) {
				RepairCause actual = repair.getCauses().get(i);

				// TODO work with probablility
				state.getSmells().add(actual.getSmell());
			}

			System.out.println("------new state using repair " + repair.getName() + "---------");
			state.printSmells();
			// if repair removed som smells, create state
			if (!state.getSmells().containsAll(smells)) {
				out.add(state);
			}
		}
		return out;
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
