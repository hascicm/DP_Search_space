package model;

public class RepairCause {
	private Smell smell;
	private int probabilty;

	public RepairCause() {
	}

	public RepairCause(Smell smell, int probabilty) {
		super();
		this.smell = smell;
		this.probabilty = probabilty;
	}

	public Smell getSmell() {
		return smell;
	}

	public void setSmell(Smell smell) {
		this.smell = smell;
	}

	public int getProbabilty() {
		return probabilty;
	}

	public void setProbabilty(int probabilty) {
		this.probabilty = probabilty;
	}

}
