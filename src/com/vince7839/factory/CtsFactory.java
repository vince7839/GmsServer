package com.vince7839.factory;

import com.vince7839.entity.Test;
import com.vince7839.entity.test.CtsTest;

public class CtsFactory implements ITestFactory {

	@Override
	public Test getTest() {
		// TODO Auto-generated method stub		
		Test cts = new CtsTest();
		System.out.println("cts factory:" + cts.getName());
		return cts;
	}

}
