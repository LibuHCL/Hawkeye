package com.hcl.hawkeye.valueaddmanagement.DO;

import java.util.ArrayList;

public class Result {

	  private String _programName;

	  public String getProgramName() { return this._programName; }

	  public void setProgramName(String _programName) { this._programName = _programName; }

	  private int _programId;

	  public int getProgramId() { return this._programId; }

	  public void setProgramId(int _programId) { this._programId = _programId; }

	  private ArrayList<Kpi> kpis;

	  public ArrayList<Kpi> getKpis() { return this.kpis; }

	  public void setKpis(ArrayList<Kpi> kpis) { this.kpis = kpis; }


}
