package com.hcl.hawkeye.batch.build.DO;

public class PlanResultKey {

    private EntityKey entityKey;

    private String resultNumber;

    private String key;

	public EntityKey getEntityKey() {
		return entityKey;
	}

	public void setEntityKey(EntityKey entityKey) {
		this.entityKey = entityKey;
	}

	public String getResultNumber() {
		return resultNumber;
	}

	public void setResultNumber(String resultNumber) {
		this.resultNumber = resultNumber;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public String toString() {
		return "PlanResultKey [entityKey=" + entityKey + ", resultNumber=" + resultNumber + ", key=" + key + "]";
	}
    
}
