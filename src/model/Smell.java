package model;

public class Smell {
	private int id;
	private String name;
	private int priority;

	public Smell() {
	}

	public Smell(String name, int priority) {
		this.name = name;
		this.priority = priority;
	}

	public Smell(String name) {
		this.name = name;
		this.priority = 10;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

}
