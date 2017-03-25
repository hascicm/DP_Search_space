package model;

public class RepairFix {
	private Smell smell;
	private int priority;

	public RepairFix(Smell smell, int priority) {
		this.smell = smell;
		this.priority = priority;
	}

	public Smell getSmell() {
		return smell;
	}

	public void setSmell(Smell smell) {
		this.smell = smell;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

}
