package com.hcl.hawkeye.batch.build.DO;

public class Plan {

	private PlanKey planKey;

	private String enabled;

	private String name;

	private String shortKey;

	private String type;

	private String shortName;

	private String key;

	public PlanKey getPlanKey() {
		return planKey;
	}

	public void setPlanKey(PlanKey planKey) {
		this.planKey = planKey;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortKey() {
		return shortKey;
	}

	public void setShortKey(String shortKey) {
		this.shortKey = shortKey;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public String toString() {
		return "Plan [planKey=" + planKey + ", enabled=" + enabled + ", name=" + name + ", shortKey=" + shortKey
				+ ", type=" + type + ", shortName=" + shortName + ", key=" + key + "]";
	}
	
	
}
