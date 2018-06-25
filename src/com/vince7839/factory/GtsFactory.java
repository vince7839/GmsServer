package com.vince7839.factory;

import com.vince7839.entity.Test;
import com.vince7839.entity.test.GtsTest;

public class GtsFactory implements ITestFactory {

	@Override
	public Test getTest() {
		// TODO Auto-generated method stub
		return new GtsTest();
	}

}
