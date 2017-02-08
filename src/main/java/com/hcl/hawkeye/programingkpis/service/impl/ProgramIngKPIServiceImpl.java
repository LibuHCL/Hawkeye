package com.hcl.hawkeye.programingkpis.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hcl.hawkeye.programingkpis.DO.KPIType;
import com.hcl.hawkeye.programingkpis.DO.KPIValue;
import com.hcl.hawkeye.programingkpis.DO.Result;
import com.hcl.hawkeye.programingkpis.service.ProgramIngKPIService;

@Service
public class ProgramIngKPIServiceImpl implements ProgramIngKPIService{

	@Override
	public Result getKpiResults() {
		Result res = new Result();
		List<KPIType> Klist = new ArrayList<KPIType>();
		KPIType kp = new KPIType();
		List<KPIValue> kVList = new ArrayList<KPIValue>();
		KPIValue kv = new KPIValue();
		kv.set_name("Test 1");
		kv.set_labels(new ArrayList<String>());
		kv.set_graphdata(new ArrayList<Integer>());
		kVList.add(kv);
		kp.setKpis(kVList);
		kp.set_programId(1111);
		kp.set_programName("Program 1");
		Klist.add(kp);
		res.setResult(Klist);
		
		return res;
	}

}
