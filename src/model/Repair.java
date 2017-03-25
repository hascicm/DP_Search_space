package model;

import java.util.ArrayList;
import java.util.List;

public class Repair {
	private String name;
	private String description;
	private List<RepairCause> causes;
	private List<RepairFix> fixes;

	public Repair() {
		causes = new ArrayList<RepairCause>();
		fixes = new ArrayList<RepairFix>();

	}

	public Repair(String name, String description, List<RepairCause> cause, List<RepairFix> fixes) {
		this.name = name;
		this.causes = cause;
		this.fixes = fixes;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<RepairCause> getCauses() {
		return causes;
	}

	public void setCauses(List<RepairCause> causes) {
		this.causes = causes;
	}

	public List<RepairFix> getFixes() {
		return fixes;
	}

	public void setFixes(List<RepairFix> fixes) {
		this.fixes = fixes;
	}

}
