package com.vince7839.factory;

import com.vince7839.entity.Test;
import com.vince7839.entity.test.PerfomTest;

public class PerformFactory implements ITestFactory {

	@Override
	public Test getTest() {
		// TODO Auto-generated method stub
		return new PerfomTest();
	}

}
