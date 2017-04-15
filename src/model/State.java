package model;

import java.util.ArrayList;
import java.util.List;

public class State {
	private List<Smell> smells; // input from search
	private int fittnes; // calculated on creation
	private List<Repair> avanlibleRepairs; // input from rule engine
	private int depth; // depth of state
	private Repair usedRepair; // repair that was used to create this state
	private State parent; // parrent state

	public State() {
		setUsedRepair(null);
		setParent(null);
		smells = new ArrayList<Smell>();
		avanlibleRepairs = new ArrayList<Repair>();
		depth = 0;
	}

	public State(List<Smell> smells, List<Repair> avanlibleRepairs) {
		this.setSmells(smells);
		this.setAvanlibleRepairs(avanlibleRepairs);
	}

	public State(List<Smell> smells, List<Repair> avanlibleRepairs, Repair usedRepair, State parent) {
		this.smells = smells;
		this.avanlibleRepairs = avanlibleRepairs;
		this.setUsedRepair(usedRepair);
		this.setParent(parent);
		calculateFitness();
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

	public int getFittnes() {
		return fittnes;
	}

	public void setFittnes(int fittnes) {
		this.fittnes = fittnes;
	}

	public void calculateFitness() {
		fittnes = smells.size();
		// depth, pravdepodobnost, distance, weighted smells
	}

	public void findRepairsForSmells(List<Repair> repairs) {
		for (Smell s : smells) {
			for (Repair r : repairs) {
				for (RepairFix f : r.getFixes()) {
					if (f.getSmell().equals(s) && !avanlibleRepairs.contains(r)) {
						avanlibleRepairs.add(r);
					}
				}
			}
		}
	}

	/**
	 * Depreciated
	 * 
	 * @return
	 */
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
				if ((index = state.getSmells().indexOf(actual.getSmell())) != -1) {
					state.getSmells().remove(index);
				}
			}

			// for each cause within this repair
			for (int i = 0; i < repair.getCauses().size(); i++) {
				RepairCause actual = repair.getCauses().get(i);

				// TODO work with probablility
				state.getSmells().add(actual.getSmell());
			}

			// if repair removed some smells, create state
			state.calculateFitness();
			if (!state.getSmells().containsAll(smells)) {
				out.add(state);
			}
		}
		return out;
	}

	/**
	 * takes one state and create all possible states created by repairs
	 * 
	 * @return list of newly created states
	 */
	public ArrayList<State> expandStete() {
		ArrayList<State> out = new ArrayList<State>();

		for (Repair repair : avanlibleRepairs) {
			System.out.println("----using " + repair.getName() + " ---------");
			State newState = applyRepair(repair);

			if (newState != null) {
				newState.avanlibleRepairs.remove(repair);
				newState.setParent(this);
				newState.setUsedRepair(repair);
				out.add(newState);
			}
		}
		return out;
	}

	/**
	 * 
	 * @param repair
	 *            takes one repair and apply it on one current state
	 * @return return state if some smells was removed, null if nothing usefull
	 *         happened
	 */
	public State applyRepair(Repair repair) {
		// index of found smell
		int index;
		// indicator of usefulness of repair

		boolean repaired = false;
		State newState = copyState();

		// for each fix within this repair
		for (RepairFix actual : repair.getFixes()) {
			// if there is a smell that it can remove, remove it
			if ((index = newState.getSmells().indexOf(actual.getSmell())) != -1) {
				newState.getSmells().remove(index);
				repaired = true;
			}
		}

		// for each cause within this repair
		for (RepairCause actual : repair.getCauses()) {
			// TODO work with probability
			newState.getSmells().add(actual.getSmell());
		}
		if (repaired)
			return newState;
		else
			return null;
	}

	/**
	 * creates copy of state
	 * 
	 * @return
	 */
	private State copyState() {
		System.out.println("----- creating copy -------");
		State state = new State();
		state.setSmells(new ArrayList<Smell>(smells));
		state.setAvanlibleRepairs(new ArrayList<Repair>(avanlibleRepairs));
		state.depth = this.depth + 1;
		state.setParent(this);
		state.printSmells();
		state.printAvanlibleRepairs();
		System.out.println("----- returning copy -------");

		return state;
	};

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

	public State getParent() {
		return parent;
	}

	public void setParent(State parent) {
		this.parent = parent;
	}

	public Repair getUsedRepair() {
		return usedRepair;
	}

	public void setUsedRepair(Repair usedRepair) {
		this.usedRepair = usedRepair;
	}

}
